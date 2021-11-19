package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.recoveryapp.R;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.entities.ExerciseSet;
import com.recoveryapp.viewmodel.StartWorkoutViewModel;
import com.recoveryapp.viewmodel.WorkoutSettingsViewModel;

import java.util.List;

public class StartWorkoutActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "com.recoveryapp.activities.EXTRA_WORKOUT_ID";
    public static int EXERCISE_NUMBER = 1;
    private long exerciseTime = 20000;
    private long exerciseBreakTime = 10000;
    private int series = 1;
    private CountDownTimer countDownTimer;
    private CountDownTimer countDownBreakTimer;
    private StartWorkoutViewModel startWorkoutViewModel;
    private MediaPlayer mediaPlayer_background;
    private MediaPlayer mediaPlayer_start;
    private MediaPlayer mediaPlayer_break;
    private MediaPlayer mediaPlayer_nextExercise;
    private MediaPlayer mediaPlayer_lastExercise;
    private MediaPlayer mediaPlayer_lastSeries;
    private MediaPlayer mediaPlayer_startExercise;
    private MediaPlayer mediaPlayer_breakDude;

    public boolean isEndedExercise = false;

    private long exercise_set1_id;
    private long exercise_set2_id;
    private long exercise_set3_id;
    private long exercise_set4_id;

    private ExerciseSet exerciseSet1;
    private ExerciseSet exerciseSet2;
    private ExerciseSet exerciseSet3;
    private ExerciseSet exerciseSet4;

    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;
    private Exercise exercise4;

    private int seriesNumber1;
    private int seriesNumber2;
    private int seriesNumber3;
    private int seriesNumber4;


    private TextView textView_exerciseName;
    private TextView textView_steps;

    private Button button_nextExercise;

    private ProgressBar progressBar_workout;

    private TextView textView_countDownTimer;

    private TextView textView_seriesNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.work);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.exercises:
                        startActivity(new Intent(getApplicationContext(), ExercisesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.work:
                        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        startWorkoutViewModel = new ViewModelProvider(this).get(StartWorkoutViewModel.class);
        /*Initialize components*/
        textView_exerciseName = findViewById(R.id.text_view_exercise_name_in_workout);
        textView_steps = findViewById(R.id.text_view_how_to_do_workout);
        button_nextExercise = findViewById(R.id.button_next_exercise);
        progressBar_workout = findViewById(R.id.progressBar_workout);
        textView_countDownTimer = findViewById(R.id.text_view_count_down_timer);
        textView_seriesNumber = findViewById(R.id.text_view_series_number);
        /*Initialize data from intent*/
        Intent data = getIntent();

        List<Long> exerciseSetIdsList = startWorkoutViewModel.getLastIds();
        exercise_set1_id = exerciseSetIdsList.get(3);
        exercise_set2_id = exerciseSetIdsList.get(2);
        exercise_set3_id = exerciseSetIdsList.get(1);
        exercise_set4_id = exerciseSetIdsList.get(0);

        /*Inital media player*/
        mediaPlayer_background = MediaPlayer.create(
                this, R.raw.parostatek);
        mediaPlayer_start = MediaPlayer.create(
                this, R.raw.zaczynamy);
        mediaPlayer_break = MediaPlayer.create(
                this, R.raw.przerwa);
        mediaPlayer_nextExercise = MediaPlayer.create(
                this, R.raw.nastepne);
        mediaPlayer_lastExercise = MediaPlayer.create(
                this, R.raw.ostatnie_cwiczenie);
        mediaPlayer_lastSeries = MediaPlayer.create(
                this, R.raw.ostatnia_seria_cwiczenia);
        mediaPlayer_startExercise = MediaPlayer.create(
                this, R.raw.zaczynamy_cwiczenie);
        mediaPlayer_breakDude = MediaPlayer.create(
                this, R.raw.odpocznij_ziomeku);
        /* fetch record from db */
        exerciseSet1 = startWorkoutViewModel.getExerciseSetById(exercise_set1_id);
        exerciseSet2 = startWorkoutViewModel.getExerciseSetById(exercise_set2_id);
        exerciseSet3 = startWorkoutViewModel.getExerciseSetById(exercise_set3_id);
        exerciseSet4 = startWorkoutViewModel.getExerciseSetById(exercise_set4_id);

        exercise1 = startWorkoutViewModel.getExerciseById(exerciseSet1.getFk_exerciseId());
        exercise2 = startWorkoutViewModel.getExerciseById(exerciseSet2.getFk_exerciseId());
        exercise3 = startWorkoutViewModel.getExerciseById(exerciseSet3.getFk_exerciseId());
        exercise4 = startWorkoutViewModel.getExerciseById(exerciseSet4.getFk_exerciseId());

        /*Initialize first exercise*/
        String exerciseName = "";
        String steps = "";

        exerciseName = exercise1.getName();
        steps = exercise1.getSteps();

        textView_exerciseName.setText(exerciseName);
        textView_steps.setText(steps);
        progressBar_workout.setProgress(1);
    }

    public void nextExercise(View view) {
        if (isEndedExercise) {
            EXERCISE_NUMBER++;
            series = 1;

            String exerciseName = "";
            String steps = "";

            switch (EXERCISE_NUMBER) {
                case 2:
                    exerciseName = exercise2.getName();
                    steps = exercise2.getSteps();
                    progressBar_workout.setProgress(25);
                    break;
                case 3:
                    exerciseName = exercise3.getName();
                    steps = exercise3.getSteps();
                    progressBar_workout.setProgress(50);
                    break;
                case 4:
                    exerciseName = exercise4.getName();
                    steps = exercise4.getSteps();
                    progressBar_workout.setProgress(75);
                    break;
                case 5:
                    break;
            }
            textView_exerciseName.setText(exerciseName);
            textView_steps.setText(steps);

            if (EXERCISE_NUMBER == 4) {
                button_nextExercise.setText("Zakończ trening");
            }
            if (EXERCISE_NUMBER == 5) {
                EXERCISE_NUMBER = 1;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            isEndedExercise = false;
            button_nextExercise.setText("Rozpocznij kolejne ćwiczenie");
            //tutaj rozpocznij kolejne cwiczenie
            mediaPlayer_nextExercise.start();
        } else {
            seriesNumber1 = exerciseSet1.getSet();
            seriesNumber2 = exerciseSet2.getSet();
            seriesNumber3 = exerciseSet3.getSet();
            seriesNumber4 = exerciseSet4.getSet();

            textView_seriesNumber.setText("Seria "+series);
            //tutaj
            mediaPlayer_background.start();

            startExercise();

        }
    }

    public void startExercise() {
        textView_countDownTimer.setVisibility(View.VISIBLE);
        button_nextExercise.setEnabled(false);
        button_nextExercise.setText("Trening");
        //tutaj rozpocznam trening
        if(series-1 == seriesNumber1 ||series-1 == seriesNumber2 || series-1 == seriesNumber3 || series-1 == seriesNumber4){
            mediaPlayer_lastSeries.start();
        }else{
            mediaPlayer_start.start();
        }

        startTimer(exerciseTime);
    }

    public void startBreak() {
        textView_countDownTimer.setVisibility(View.VISIBLE);
        button_nextExercise.setText("Przerwa");
        button_nextExercise.setEnabled(false);
        //tutaj przerwa
        mediaPlayer_breakDude.start();
        startBreakTimer(exerciseBreakTime);
    }

    public void startTimer(long time) {
        countDownTimer = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView_countDownTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textView_countDownTimer.setText("0");
                startBreak();
            }
        }.start();
    }

    public void startBreakTimer(long time) {
        countDownBreakTimer = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView_countDownTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                if(series == seriesNumber1 && EXERCISE_NUMBER == 1){
                    button_nextExercise.setText("Następne ćwiczenie");
                    button_nextExercise.setEnabled(true);
                    isEndedExercise = true;
                }
                else if(series == seriesNumber2 && EXERCISE_NUMBER == 2){
                    button_nextExercise.setText("Następne ćwiczenie");
                    button_nextExercise.setEnabled(true);
                    isEndedExercise = true;
                }
                else if(series == seriesNumber3 && EXERCISE_NUMBER == 3){
                    button_nextExercise.setText("Następne ćwiczenie");
                    button_nextExercise.setEnabled(true);
                    isEndedExercise = true;
                }
                else if(series == seriesNumber4 && EXERCISE_NUMBER == 4){
                    button_nextExercise.setText("Następne ćwiczenie");
                    button_nextExercise.setEnabled(true);
                    isEndedExercise = true;
                    //tutaj
                    mediaPlayer_lastExercise.start();
                }
                else{
                textView_countDownTimer.setText("0");
                button_nextExercise.setText("Trening");
                series++;
                textView_seriesNumber.setText("Seria "+series);
                startExercise();
                }
            }
        }.start();
    }
}