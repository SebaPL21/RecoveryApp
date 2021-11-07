package com.recoveryapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.recoveryapp.RecoveryDatabase;
import com.recoveryapp.dao.CategoryDao;
import com.recoveryapp.dao.WorkoutDao;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Workout;

import java.util.List;

public class WorkoutRepository {
    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> workouts;

    public WorkoutRepository(Application application) {
        RecoveryDatabase database = RecoveryDatabase.getInstance(application);
        workoutDao = database.workoutDao();
        workouts = workoutDao.getAllWorkouts();;
    }
    public void insert(Workout workout){
        new InsertWorkoutAsyncTask(workoutDao).execute(workout);
    }
    public void update(Workout workout){
        new UpdateWorkoutAsyncTask(workoutDao).execute(workout);
    }
    public void delete(Workout workout){
        new DeleteWorkoutAsyncTask(workoutDao).execute(workout);
    }

    public LiveData<List<Workout>> getWorkouts() {
        return workouts;
    }
    public Workout findById(long id){
        return workoutDao.findById(id);
    }
    private static class InsertWorkoutAsyncTask extends AsyncTask<Workout,Void, Void> {
        private WorkoutDao workoutDao;

        public InsertWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.insert(workouts[0]);
            return null;
        }
    }
    private static class UpdateWorkoutAsyncTask extends AsyncTask<Workout,Void, Void> {
        private WorkoutDao workoutDao;

        public UpdateWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.update(workouts[0]);
            return null;
        }
    }
    private static class DeleteWorkoutAsyncTask extends AsyncTask<Workout,Void, Void> {
        private WorkoutDao workoutDao;

        public DeleteWorkoutAsyncTask(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDao.delete(workouts[0]);
            return null;
        }
    }
}
