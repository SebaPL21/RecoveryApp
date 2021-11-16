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
    public OnClickListener onclickListener;
    private List<Exercise> exercises = new ArrayList<>();
    @NonNull
    @Override
    public ExerciseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View exerItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_item,parent, false);
        return new ExerciseHolder(exerItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseHolder holder, int position) {
        Exercise currentExercise= exercises.get(position);
        holder.tViewTitle.setText(currentExercise.getName());
        holder.tViewDescription.setText(currentExercise.getDescription());
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
        private TextView tViewDescription;

        public ExerciseHolder(@NonNull View itemView) {
            super(itemView);
            tViewTitle = itemView.findViewById(R.id.text_view_exercise_name);
            tViewDescription= itemView.findViewById(R.id.text_view_exercise_decription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onclickListener != null && position != RecyclerView.NO_POSITION){
                        onclickListener.onItemClick(exercises.get(position));
                        System.out.println(exercises.get(position).getExerciseId());
                    }
                }
            });
        }
    }
    public interface OnClickListener{
        void onItemClick(Exercise exercise);
    }
    public void setOnClickListener(OnClickListener listener){
        this.onclickListener = listener;
    }
}
