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
import com.recoveryapp.adapters.CategoryAdapter;
import com.recoveryapp.R;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.viewmodel.CategoryViewModel;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
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
        RecyclerView recyclerView = findViewById(R.id.category_workout_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CategoryAdapter adapter = new CategoryAdapter();
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
    }
}