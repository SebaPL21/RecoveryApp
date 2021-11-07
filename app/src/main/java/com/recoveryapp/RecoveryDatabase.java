package com.recoveryapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.recoveryapp.dao.CategoryDao;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.entities.ExerciseSet;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutLog;

@Database(entities = {Category.class, Exercise.class, ExerciseSet.class, Workout.class, WorkoutLog.class}, version = 1)
public abstract class RecoveryDatabase extends RoomDatabase {
    private static RecoveryDatabase instance;

    public abstract CategoryDao categoryDao();

    public static synchronized  RecoveryDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),RecoveryDatabase.class, "recovery_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
