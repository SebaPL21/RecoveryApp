package com.recoveryapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.recoveryapp.RecoveryDatabase;
import com.recoveryapp.dao.WorkoutLogDao;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutLog;
import com.recoveryapp.repositories.WorkoutLogRepository;
import com.recoveryapp.repositories.WorkoutRepository;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    LiveData<List<WorkoutLog>> workoutLog;
    WorkoutLogRepository workoutLogRepository;
    WorkoutRepository workoutRepository;

    public ProfileViewModel(@NonNull Application application) { super(application);
       workoutLogRepository = new WorkoutLogRepository(application);
       workoutRepository = new WorkoutRepository(application);
       workoutLog = workoutLogRepository.getWorkoutLogs();
    }

    public LiveData<List<WorkoutLog>> getWorkoutLog() {
        return workoutLog;
    }
    public Workout getWorkouts(int id) {
        return workoutRepository.findById(id);
    }

}
