package com.recoveryapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Category;
import com.recoveryapp.entities.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {
    private List<Workout> workoutList = new ArrayList<>();
    private OnClickListener onClickListener;

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onClickListener != null && position != RecyclerView.NO_POSITION){
                        onClickListener.onItemClick(workoutList.get(position));
                        System.out.println(workoutList.get(position).getWorkoutId());
                    }
                }
            });
        }
    }
    /*On click listener*/
    public interface OnClickListener{
        void onItemClick(Workout workout);
    }
    public void setOnClickListener(OnClickListener listener){
        this.onClickListener = listener;
    }
}
