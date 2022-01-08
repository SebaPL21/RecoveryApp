package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.recoveryapp.R;
import com.recoveryapp.viewmodel.CategoryViewModel;
import com.recoveryapp.viewmodel.WorkoutViewModel;
import com.recoveryapp.adapters.WorkoutAdapter;
import com.recoveryapp.entities.Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkoutActivity extends AppCompatActivity {
    private WorkoutViewModel workoutViewModel;
    private CategoryViewModel categoryViewModel;
    public static final String EXTRA_CATEGORY_ID = "com.recoveryapp.activities.EXTRA_CATEGORY_ID";
    private String category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        /*Initialize data from intent*/
        Intent data = getIntent();
        category_id = data.getStringExtra(WorkoutActivity.EXTRA_CATEGORY_ID);
        /*Bottom menu on item selected*/
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
        //menu toolbar
        Toolbar toolbar = findViewById(R.id.topAppBar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.about_as:
                        startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                        overridePendingTransition(0, 0);
                }
                return true;
            }
        });

        /*RecyclerView implementation*/
        RecyclerView recyclerView = findViewById(R.id.workout_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        WorkoutAdapter adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);

        /*View model observer */

        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        workoutViewModel.getWorkouts().observe(this, new Observer<List<Workout>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<Workout> workouts) {
                long categoryId = Long.valueOf(category_id).longValue();
                List<Workout> list = new ArrayList<>();
                for (Workout x : workouts) {
                    if (x.getFk_categoryId() == categoryId) {
                        list.add(x);
                    }
                }
                adapter.setWorkoutList(list);
            }
        });

        /*On click listener*/
        adapter.setOnClickListener(new WorkoutAdapter.OnClickListener() {
            @Override
            public void onItemClick(Workout workout) {
                Intent intent = new Intent(getApplicationContext(), WorkoutSettingsActivity.class);
                intent.putExtra(WorkoutSettingsActivity.EXTRA_WORKOUT_ID,String.valueOf(workout.getWorkoutId()));
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
}