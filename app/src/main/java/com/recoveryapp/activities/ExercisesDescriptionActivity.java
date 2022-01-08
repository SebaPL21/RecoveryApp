package com.recoveryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.recoveryapp.R;
import com.recoveryapp.adapters.FragmentAdapter;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.viewmodel.ExerciseDescriptionViewModel;

public class ExercisesDescriptionActivity extends AppCompatActivity {
    private ExerciseDescriptionViewModel exercisesViewModel;
    public static final String Extra_Exercise_ID = "com.recoveryapp.activities.Extra_Exercise_ID";
    private String exercise_id;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_description);
        Intent data = getIntent();
        exercise_id = data.getStringExtra(ExercisesDescriptionActivity.Extra_Exercise_ID);
        System.out.println("id cwiczenia: "+exercise_id);
        tabLayout = findViewById(R.id.exercise_description_tab_view);
        viewPager2 = findViewById(R.id.view_pager);

        FragmentManager fm  = getSupportFragmentManager();
        adapter =  new FragmentAdapter(fm,getLifecycle());
        viewPager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Opis ćwiczenia"));
        tabLayout.addTab(tabLayout.newTab().setText("Jak wkonać ćwiczenie"));

        exercisesViewModel = new ViewModelProvider(this).get(ExerciseDescriptionViewModel.class);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

                System.out.println("------"+ exercise_id);
                overridePendingTransition(0,0);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
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
    }
    public Exercise getExecise(){
        Exercise exercise;
        Intent data = getIntent();
        exercise_id = data.getStringExtra(ExercisesDescriptionActivity.Extra_Exercise_ID);
        int exer = Integer.valueOf(exercise_id);
        exercise =exercisesViewModel.getExerciseById(exer);
       // System.out.println(exercise.getName());
        return exercise;
    }


}