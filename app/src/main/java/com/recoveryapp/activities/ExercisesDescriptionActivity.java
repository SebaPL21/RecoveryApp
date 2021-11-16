package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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
import com.recoveryapp.adapters.ExerciseDescriptionAdapter;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.viewmodel.ExerciseDescriptionViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExercisesDescriptionActivity extends AppCompatActivity {
    private ExerciseDescriptionViewModel exercisesViewModel;
    public static final String Extra_Exercise_ID = "com.recoveryapp.activities.Extra_Exercise_ID";
    private String exercise_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_description);

        Intent data = getIntent();
        exercise_id = data.getStringExtra(ExercisesDescriptionActivity.Extra_Exercise_ID);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.exercises);
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

        RecyclerView recyclerView = findViewById(R.id.exercise_description);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ExerciseDescriptionAdapter adapter = new ExerciseDescriptionAdapter();
        recyclerView.setAdapter(adapter);

        exercisesViewModel = new ViewModelProvider(this).get(ExerciseDescriptionViewModel.class);

        exercisesViewModel.getExerciseList().observe(this, new Observer<List<Exercise>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<Exercise> exercises) {
                long exerciseid = Long.valueOf(exercise_id).longValue();
                List<Exercise> list = new ArrayList<>();
                for(Exercise x: exercises){
                    if(x.getExerciseId()==exerciseid){
                        list.add(x);
                    }
                }
                adapter.setExercisesDesriptionList(list);
            }
        });
        adapter.setOnClickListener(new ExerciseDescriptionAdapter.OnClickListener(){

            @Override
            public void onItemClick(Exercise exercise) {
               /*
                Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
                intent.putExtra(WorkoutActivity.EXTRA_CATEGORY_ID,String.valueOf(workout.getCategoryId()));
                startActivity(intent);
                overridePendingTransition(0,0);*/
            }
        });
    }
}