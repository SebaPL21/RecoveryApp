package com.recoveryapp.activities;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.recoveryapp.R;

public class WorkoutSettingsActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "com.recoveryapp.activities.EXTRA_WORKOUT_ID";
    private String workout_id;
    private ViewFlipper viewFlipper;
    private Button prevButton;
    private Button nextButton;
    private int pageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_settings);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.work);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.exercises:
                        startActivity(new Intent(getApplicationContext(),ExercisesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.work:
                        startActivity(new Intent(getApplicationContext(),CategoryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        viewFlipper = findViewById(R.id.view_flipper_workout_settings);
        prevButton = findViewById(R.id.workout_settings_prev_button);
        nextButton = findViewById(R.id.workout_settings_next_button);
        prevButton.setBackgroundColor(Color.GRAY);

        /*Initialize data from intent*/
        Intent data = getIntent();
        workout_id = data.getStringExtra(WorkoutSettingsActivity.EXTRA_WORKOUT_ID);
        System.out.println("WorkoutId passed from intent:"+workout_id);
    }

    public void nextView(View view) {
        if (pageIndex == 1) {
            Toast.makeText(getApplicationContext(),
                    "Proszę kliknać rozpocznij trening", Toast.LENGTH_SHORT).show();
            nextButton.setBackgroundColor(Color.GRAY);
        } else {
            viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
            viewFlipper.showNext();
            pageIndex++;
            prevButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.dark_primary_color));
        }
        if (pageIndex == 1) {
            nextButton.setBackgroundColor(Color.GRAY);
        }
    }

    public void previousView(View view) {
        if (pageIndex == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please click next", Toast.LENGTH_SHORT).show();
            prevButton.setBackgroundColor(Color.GRAY);
        } else {
            viewFlipper.setInAnimation(this, android.R.anim.slide_out_right);
            viewFlipper.setOutAnimation(this,android.R.anim.slide_in_left);
            viewFlipper.showPrevious();
            pageIndex--;
            nextButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.dark_primary_color));
        }
        if (pageIndex == 0) {
            prevButton.setBackgroundColor(Color.GRAY);}
    }
}