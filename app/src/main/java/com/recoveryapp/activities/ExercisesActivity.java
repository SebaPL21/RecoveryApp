package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.recoveryapp.R;
import com.recoveryapp.adapters.ExercisesAdapter;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.viewmodel.ExercisesViewModel;

import java.util.List;

public class ExercisesActivity extends AppCompatActivity {
    private ExercisesViewModel exercisesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
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

        RecyclerView recyclerView = findViewById(R.id.exercises_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ExercisesAdapter adapter = new ExercisesAdapter();
        recyclerView.setAdapter(adapter);

        /*View model observer */
        exercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);

        exercisesViewModel.getAllExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                adapter.setExercises(exercises);
            }
        });
        adapter.setOnClickListener(new ExercisesAdapter.OnClickListener() {
            @Override
            public void onItemClick(Exercise exercise) {
                Intent intent = new Intent(getApplicationContext(), ExercisesDescriptionActivity.class);
                intent.putExtra(ExercisesDescriptionActivity.Extra_Exercise_ID,String.valueOf(exercise.getExerciseId()));
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

    }
}