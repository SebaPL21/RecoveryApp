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
            categoryDao.insert(new Category("Kregosłup","Kregosłup to jedna z najważniejszych części ciała"));
            categoryDao.insert(new Category("Dłonie","Okolica ciała obejmująca powierzchnię przednią ręki"));

            exerciseDao.insert(new Exercise("Skłony ciała","Powolne zginanie ciała w pasie.","Kroki jak wykonać ćwiczenie","",1));
            exerciseDao.insert(new Exercise("Martwe ciagi","Opis ćwiczenia2","Kroki jak wykonać ćwiczenie2","",2));
            exerciseDao.insert(new Exercise("Wymachy nogami","Opis ćwiczenia3","Kroki jak wykonać ćwiczenie3","",3));
            exerciseDao.insert(new Exercise("Skip B","Opis ćwiczenia4","Kroki jak wykonać ćwiczenie4","",3));

            workoutDao.insert(new Workout("Zdrowy kregosłup","Opis","",1,1));

            exerciseSetDao.insert(new ExerciseSet(1,2));
            exerciseSetDao.insert(new ExerciseSet(2,2));
            exerciseSetDao.insert(new ExerciseSet(3,2));
            exerciseSetDao.insert(new ExerciseSet(4,2));

            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,1));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,2));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,3));
            workoutExerciseSetDao.insert(new WorkoutExerciseSetCrossRef(1,4));
            return null;
        }
    }
}
