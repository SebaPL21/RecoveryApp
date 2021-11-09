package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.recoveryapp.R;

public class WorkoutActivity extends AppCompatActivity {
import com.recoveryapp.adapters.CategoryAdapter;
import com.recoveryapp.adapters.WorkoutAdapter;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.viewmodel.CategoryViewModel;
import com.recoveryapp.viewmodel.WorkoutViewModel;

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
                adapter.setWorkoutList(workouts.stream().filter((x) -> x.getFk_categoryId() == categoryId).collect(Collectors.toList()));
            }
        });

        /*On click listener*/
        adapter.setOnClickListener(new WorkoutAdapter.OnClickListener() {
            @Override
            public void onItemClick(Workout workout) {
                /*
                Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.EXTRA_CATEGORY_ID,String.valueOf(workout.getCategoryId()));
                startActivity(intent);
                overridePendingTransition(0,0);*/
            }
        });
    }
}