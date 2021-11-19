package com.recoveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.entities.ExerciseSet;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutLog;
import com.recoveryapp.entities.WorkoutWithExerciseSets;
import com.recoveryapp.repositories.CategoryRepository;
import com.recoveryapp.repositories.ExerciseRepository;
import com.recoveryapp.repositories.ExerciseSetRepository;
import com.recoveryapp.repositories.WorkoutLogRepository;
import com.recoveryapp.repositories.WorkoutRepository;

import java.util.List;

public class StartWorkoutViewModel extends AndroidViewModel {
    private WorkoutRepository workoutRepository;
    private ExerciseSetRepository exerciseSetRepository;
    private ExerciseRepository exerciseRepository;
    private WorkoutLogRepository workoutLogRepository;

    public StartWorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutRepository = new WorkoutRepository(application);
        exerciseSetRepository = new ExerciseSetRepository(application);
        exerciseRepository = new ExerciseRepository(application);

    }
    public void insert(WorkoutLog workoutLog){
        workoutLogRepository.insert(workoutLog);}

    public WorkoutWithExerciseSets getWorkoutWithExercise(long id){
        return workoutRepository.findByIdWithExerciseSet(id);
    }
    public List<WorkoutWithExerciseSets> getAllWorkoutsWithExerciseSets(){
        return workoutRepository.getAllWorkoutsWithExerciseSets();
    }
    public Exercise getExerciseById(long id){
        return exerciseRepository.findById(id);
    }
    public ExerciseSet getExerciseSetById(long id){
        return exerciseSetRepository.findById(id);
    }
    public List<Long> getLastIds(){
        return exerciseSetRepository.getLastIds();
    }

}
