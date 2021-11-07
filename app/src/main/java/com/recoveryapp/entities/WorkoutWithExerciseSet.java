package com.recoveryapp.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithExerciseSet {
    @Embedded
    public Workout workout;
    @Relation(
            parentColumn = "id_workout",
            entity = ExerciseSet.class,entityColumn = "fk_exerciseId")
    public List<ExerciseSet> exerciseSetList;

}
