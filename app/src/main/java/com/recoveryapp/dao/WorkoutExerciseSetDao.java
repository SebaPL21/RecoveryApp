package com.recoveryapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutExerciseSetCrossRef;

@Dao
public interface WorkoutExerciseSetDao {
    @Insert
    void insert(WorkoutExerciseSetCrossRef workoutExerciseSetCrossRef);
}
