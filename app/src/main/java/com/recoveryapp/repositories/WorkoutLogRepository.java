package com.recoveryapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.recoveryapp.RecoveryDatabase;
import com.recoveryapp.dao.CategoryDao;
import com.recoveryapp.dao.WorkoutLogDao;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.WorkoutLog;

import java.util.List;

public class WorkoutLogRepository {
    private WorkoutLogDao workoutLogDao;
    private LiveData<List<WorkoutLog>> workoutLogs;

    public WorkoutLogRepository(Application application) {
        RecoveryDatabase database = RecoveryDatabase.getInstance(application);
        workoutLogDao = database.workoutLogDao();
        workoutLogs = workoutLogDao.getAllWorkoutLogs();
    }
    public void insert(WorkoutLog workoutLog){
        new InsertWorkoutLogAsyncTask(workoutLogDao).execute(workoutLog);
    }
    public void update(WorkoutLog workoutLog){
        new UpdateWorkoutLogAsyncTask(workoutLogDao).execute(workoutLog);
    }
    public void delete(WorkoutLog workoutLog){
        new DeleteWorkoutLogAsyncTask(workoutLogDao).execute(workoutLog);
    }

    public LiveData<List<WorkoutLog>> getWorkoutLogs() {
        return workoutLogs;
    }
    public WorkoutLog findById(long id){
        return workoutLogDao.findById(id);
    }
    private static class InsertWorkoutLogAsyncTask extends AsyncTask<WorkoutLog,Void, Void> {
        private WorkoutLogDao workoutLogDao;

        public InsertWorkoutLogAsyncTask(WorkoutLogDao workoutLogDao) {
            this.workoutLogDao = workoutLogDao;
        }

        @Override
        protected Void doInBackground(WorkoutLog... workoutLogs) {
            workoutLogDao.insert(workoutLogs[0]);
            return null;
        }
    }
    private static class UpdateWorkoutLogAsyncTask extends AsyncTask<WorkoutLog,Void, Void> {
        private WorkoutLogDao workoutLogDao;

        public UpdateWorkoutLogAsyncTask(WorkoutLogDao workoutLogDao) {
            this.workoutLogDao = workoutLogDao;
        }

        @Override
        protected Void doInBackground(WorkoutLog... workoutLogs) {
            workoutLogDao.update(workoutLogs[0]);
            return null;
        }
    }
    private static class DeleteWorkoutLogAsyncTask extends AsyncTask<WorkoutLog,Void, Void> {
        private WorkoutLogDao workoutLogDao;

        public DeleteWorkoutLogAsyncTask(WorkoutLogDao workoutLogDao) {
            this.workoutLogDao = workoutLogDao;
        }

        @Override
        protected Void doInBackground(WorkoutLog... workoutLogs) {
            workoutLogDao.delete(workoutLogs[0]);
            return null;
        }
    }
}
