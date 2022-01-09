package com.recoveryapp.adapters;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.recoveryapp.R;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Exercise;
import com.recoveryapp.entities.ExerciseSet;
import com.recoveryapp.entities.Workout;
import com.recoveryapp.entities.WorkoutLog;
import com.recoveryapp.repositories.ExerciseRepository;
import com.recoveryapp.repositories.ExerciseSetRepository;
import com.recoveryapp.repositories.WorkoutRepository;
import com.recoveryapp.viewmodel.CategoryViewModel;
import com.recoveryapp.viewmodel.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ProfileHolder>{
    private List<WorkoutLog> workoutLogs = new ArrayList<>();
    private Application application;

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item,parent,false);
        return new ProfileHolder(itemView);
    }

    public ProfileAdapter(Application application) {
        this.application = application;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        WorkoutLog currentWorkoutLog= workoutLogs.get(position);
        WorkoutRepository workoutRepository = new WorkoutRepository(application);
        ExerciseSetRepository exerciseSetRepository = new ExerciseSetRepository(application);
        ExerciseSet exerciseSet1 = exerciseSetRepository.findById(currentWorkoutLog.getExerciseSetId1());
        ExerciseSet exerciseSet2 = exerciseSetRepository.findById(currentWorkoutLog.getExerciseSetId2());
        ExerciseSet exerciseSet3 = exerciseSetRepository.findById(currentWorkoutLog.getExerciseSetId3());
        ExerciseSet exerciseSet4 = exerciseSetRepository.findById(currentWorkoutLog.getExerciseSetId4());

        ExerciseRepository exerciseRepository = new ExerciseRepository(application);

        Exercise exerciseFromExerciseSet1 = exerciseRepository.findById(exerciseSet1.getFk_exerciseId());
        Exercise exerciseFromExerciseSet2 = exerciseRepository.findById(exerciseSet2.getFk_exerciseId());
        Exercise exerciseFromExerciseSet3 = exerciseRepository.findById(exerciseSet3.getFk_exerciseId());
        Exercise exerciseFromExerciseSet4 = exerciseRepository.findById(exerciseSet4.getFk_exerciseId());

        Workout workout = workoutRepository.findById(currentWorkoutLog.getFk_workoutId());
        holder.textViewName.setText(workout.getName());
        holder.textViewSets.setText("Cwiczenia:");
        holder.textViewE1.setText("* "+exerciseFromExerciseSet1.getName() + " ilość serii: "+exerciseSet1.getSet());
        holder.textViewE2.setText("* "+exerciseFromExerciseSet2.getName() + " ilość serii: "+exerciseSet2.getSet());
        holder.textViewE3.setText("* "+exerciseFromExerciseSet3.getName() + " ilość serii: "+exerciseSet3.getSet());
        holder.textViewE4.setText("* "+exerciseFromExerciseSet4.getName() + " ilość serii: "+exerciseSet4.getSet());
    }
    @Override
    public int getItemCount() {
        return workoutLogs.size();
    }

    public void setWorkoutLogs(List<WorkoutLog> workoutLogs) {
        this.workoutLogs = workoutLogs;
        notifyDataSetChanged();
    }
    class ProfileHolder extends  RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewSets;
        private TextView textViewE1;
        private TextView textViewE2;
        private TextView textViewE3;
        private TextView textViewE4;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_workout_log_name);
            textViewSets = itemView.findViewById(R.id.text_view_workout_log_sets);
            textViewE1 = itemView.findViewById(R.id.text_view_e1);
            textViewE2 = itemView.findViewById(R.id.text_view_e2);
            textViewE3 = itemView.findViewById(R.id.text_view_e3);
            textViewE4 = itemView.findViewById(R.id.text_view_e4);
        }
    }
}
