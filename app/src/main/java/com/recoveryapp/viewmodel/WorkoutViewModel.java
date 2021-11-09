package com.recoveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.repositories.CategoryRepository;
import com.recoveryapp.repositories.WorkoutRepository;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    private WorkoutRepository workoutRepository;
    private LiveData<List<Workout>> workouts;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutRepository = new WorkoutRepository(application);
        workouts = workoutRepository.getWorkouts();
    }

    public LiveData<List<Workout>> getWorkouts() {
        return workouts;
    }
}
