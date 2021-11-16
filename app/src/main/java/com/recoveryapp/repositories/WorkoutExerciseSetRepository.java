package com.recoveryapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.recoveryapp.RecoveryDatabase;
import com.recoveryapp.dao.WorkoutDao;
import com.recoveryapp.dao.WorkoutExerciseSetDao;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutExerciseSetCrossRef;

public class WorkoutExerciseSetRepository {
    private WorkoutExerciseSetDao workoutExerciseSetDao;

    public WorkoutExerciseSetRepository(Application application) {
        RecoveryDatabase database = RecoveryDatabase.getInstance(application);
        workoutExerciseSetDao = database.workoutExerciseSetDao();
    }
    public void insert(WorkoutExerciseSetCrossRef workoutExerciseSetCrossRef){
        new WorkoutExerciseSetRepository.InsertWorkoutExerciseSetAsyncTask(workoutExerciseSetDao).execute(workoutExerciseSetCrossRef);
    }
    private static class InsertWorkoutExerciseSetAsyncTask extends AsyncTask<WorkoutExerciseSetCrossRef,Void, Void> {
        private WorkoutExerciseSetDao workoutExerciseSetDao;

        public InsertWorkoutExerciseSetAsyncTask(WorkoutExerciseSetDao workoutExerciseSetDao) {
            this.workoutExerciseSetDao = workoutExerciseSetDao;
        }

        @Override
        protected Void doInBackground(WorkoutExerciseSetCrossRef... workoutExerciseSetCrossRefs) {
            workoutExerciseSetDao.insert(workoutExerciseSetCrossRefs[0]);
            return null;
        }
    }
}
