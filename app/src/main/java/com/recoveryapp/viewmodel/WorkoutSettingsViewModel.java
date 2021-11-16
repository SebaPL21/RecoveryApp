package com.recoveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutExerciseSetCrossRef;
import com.recoveryapp.entities.WorkoutWithExerciseSets;
import com.recoveryapp.repositories.WorkoutRepository;

import java.util.List;

public class WorkoutSettingsViewModel extends AndroidViewModel {
    private WorkoutRepository workoutRepository;
    private LiveData<List<Workout>> workouts;

    public WorkoutSettingsViewModel(@NonNull Application application) {
        super(application);
        workoutRepository = new WorkoutRepository(application);
        workouts = workoutRepository.getWorkouts();
    }

    public LiveData<List<Workout>> getWorkouts() {
        return workouts;
    }

    public WorkoutWithExerciseSets getWorkoutWithExercise(long id){
        return workoutRepository.findByIdWithExerciseSet(id);
    }
    public List<WorkoutWithExerciseSets> getAllWorkoutsWithExerciseSets(){
        return workoutRepository.getAllWorkoutsWithExerciseSets();
    }
}
