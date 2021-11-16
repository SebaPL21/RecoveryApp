package com.recoveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.recoveryapp.entities.Exercise;
import com.recoveryapp.repositories.ExerciseRepository;

import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {
    private ExerciseRepository exerciseRepository;
    private LiveData<List<Exercise>> exercises;

    public ExercisesViewModel(@NonNull Application application) {
        super(application);
        exerciseRepository = new ExerciseRepository(application);
        exercises = exerciseRepository.getAllExercises();
    }
    public LiveData<List<Exercise>> getAllExercises(){
        return exercises;
    }

}


