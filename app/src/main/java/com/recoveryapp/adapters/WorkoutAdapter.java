package com.recoveryapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {
    private List<Workout> workoutList = new ArrayList<>();

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item,parent,false);
        return new WorkoutHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder holder, int position) {
        Workout currentWorkout = workoutList.get(position);
        holder.textViewName.setText(currentWorkout.getName());
        holder.textViewDifficultLevel.setText(String.valueOf(currentWorkout.getDifficultLevel()));
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    public void setWorkoutList(List<Workout> workouts){
        this.workoutList = workouts;
        notifyDataSetChanged();
    }


    class WorkoutHolder extends  RecyclerView.ViewHolder{
        private TextView textViewName;
        private TextView textViewDifficultLevel;

        public WorkoutHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDifficultLevel = itemView.findViewById(R.id.text_view_difficult_level);
        }
    }
}
