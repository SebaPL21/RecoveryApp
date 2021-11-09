package com.recoveryapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter <ExercisesAdapter.ExerciseHolder>{
    private List<Exercise> exercises = new ArrayList<>();
    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View exerItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_exercises,parent, false);
        return new ExerciseHolder(exerItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position) {
        Exercise currentExercise= exercises.get(position);
        holder.tViewTitle.setText(currentExercise.getName());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    class ExerciseHolder extends RecyclerView.ViewHolder{
        private TextView tViewTitle;


        public ExerciseHolder(@NonNull View itemView) {
            super(itemView);
            tViewTitle = itemView.findViewById(R.id.text_view_exerciese_name);
        }
    }
}
