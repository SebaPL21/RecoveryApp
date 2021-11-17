package com.recoveryapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.recoveryapp.entities.ExerciseSet;

import java.util.List;

@Dao
public interface ExerciseSetDao {
    @Insert
    void insert(ExerciseSet exerciseSet);

    @Update
    void update(ExerciseSet exerciseSet);

    @Delete
    void delete(ExerciseSet exerciseSet);

    @Query("Select * FROM exercise_set_table")
    LiveData<List<ExerciseSet>> getAllExerciseSets();

    @Query("SELECT * FROM exercise_set_table WHERE exerciseSetId = :id")
    ExerciseSet findById(long id);

    @Query("SELECT exerciseSetId FROM exercise_set_table order by exerciseSetId DESC limit 4")
    List<Long> getLastIds();
}
