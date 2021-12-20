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
import com.recoveryapp.adapters.ProfileAdapter;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.viewmodel.CategoryViewModel;
import com.recoveryapp.viewmodel.ExercisesViewModel;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
private  ExercisesViewModel exercisesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.profile);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        System.out.println("-------------------"+item.getItemId());
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
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
                        return true;
                }
                return false;
            }
        });
        RecyclerView recycler = findViewById(R.id.activityProfile);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);


        ProfileAdapter adapter = new ProfileAdapter();
        recycler.setAdapter(adapter);

        exercisesViewModel = new ViewModelProvider(this).get(ExercisesViewModel.class);

        exercisesViewModel.getAllExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                adapter.setExercises(exercises);
            }
        });

    }
}