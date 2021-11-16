package com.recoveryapp.entities;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithExerciseSets {
    @Embedded
    public Workout workout;
    @Relation(
            parentColumn = "workoutId",
            entity = ExerciseSet.class,
            entityColumn = "exerciseSetId",
            associateBy = @Junction(value = WorkoutExerciseSetCrossRef.class,parentColumn = "workoutId",entityColumn = "exerciseSetId")
    )
    public List<ExerciseSet> exerciseSets;
}
