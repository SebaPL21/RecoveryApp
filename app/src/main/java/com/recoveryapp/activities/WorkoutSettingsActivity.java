package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    private TextView textView_workoutName;

    private TextView textView_exerciseName1;
    private TextView textView_exerciseName2;
    private TextView textView_exerciseName3;
    private TextView textView_exerciseName4;

    private TextView textView_exerciseDescription1;
    private TextView textView_exerciseDescription2;
    private TextView textView_exerciseDescription3;
    private TextView textView_exerciseDescription4;

    private TextView textView_seriesQuantity1;
    private TextView textView_seriesQuantity2;
    private TextView textView_seriesQuantity3;
    private TextView textView_seriesQuantity4;

    private TextView textView_summaryItemName1;
    private TextView textView_summaryItemName2;
    private TextView textView_summaryItemName3;
    private TextView textView_summaryItemName4;

    private TextView textView_summaryItemSeriesNumber1;
    private TextView textView_summaryItemSeriesNumber2;
    private TextView textView_summaryItemSeriesNumber3;
    private TextView textView_summaryItemSeriesNumber4;

    private ImageView imageView_exercise_set_1;
    private ImageView imageView_exercise_set_2;
    private ImageView imageView_exercise_set_3;
    private ImageView imageView_exercise_set_4;

    private int series1 = 2;
    private int series2 = 2;
    private int series3 = 2;
    private int series4 = 2;

    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;
    private Exercise exercise4;


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

        exercise1 = workoutSettingsViewModel.getExerciseById(exerciseSet1.getFk_exerciseId());
        exercise2 = workoutSettingsViewModel.getExerciseById(exerciseSet2.getFk_exerciseId());
        exercise3 = workoutSettingsViewModel.getExerciseById(exerciseSet3.getFk_exerciseId());
        exercise4 = workoutSettingsViewModel.getExerciseById(exerciseSet4.getFk_exerciseId());

        /*Set name*/

        textView_workoutName = findViewById(R.id.text_view_name_workout_settings);
        textView_workoutName.setText(workout.workout.getName());

        textView_exerciseName1 = findViewById(R.id.name_exercise_setting1);
        textView_exerciseName2 = findViewById(R.id.name_exercise_setting2);
        textView_exerciseName3 = findViewById(R.id.name_exercise_setting3);
        textView_exerciseName4 = findViewById(R.id.name_exercise_setting4);

        textView_exerciseDescription1 = findViewById(R.id.description_exercise_setting1);
        textView_exerciseDescription2 = findViewById(R.id.description_exercise_setting2);
        textView_exerciseDescription3 = findViewById(R.id.description_exercise_setting3);
        textView_exerciseDescription4 = findViewById(R.id.description_exercise_setting4);

        textView_seriesQuantity1 = findViewById(R.id.text_view_series1);
        textView_seriesQuantity2 = findViewById(R.id.text_view_series2);
        textView_seriesQuantity3 = findViewById(R.id.text_view_series3);
        textView_seriesQuantity4 = findViewById(R.id.text_view_series4);

        textView_exerciseName1.setText(exercise1.getName());
        textView_exerciseName2.setText(exercise2.getName());
        textView_exerciseName3.setText(exercise3.getName());
        textView_exerciseName4.setText(exercise4.getName());

        textView_exerciseDescription1.setText(exercise1.getDescription());
        textView_exerciseDescription2.setText(exercise2.getDescription());
        textView_exerciseDescription3.setText(exercise3.getDescription());
        textView_exerciseDescription4.setText(exercise4.getDescription());

        /*Summary initalizaction*/
        textView_summaryItemName1 = findViewById(R.id.text_view_item_summary_name1);
        textView_summaryItemName2 = findViewById(R.id.text_view_item_summary_name2);
        textView_summaryItemName3 = findViewById(R.id.text_view_item_summary_name3);
        textView_summaryItemName4 = findViewById(R.id.text_view_item_summary_name4);

        textView_summaryItemSeriesNumber1 = findViewById(R.id.text_view_item_summary_series1);
        textView_summaryItemSeriesNumber2 = findViewById(R.id.text_view_item_summary_series2);
        textView_summaryItemSeriesNumber3 = findViewById(R.id.text_view_item_summary_series3);
        textView_summaryItemSeriesNumber4 = findViewById(R.id.text_view_item_summary_series4);

        /*Image view*/
        imageView_exercise_set_1 = findViewById(R.id.image_exercise_setting1);
        imageView_exercise_set_2 = findViewById(R.id.image_exercise_setting2);
        imageView_exercise_set_3 = findViewById(R.id.image_exercise_setting3);
        imageView_exercise_set_4 = findViewById(R.id.image_exercise_setting4);

        /*settig image*/
        String iconName = exercise1.getImagePath();
        int resID = getResources().getIdentifier(iconName, "drawable", getPackageName());
        imageView_exercise_set_1.setImageResource(resID);

        iconName = exercise2.getImagePath();
        resID = getResources().getIdentifier(iconName, "drawable", getPackageName());
        imageView_exercise_set_2.setImageResource(resID);

        iconName = exercise3.getImagePath();
        resID = getResources().getIdentifier(iconName, "drawable", getPackageName());
        imageView_exercise_set_3.setImageResource(resID);

        iconName = exercise4.getImagePath();
        resID = getResources().getIdentifier(iconName, "drawable", getPackageName());
        imageView_exercise_set_4.setImageResource(resID);
    }

    public void nextView(View view) {
        if (pageIndex == 4) {
            Toast.makeText(getApplicationContext(),
                    "Proszę kliknać rozpocznij trening", Toast.LENGTH_SHORT).show();
            nextButton.setBackgroundColor(Color.GRAY);
        } else {
            /*Seting data to summary*/
            textView_summaryItemName1.setText(exercise1.getName());
            textView_summaryItemName2.setText(exercise2.getName());
            textView_summaryItemName3.setText(exercise3.getName());
            textView_summaryItemName4.setText(exercise4.getName());

            textView_summaryItemSeriesNumber1.setText("Ilość seri "+series1);
            textView_summaryItemSeriesNumber2.setText("Ilość seri "+series2);
            textView_summaryItemSeriesNumber3.setText("Ilość seri "+series3);
            textView_summaryItemSeriesNumber4.setText("Ilość seri "+series4);


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
                    "Proszę kliknąć następny", Toast.LENGTH_SHORT).show();
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
            textView_seriesQuantity1.setText(String.valueOf(series1));
        }
    }

    public void decreaseSeries1(View view) {
        if (series1 > 1) {
            series1--;
            textView_seriesQuantity1.setText(String.valueOf(series1));
        }
    }

    public void increaseSeries2(View view) {
        if (series2 < 5) {
            series2++;
            textView_seriesQuantity2.setText(String.valueOf(series2));
        }
    }

    public void decreaseSeries2(View view) {
        if (series2 > 1) {
            series2--;
            textView_seriesQuantity2.setText(String.valueOf(series2));
        }
    }

    public void increaseSeries3(View view) {
        if (series3 < 5) {
            series3++;
            textView_seriesQuantity3.setText(String.valueOf(series3));
        }
    }

    public void decreaseSeries3(View view) {
        if (series3 > 1) {
            series3--;
            textView_seriesQuantity3.setText(String.valueOf(series3));
        }
    }

    public void increaseSeries4(View view) {
        if (series4 < 5) {
            series4++;
            textView_seriesQuantity4.setText(String.valueOf(series4));
        }
    }

    public void decreaseSeries4(View view) {
        if (series4 > 1) {
            series4--;
            textView_seriesQuantity4.setText(String.valueOf(series4));
        }
    }
    public void startWork(View view){
        workoutSettingsViewModel.insert(new ExerciseSet(exercise1.getExerciseId(),series1));
        workoutSettingsViewModel.insert(new ExerciseSet(exercise2.getExerciseId(),series2));
        workoutSettingsViewModel.insert(new ExerciseSet(exercise3.getExerciseId(),series3));
        workoutSettingsViewModel.insert(new ExerciseSet(exercise4.getExerciseId(),series4));

        List<Long> lastInsertedIds = workoutSettingsViewModel.getLastIds();

        Intent intent = new Intent(getApplicationContext(), StartWorkoutActivity.class);
        intent.putExtra(StartWorkoutActivity.EXTRA_WORKOUT_ID,String.valueOf(String.valueOf(workout_id)));
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}