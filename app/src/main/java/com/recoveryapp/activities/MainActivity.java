package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
import com.recoveryapp.adapters.CategoryAdapter;
import com.recoveryapp.adapters.HomeCategoryAdapter;
import com.recoveryapp.adapters.HomeWorkoutAdapter;
import com.recoveryapp.adapters.WorkoutAdapter;
import com.recoveryapp.entities.Category;
import com.recoveryapp.R;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.viewmodel.CategoryViewModel;
import com.recoveryapp.viewmodel.WorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CategoryViewModel categoryViewModel;
    private WorkoutViewModel workoutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.home);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.exercises:
                        startActivity(new Intent(getApplicationContext(),ExercisesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.work:
                        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
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

        /*RecyclerView implementation*/
        RecyclerView recyclerView = findViewById(R.id.home_category_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        HomeCategoryAdapter adapter = new HomeCategoryAdapter();
        recyclerView.setAdapter(adapter);

        /*View model observer */
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                adapter.setCategoryList(categories);
            }
        });
        /*On click listener*/
        adapter.setOnClickListener(new CategoryAdapter.OnClickListener() {
            @Override
            public void onItemClick(Category category) {
                Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.EXTRA_CATEGORY_ID,String.valueOf(category.getCategoryId()));
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        /*RecyclerView implementation*/
        RecyclerView workoutRecyclerView = findViewById(R.id.home_workout_recycler_view);
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        workoutRecyclerView.setHasFixedSize(true);

        HomeWorkoutAdapter workoutAdapter = new HomeWorkoutAdapter();
        workoutRecyclerView.setAdapter(workoutAdapter);

        /*View model observer */

        workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);

        workoutViewModel.getWorkouts().observe(this, new Observer<List<Workout>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<Workout> workouts) {
                workoutAdapter.setWorkoutList(workouts);
            }
        });

        /*On click listener*/
        workoutAdapter.setOnClickListener(new WorkoutAdapter.OnClickListener() {
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