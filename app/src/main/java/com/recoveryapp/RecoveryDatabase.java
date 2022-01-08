package com.recoveryapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.recoveryapp.dao.CategoryDao;
import com.recoveryapp.dao.ExerciseDao;
import com.recoveryapp.dao.ExerciseSetDao;
import com.recoveryapp.dao.WorkoutDao;
import com.recoveryapp.dao.WorkoutExerciseSetDao;
import com.recoveryapp.dao.WorkoutLogDao;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.entities.ExerciseSet;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutExerciseSetCrossRef;
import com.recoveryapp.entities.WorkoutLog;

@Database(entities = {Category.class, Exercise.class, ExerciseSet.class, Workout.class, WorkoutLog.class,WorkoutExerciseSetCrossRef.class}, version = 1)
public abstract class RecoveryDatabase extends RoomDatabase {
    private static RecoveryDatabase instance;

    public abstract CategoryDao categoryDao();
    public abstract ExerciseDao exerciseDao();
    public abstract ExerciseSetDao exerciseSetDao();
    public abstract WorkoutDao workoutDao();
    public abstract WorkoutLogDao workoutLogDao();
    public abstract WorkoutExerciseSetDao workoutExerciseSetDao();

    public static synchronized  RecoveryDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),RecoveryDatabase.class, "recovery_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new SeedDatabaseAsyncTask(instance).execute();
        }
    };
    private static class SeedDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private CategoryDao categoryDao;
        private ExerciseDao exerciseDao;
        private WorkoutDao workoutDao;
        private WorkoutLogDao workoutLogDao;
        private ExerciseSetDao exerciseSetDao;
        private WorkoutExerciseSetDao workoutExerciseSetDao;

        public SeedDatabaseAsyncTask(RecoveryDatabase database) {
            categoryDao = database.categoryDao();
            exerciseDao = database.exerciseDao();
            workoutDao = database.workoutDao();
            workoutLogDao = database.workoutLogDao();
            exerciseSetDao = database.exerciseSetDao();
            workoutExerciseSetDao = database.workoutExerciseSetDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.insert(new Category("Kregosłup","Kregosłup to jedna z najważniejszych części ciała","kregoslup_kategoria"));
            categoryDao.insert(new Category("Dłonie","Okolica ciała obejmująca powierzchnię przednią ręki","nadgarstek_kategoria"));
            categoryDao.insert(new Category("Kolana","Potoczna nazwa stawu kolanowego ssaków","kolano_kategoria"));

            exerciseDao.insert(new Exercise("Koci grzbiet","Ćwiczenie pilatesowe","Pozycja wyjściowa: Klęk podparty. Kręgosłup w pozycji neutralnej. Brzuch wciągnięty.\n" +
                    "Ruch: \n" +
                    "* Unieś głowę – wdech.\n" +
                    "* Opuść głowę, wypchnij środkową część kręgosłupa „koci grzbiet” – wydech.","cw_1",1));
            exerciseDao.insert(new Exercise("Rozciąganie pleców","Ćwiczenie rozciągające","Pozycja wyjściowa: Klęk podparty.\n" +
                    "Ruch: \n* Ściągnij łopatki, wyprostuj plecy – wdech.\n" +
                    "* Siądź na piętach, wyciągnij ręce jak najdalej w przód („ukłon japoński”) – wydech","cw_2",1));
            exerciseDao.insert(new Exercise("Superman","Ćwiczenie rozciągające","Pozycja wyjściowa: Klęk podparty.\n" +
                    "Ruch: \n* Wyciągnij prawą rękę w przód, lewą nogę w tył – wdech\n" +
                    "* Zegnij lewą nogę w kolanie, prawą rękę w łokciu i przyciągnij do siebie. Dotknij prawym łokciem do lewego kolana – wydec" +
                    "* Powtórz ćwiczenie na drugą stronę.","cw_3",1));
            exerciseDao.insert(new Exercise("Klęk na lewym kolanie","Ćwiczenie rozciągające","Pozycja wyjściowa: Klęk na lewym kolanie. Obie ręce oparte na prawym kolanie.\n" +
                    "Ruch: \n* Przesuń biodra w przód i dół. Końcową pozycję utrzymaj 30 sekund. Oddychaj regularnie.\n" +
                    "* Powtórz ćwiczenie na drugą stronę zmieniając pozycję wyjściową na klęk na prawym kolanie. Obie ręce oparte na lewym kolanie.\n","cw_4",2));


            exerciseDao.insert(new Exercise("Leżenie na brzuchu","Ćwiczenie pilatesowe","Pozycja wyjściowa: Leżenie na brzuchu. Łokcie ugięte, ręce oparte na materacu na wysokości klatki piersiowej.\n" +
                    "Ruch: \n* Wyprostuj łokcie, powoli odchyl się w tył.\n" +
                    "* Utrzymaj końcową pozycję 30 sekund. Oddychaj regularnie.","cw_5",1));
            exerciseDao.insert(new Exercise("Leżenie na plecach","Ćwiczenie rozciągające odcinek lędzwiowy","Pozycja wyjściowa:  Leżenie na plecach, ręce wzdłuż tułowia, lewa noga zgięta w kolanie, stopa oparta na materacu, prawa noga wyprostowana na materacu.\n" +
                    "Ruch: \n* Unieś prawą nogę w górę: (wyprostowane kolano, stopa zgięta grzbietowo). Końcową pozycję utrzymaj 30 sekund.\n" +
                    "* Powrót do pozycji wyjściowej.\n" +
                    "* Powtórz ćwiczenie na drugą stronę.","cw_6",3));
            exerciseDao.insert(new Exercise("Leżenie z rozłożonymi nogami","Ćwiczenie rozciągające","Pozycja wyjściowa: Leżenie na plecach, ręce wzdłuż tułowia, kolana ugięte, stopy oparte na materacu.\n" +
                    "Ruch: \n* Wyciągnij prawą rękę w przód, lewą nogę w tył – wdech\n" +
                    "* Złącz stopy, rozstaw szeroko kolana, zbliż stopy możliwie najbliżej do tułowia.","cw_7",2));
            exerciseDao.insert(new Exercise("Kołyska","Ćwiczenie rozciągające","Pozycja wyjściowa: Klęk na lewym kolanie. Obie ręce oparte na prawym kolanie.\n" +
                    "Ruch: \n* Przesuń biodra w przód i dół. Końcową pozycję utrzymaj 30 sekund\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Przyciągnij kolana do brzucha, obejmij rękoma i wykonaj ruchy w przód i w tył – „kołyska”\n","cw_8",2));

            exerciseDao.insert(new Exercise("Skłony ciała","Najprostsze rozciągające ćwiczenie, polega na klasycznych skłonach tułowia w przód. ... W czasie ćwiczenia ważna jest technika oddychania. Wydech następuje podczas ruchu ramion, a przy powrocie do pozycji wyjściowej jest wdech.","Ruch: \n* Stań ze złączonymi nogami\n" +
                    "* Prowadząc dłonie blisko ciała, zejdź w dół do skłonu\n" +
                    "* Powoli podnoś się aż do wyprostu kręgosłupa","sklony",1));
            exerciseDao.insert(new Exercise("Martwe ciagi","Ćwiczenie wielostawowe","Pozycja wyjściowa: Stan nad sztangą nogi na szerokości barków \n" +
                    "Ruch: \n* Stojąc w pozycji wyprostowanej, ściągamy łopatki do siebie i w pierwszej fazie ruchu wypychamy biodra do tyłu, zaczynając obniżać pozycję.","martwe_ciagi",2));
            exerciseDao.insert(new Exercise("Wymachy nogami","Ćwiczenie poprawiające stabilność","Ruch: \n* Stań na jednej nodze i wykonuj wymachy jedną noga \n" +
                    "* Po wykonaniu połowy czasu zmień noge","wymachy_nogami",3));
            exerciseDao.insert(new Exercise("Skip B","Ćwiczenie doskalającego szybkość","Ruch: \n* Naprzemiennie podność nogi jak najwyzej klatki pierosiowej","skip_b",3));

            workoutDao.insert(new Workout("Zdrowy kregosłup lędzwiowy 1","Pierwsza część treningu","zdrowy_kregoslup_trening1",1,1));
            workoutDao.insert(new Workout("Zdrowy kregosłup lędzwiowy 2","Druga część treningu","zdrowy_kregoslup_trening2",2,1));
            workoutDao.insert(new Workout("Zdrowy kregosłup lędzwiowy 3","Trzecia część treningu","zdrowy_kregoslup_trening3",3,1));

            exerciseSetDao.insert(new ExerciseSet(1,2));
            exerciseSetDao.insert(new ExerciseSet(2,2));
            exerciseSetDao.insert(new ExerciseSet(3,2));
            exerciseSetDao.insert(new ExerciseSet(4,2));
            exerciseSetDao.insert(new ExerciseSet(5,2));
            exerciseSetDao.insert(new ExerciseSet(6,2));
            exerciseSetDao.insert(new ExerciseSet(7,2));
            exerciseSetDao.insert(new ExerciseSet(8,2));
            exerciseSetDao.insert(new ExerciseSet(9,2));
            exerciseSetDao.insert(new ExerciseSet(10,2));
            exerciseSetDao.insert(new ExerciseSet(11,2));
            exerciseSetDao.insert(new ExerciseSet(12,2));

            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,1));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,2));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,3));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,4));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(2,5));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(2,6));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(2,7));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(2,8));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(3,9));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(3,10));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(3,11));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(3,12));

            exerciseDao.insert(new Exercise("Odwodzenie ręki","Ćwiczenie rozluźniące staw nadgarstka","Pozycja wyjściowa: pozycja siedząca, przedramię spoczywa na stole w pozycji nawrócenia.\n" +
                    "Ruch: \n* Wykonaj odwodzenie łokciowe i promieniowe ręki.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do końca czasu”\n","cw_9",2));
            exerciseDao.insert(new Exercise("Kręcenie dłońmi","Ćwiczenie nadgarstka","Pozycja wyjściowa: pozycja siedząca, kończyna górna swobodna, niepodparta.\n" +
                    "Ruch: \n* Wykonaj krążenie ręką.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do końca czasu”\n","cw_10",2));
            exerciseDao.insert(new Exercise("Zginanie palców","Ćwiczenie polegające na zginaniu palców drugą reką","Pozycja wyjściowa: pozycja siedząca, przedramię spoczywa na stole, ręka zgięta grzbietowo, podparta drugą ręką\n" +
                    "Ruch: \n* Zegnij i wyprostuj palce w stawach śródręczno-paliczkowych i międzypaliczkowych.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do końca czasu”\n","cw_11",2));
            exerciseDao.insert(new Exercise("Odwodzenie palców","Ćwiczenie nadgarstka","Pozycja wyjściowa: pozycja siedzącą, przedramię spoczywa na stole w nawróceniu.\n" +
                    "Ruch: \n* Wykonaj odwodzenie i przywodzenie palców.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do konca czasu”\n","cw_12",2));

            exerciseDao.insert(new Exercise("Przywodzenie kciuka.","Ćwiczenie rozluźniące kcuika","Pozycja wyjściowa: pozycja siedząca, ręka spoczywa na stole w pozycji zerowej.\n" +
                    "Ruch: \n* Wykonaj odwodzenie łokciowe i promieniowe ręki.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do końca czasu”\n","cw_13",1));
            exerciseDao.insert(new Exercise("Przeciwwstawianie kciuka.","Ćwiczenie polegające na odprowadzaniu kciuka na zewnątrz","Pozycja wyjściowa: pozycja siedząca, ręka spoczywa na stole w pozycji zerowej.\n" +
                    "Ruch: \n* Zbliż i oddal od siebie kciuk i palec mały.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do końca czasu”\n","cw_14",2));
            exerciseDao.insert(new Exercise("Wyprost palców","Ćwiczenie polegające na prostowaniu palców","Pozycja wyjściowa: wyprost palców\n" +
                    "Ruch: \n* Napnij i utrzymaj napięcie mięśni przy wyprostowanych palcach przez czas od 5 do 10 sekund.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 5-10 razy do końca czasu”\n","cw_15",3));
            exerciseDao.insert(new Exercise("Ręce splecione","Ćwiczenie nadgarstka","Pozycja wyjściowa: ręce splecione.\n" +
                    "Ruch: \n* Zegnij splecione ręce w obu nadgarstkach\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do konca czasu”\n","cw_16",3));

            exerciseDao.insert(new Exercise("Ręce ułożone w diament","Ćwiczenie nadgarstka polegające na rozciągnęciu przyśrodkowej części rąk","Pozycja wyjściowa: ręce częścią dłoniową spoczywają na podłożu.\n" +
                    "Ruch: \n* Rozciągnij przyśrodkowe części rąk.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do konca czasu”\n","cw_17",3));
            exerciseDao.insert(new Exercise("Ręce na bandażu","Ćwiczenie nadgarstka polegające na rozciągnęciu przyśrodkowej części rąk","Pozycja wyjściowa: ręka oparta na bandażu elastycznym.\n" +
                    "Ruch: \n* Rozwiń i zwiń bandaż częścią dłoniową.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do konca czasu”\n","cw_18",3));
            exerciseDao.insert(new Exercise("Ręce na piłce","Ćwiczenie nadgarstka polegające na rozciągnęciu przyśrodkowej części rąk","Pozycja wyjściowa: ręka oparta na piłce.\n" +
                    "Ruch: \n* Przetocz piłkę częścią dłoniową ręki.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do konca czasu”\n","cw_19",3));

            exerciseDao.insert(new Exercise("Ręce na piłce 2","Ćwiczenie nadgarstka polegające na rozciągnęciu przyśrodkowej części rąk","Pozycja wyjściowa: ręka oparta na piłce.\n" +
                    "Ruch: \n* Ściśnij piłkę częścią dłoniową ręki.\n" +
                    "* Oddychaj regularnie.\n" +
                    "* Powtórz 10 razy do konca czasu”\n","cw_20",3));

            workoutDao.insert(new Workout("Zdrowy nadgarstek 1","Pierwsza cześć treningu","zdrowy_nadgarstek1",1,2));
            workoutDao.insert(new Workout("Zdrowy nadgarstek 2","Druga cześć treningu","zdrowy_nadgarstek2",2,2));
            workoutDao.insert(new Workout("Zdrowy nadgarstek 3","Trzecia cześć treningu","zdrowy_nadgarstek3",3,2));

            exerciseSetDao.insert(new ExerciseSet(13,2));
            exerciseSetDao.insert(new ExerciseSet(14,2));
            exerciseSetDao.insert(new ExerciseSet(15,2));
            exerciseSetDao.insert(new ExerciseSet(16,2));
            exerciseSetDao.insert(new ExerciseSet(17,2));
            exerciseSetDao.insert(new ExerciseSet(18,2));
            exerciseSetDao.insert(new ExerciseSet(19,2));
            exerciseSetDao.insert(new ExerciseSet(20,2));
            exerciseSetDao.insert(new ExerciseSet(21,2));
            exerciseSetDao.insert(new ExerciseSet(22,2));
            exerciseSetDao.insert(new ExerciseSet(23,2));
            exerciseSetDao.insert(new ExerciseSet(24,2));

            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(4,13));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(4,14));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(4,15));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(4,16));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(5,17));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(5,18));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(5,19));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(5,20));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(6,21));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(6,22));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(6,23));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(6,24));

            exerciseDao.insert(new Exercise("Ruchomość stawu","Ćwiczenie zwiększające ruchomość stawu kolanowego.","Pozycja wyjściowa: leżenie tyłem, kończyny dolne wyprostowane, kończyny górne wzdłuż tułowia.\n" +
                    "Ruch: \n* Wykonaj zgięcie jednej kończyny dolnej w stawie kolanowym przesuń piętą po podłożu w kierunku pośladka.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_21",1));
            exerciseDao.insert(new Exercise("Przednia strona uda","Ćwiczenie rozciągające mięśnie przedniej strony uda i powiększające zakres ruchu zgięcia kolana.","Pozycja wyjściowa: leżenie przodem, kończyny dolne wyprostowane.\n" +
                    "Ruch: \n* Zegnij kończynę dolną w stawie kolanowym, kończyną górną przytrzymujemy stopę, piętą dociskając do pośladka. Przytrzymaj 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_22",1));
            exerciseDao.insert(new Exercise("Mięsień czworogłowy","Ćwiczenie wzmacniające mięsień czworogłowy i zwiększające ruchomość w stawach kolanowych.","Pozycja wyjściowa: siad prosty na leżance, kończyny dolne zgięte w stawie kolanowym pod kątem prostym, podudzia zwisają poza leżanką. Stopa jednej kończyny dolnej znajduje się pod stopą drugiej kończyny.\n" +
                    "Ruch: \n* Wykonaj wyprost kończyny dolnej w stawie kolanowym, stopa drugiej kończyny znajdująca się nad pierwszą oporuje ruch wyprostu kończyny dolnej znajdującej się niżej. Przytrzymaj 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_23",1));
            exerciseDao.insert(new Exercise("Zginacze kolan","Ćwiczenie rozciągające mięśnie zginacze stawu kolanowego oraz zapobiegające przykurczowi zgięciowemu stawu kolanowego.","Pozycja wyjściowa: leżenie przodem, ręce splecione pod brodą, staw kolanowy i podudzia poza leżanką, kończyna dolna wyprostowana spoczywa na drugiej kończynie.\n" +
                    "Ruch: \n* Wykonaj wyprost w stawie kolanowym.\n" +
                    "* Kończyna znajdująca się na górze wspomaga wyprost. Przytrzymaj 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n"+
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_24",1));

            exerciseDao.insert(new Exercise("Zakres zgiecia","Ćwiczenie zwiększające zakres zgięcia stawu kolanowego.","Pozycja wyjściowa: leżenie przodem. Kończyny dolne wyprostowane w stawach kolanowych, stopa jednej kończyny spoczywa na drugiej kończynie.\n" +
                    "Ruch: \n* Wykonaj zgięcie stawów kolanowych. Stopa kończyny dolnej znajdującej się bliżej leżanki(na zdjęciu prawa) dociska drugą kończynę (lewą), pogłębiając zgięcie. Przytrzymaj w pozycji maksymalnego zgięcia 5 sekund.\n" +
                    "* Rozluźnij.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_25",2));
            exerciseDao.insert(new Exercise("Grupa tylnia uda","Rozciąganie grupy tylnej uda –  mięśni kulszowo-goleniowych.","Pozycja wyjściowa: stanie bokiem do leżanki, stołu. Kończyna dolna bliższa leżanki spoczywa na niej. Kończyny górne wzdłuż tułowia.\n" +
                    "Ruch: \n* Wykonaj zgięcie grzbietowe stopy i utrzymujemy wyprost stawu kolanowego 5 sekund (bez odczucia bólu w grupie tylnej uda).\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_26",2));
            exerciseDao.insert(new Exercise("Pośladki","Wzmacnianie mięśni pośladkowych i mięśni grupy tylnej uda.","Pozycja wyjściowa: leżenie przodem, wałek pod brzuchem, jedna kończyna dolna zgięta w stawie kolanowym do kąta prostego, druga wyprostowana spoczywa na leżance.\n" +
                    "Ruch: \n* Unieś kończynę dolną zgiętą w stawie kolanowym około 5 cm nad podłoże. Druga kończyna spoczywa na leżance. Przytrzymaj w tej pozycji 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_27",2));
            exerciseDao.insert(new Exercise("Odwodziciele uda","Wzmacniające mięśnie odwodziciele uda.","Pozycja wyjściowa: leżenie tyłem, kończyny dolne wyprostowane, w połowie uda przewiązane elastyczną taśmą, kończyny górne wzdłuż tułowia.\n" +
                    "Ruch: \n* Wykonaj odwodzenie kończyn dolnych przy równoczesnym rozciąganiu taśmy. Przytrzymaj 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_28",2));

            exerciseDao.insert(new Exercise("Przywodziciele uda","Wzmacniające mięśnie przywodziciele uda oraz poprawiające ruchomość w stawach kolanowych.","Pozycja wyjściowa: leżenie tyłem, kończyny górne wzdłuż tułowia, kończyny dolne wyprostowane, pomiędzy stawami kolanowymi umieszczona piłka.\n" +
                    "Ruch: \n* Wykonaj zginanie stawów kolanowych z przesuwaniem stóp po podłożu, z jednoczesnym ściskaniem piłki. Pozycję utrzymaj 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_29",3));
            exerciseDao.insert(new Exercise("Przednia strona uda","Wzmacniające mięśnie przedniej strony uda – mięsień czworogłowy uda.","Pozycja wyjściowa: leżenie tyłem, kończyny górne wzdłuż tułowia. Kończyny dolne wyprostowane,pod stawami kolanowymi umieszczamy wałek lub kocyk.\n" +
                    "Ruch: \n* Unieś podudzie około 5 cm nad leżankę z jednoczesnym zgięciem grzbietowym stopy i wciskaniem stawu kolanowego w wałek.\n" +
                    "* Przytrzymaj 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_30",3));
            exerciseDao.insert(new Exercise("Odwodziciele uda","Ćwiczenia wzmacniające mięśnie odwodzące udo.","Pozycja wyjściowa: leżenie na boku. Kończyny dolne zgięte w stawach kolanowych pod kątem prostym, przewiązane taśmą elastyczną w połowie uda.\n" +
                    "Ruch: \n* Kończynę dolną odwódź (unieś do góry) z równoczesnym napięciem taśmy. Przytrzymaj przez 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_31",3));
            exerciseDao.insert(new Exercise("Trójgłowy łydki","Wzmacniające mięsień trójgłowy łydki i mięsień czworogłowy uda.","Pozycja wyjściowa: siad prosty, wałek umieszczony pod stawami kolanowymi, elastyczna taśma umieszczona pod stopą. Ręce trzymają taśmę.\n" +
                    "Ruch: \n* Wykonaj zgięcie podeszwowe stopy z równoczesnym podciągnięciem taśmy oraz wciskanie stawu kolanowego w wałek. Przytrzymaj 5 sekund.\n" +
                    "* Wróć do pozycji wyjściowej.\n" +
                    "* Powtórz ten ruch 5 razy do konca czasu”\n","cw_32",3));

            workoutDao.insert(new Workout("Zdrowe kolana 1","Pierwsza cześć treningu","zdrowe_kolana1",1,3));
            workoutDao.insert(new Workout("Zdrowe kolana 2","Druga cześć treningu","zdrowe_kolana2",2,3));
            workoutDao.insert(new Workout("Zdrowe kolana 3","Trzecia cześć treningu","zdrowe_kolana3",3,3));

            exerciseSetDao.insert(new ExerciseSet(25,2));
            exerciseSetDao.insert(new ExerciseSet(26,2));
            exerciseSetDao.insert(new ExerciseSet(27,2));
            exerciseSetDao.insert(new ExerciseSet(28,2));
            exerciseSetDao.insert(new ExerciseSet(29,2));
            exerciseSetDao.insert(new ExerciseSet(30,2));
            exerciseSetDao.insert(new ExerciseSet(31,2));
            exerciseSetDao.insert(new ExerciseSet(32,2));
            exerciseSetDao.insert(new ExerciseSet(33,2));
            exerciseSetDao.insert(new ExerciseSet(34,2));
            exerciseSetDao.insert(new ExerciseSet(35,2));
            exerciseSetDao.insert(new ExerciseSet(36,2));

            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(7,25));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(7,26));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(7,27));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(7,28));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(8,29));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(8,30));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(8,31));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(8,32));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(9,33));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(9,34));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(9,35));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(9,36));
            return null;
        }
    }
}
