package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.recoveryapp.R;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.entities.ExerciseSet;
import com.recoveryapp.entities.WorkoutExerciseSetCrossRef;
import com.recoveryapp.entities.WorkoutWithExerciseSets;
import com.recoveryapp.viewmodel.WorkoutSettingsViewModel;

import java.util.List;

public class WorkoutSettingsActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "com.recoveryapp.activities.EXTRA_WORKOUT_ID";
    private String workout_id;
    private ViewFlipper viewFlipper;
    private Button prevButton;
    private Button nextButton;
    private int pageIndex;

    private TextView workoutName;

    private TextView exerciseName1;
    private TextView exerciseName2;
    private TextView exerciseName3;
    private TextView exerciseName4;

    private TextView exerciseDescription1;
    private TextView exerciseDescription2;
    private TextView exerciseDescription3;
    private TextView exerciseDescription4;

    private TextView seriesQuantity1;
    private TextView seriesQuantity2;
    private TextView seriesQuantity3;
    private TextView seriesQuantity4;

    private int series1 = 2;
    private int series2 = 2;
    private int series3 = 2;
    private int series4 = 2;


    private WorkoutSettingsViewModel workoutSettingsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_settings);

        workoutSettingsViewModel = new ViewModelProvider(this).get(WorkoutSettingsViewModel.class);

        /*Bottom Nav*/
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
        /*View flipper*/

        viewFlipper = findViewById(R.id.view_flipper_workout_settings);
        prevButton = findViewById(R.id.workout_settings_prev_button);
        nextButton = findViewById(R.id.workout_settings_next_button);
        prevButton.setBackgroundColor(Color.GRAY);



        /*Initialize data from intent*/
        Intent data = getIntent();
        workout_id = data.getStringExtra(WorkoutSettingsActivity.EXTRA_WORKOUT_ID);


        WorkoutWithExerciseSets workout = workoutSettingsViewModel.getWorkoutWithExercise(Long.valueOf(workout_id).longValue());

        ExerciseSet exerciseSet1 = workoutSettingsViewModel.getExerciseSetById(workout.exerciseSets.get(0).getExerciseSetId());
        ExerciseSet exerciseSet2 = workoutSettingsViewModel.getExerciseSetById(workout.exerciseSets.get(1).getExerciseSetId());
        ExerciseSet exerciseSet3 = workoutSettingsViewModel.getExerciseSetById(workout.exerciseSets.get(2).getExerciseSetId());
        ExerciseSet exerciseSet4 = workoutSettingsViewModel.getExerciseSetById(workout.exerciseSets.get(3).getExerciseSetId());

        Exercise exercise1 = workoutSettingsViewModel.getExerciseById(exerciseSet1.getFk_exerciseId());
        Exercise exercise2 = workoutSettingsViewModel.getExerciseById(exerciseSet2.getFk_exerciseId());
        Exercise exercise3 = workoutSettingsViewModel.getExerciseById(exerciseSet3.getFk_exerciseId());
        Exercise exercise4 = workoutSettingsViewModel.getExerciseById(exerciseSet4.getFk_exerciseId());

        /*Set name*/

        workoutName = findViewById(R.id.text_view_name_workout_settings);
        workoutName.setText(workout.workout.getName());

        exerciseName1 = findViewById(R.id.name_exercise_setting1);
        exerciseName2 = findViewById(R.id.name_exercise_setting2);
        exerciseName3 = findViewById(R.id.name_exercise_setting3);
        exerciseName4 = findViewById(R.id.name_exercise_setting4);

        exerciseDescription1 = findViewById(R.id.description_exercise_setting1);
        exerciseDescription2 = findViewById(R.id.description_exercise_setting2);
        exerciseDescription3 = findViewById(R.id.description_exercise_setting3);
        exerciseDescription4 = findViewById(R.id.description_exercise_setting4);

        seriesQuantity1 = findViewById(R.id.text_view_series1);
        seriesQuantity2 = findViewById(R.id.text_view_series2);
        seriesQuantity3 = findViewById(R.id.text_view_series3);
        seriesQuantity4 = findViewById(R.id.text_view_series4);

        exerciseName1.setText(exercise1.getName());
        exerciseName2.setText(exercise2.getName());
        exerciseName3.setText(exercise3.getName());
        exerciseName4.setText(exercise4.getName());

        exerciseDescription1.setText(exercise1.getDescription());
        exerciseDescription2.setText(exercise2.getDescription());
        exerciseDescription3.setText(exercise3.getDescription());
        exerciseDescription4.setText(exercise4.getDescription());
    }

    public void nextView(View view) {
        if (pageIndex == 4) {
            Toast.makeText(getApplicationContext(),
                    "Proszę kliknać rozpocznij trening", Toast.LENGTH_SHORT).show();
            nextButton.setBackgroundColor(Color.GRAY);
        } else {
            viewFlipper.setInAnimation(this, R.anim.slide_in_right);
            viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
            viewFlipper.showNext();
            pageIndex++;
            prevButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.dark_primary_color));
        }
        if (pageIndex == 4) {
            nextButton.setBackgroundColor(Color.GRAY);
        }
    }

    public void previousView(View view) {
        if (pageIndex == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please click next", Toast.LENGTH_SHORT).show();
            prevButton.setBackgroundColor(Color.GRAY);
        } else {
            viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
            viewFlipper.showPrevious();
            pageIndex--;
            nextButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.dark_primary_color));
        }
        if (pageIndex == 0) {
            prevButton.setBackgroundColor(Color.GRAY);
        }
    }

    public void increaseSeries1(View view) {
        if (series1 < 5) {
            series1++;
            seriesQuantity1.setText(String.valueOf(series1));
        }
    }

    public void decreaseSeries1(View view) {
        if (series1 > 1) {
            series1--;
            seriesQuantity1.setText(String.valueOf(series1));
        }
    }

    public void increaseSeries2(View view) {
        if (series2 < 5) {
            series2++;
            seriesQuantity2.setText(String.valueOf(series2));
        }
    }

    public void decreaseSeries2(View view) {
        if (series2 > 1) {
            series2--;
            seriesQuantity2.setText(String.valueOf(series2));
        }
    }

    public void increaseSeries3(View view) {
        if (series3 < 5) {
            series3++;
            seriesQuantity3.setText(String.valueOf(series3));
        }
    }

    public void decreaseSeries3(View view) {
        if (series3 > 1) {
            series3--;
            seriesQuantity3.setText(String.valueOf(series3));
        }
    }

    public void increaseSeries4(View view) {
        if (series4 < 5) {
            series4++;
            seriesQuantity4.setText(String.valueOf(series4));
        }
    }

    public void decreaseSeries4(View view) {
        if (series4 > 1) {
            series4--;
            seriesQuantity4.setText(String.valueOf(series4));
        }
    }
}