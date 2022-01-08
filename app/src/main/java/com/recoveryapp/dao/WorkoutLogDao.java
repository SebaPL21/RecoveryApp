package com.recoveryapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.recoveryapp.entities.WorkoutLog;

import java.util.List;

@Dao
public interface WorkoutLogDao {
    @Insert
    void insert(WorkoutLog workoutLog);

    @Update
    void update(WorkoutLog workoutLog);

    @Delete
    void delete(WorkoutLog workoutLog);

   /* @Query("Select * FROM workout_log_table")
    LiveData<List<WorkoutLog>> getAllWorkoutLogs();
*/
    @Query("SELECT * FROM workout_log_table WHERE workoutLogId = :id")
    WorkoutLog findById(long id);

    @Query("Select * FROM workout_log_table")
    LiveData<List<WorkoutLog>> getAllWorkoutLogs();
}
