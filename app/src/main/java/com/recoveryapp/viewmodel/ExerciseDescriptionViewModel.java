package com.recoveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.recoveryapp.entities.Exercise;
import com.recoveryapp.repositories.ExerciseRepository;

import java.util.List;

public class ExerciseDescriptionViewModel extends AndroidViewModel {
    private ExerciseRepository exerciseRepository;
    private LiveData<List<Exercise>> exerciseList;

    public ExerciseDescriptionViewModel(@NonNull Application application) {
        super(application);
        exerciseRepository = new ExerciseRepository(application);
        exerciseList = exerciseRepository.getAllExercises();
    }
    public LiveData<List<Exercise>> getExerciseList(){return exerciseList;}
    public Exercise getExerciseById(long id){
        return exerciseRepository.findById(id);
    }
}
