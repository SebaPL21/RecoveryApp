package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.recoveryapp.entities.Category;
import com.recoveryapp.fragments.ExercisesFragment;
import com.recoveryapp.fragments.HomeFragment;
import com.recoveryapp.fragments.ProfileFragment;
import com.recoveryapp.R;
import com.recoveryapp.fragments.WorkoutFragment;
import com.recoveryapp.viewmodel.CategoryViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CategoryViewModel categoryViewModel;

    final Fragment homeFragment = new HomeFragment();
    final Fragment exercisesFragment = new ExercisesFragment();
    final Fragment workoutFragment = new WorkoutFragment();
    final Fragment profileFragment = new ProfileFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test repository
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        categoryViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                System.out.println("Ilosc kateogir: "+categories.size());
            }
        });

        fragmentManager.beginTransaction().add(R.id.fragment_container, profileFragment, "4").hide(profileFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, workoutFragment, "3").hide(workoutFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container, exercisesFragment, "2").hide(exercisesFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fragment_container,homeFragment, "1").commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                        active = homeFragment;
                        return true;

                    case R.id.exercises:
                        fragmentManager.beginTransaction().hide(active).show(exercisesFragment).commit();
                        active = exercisesFragment;
                        return true;

                    case R.id.work:
                        fragmentManager.beginTransaction().hide(active).show(workoutFragment).commit();
                        active = workoutFragment;
                        return true;
                    case R.id.profile:
                        fragmentManager.beginTransaction().hide(active).show(profileFragment).commit();
                        active = profileFragment;
                        return true;
                }
                return false;
            }
        });
    }

}