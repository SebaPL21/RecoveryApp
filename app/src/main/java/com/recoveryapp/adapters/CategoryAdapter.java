package com.recoveryapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    private List<Category> categoryList = new ArrayList<>();
    private OnClickListener onClickListener;

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item,parent,false);
        return new CategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category currentWorkout = categoryList.get(position);
        holder.textViewName.setText(currentWorkout.getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setCategoryList(List<Category> categories){
        this.categoryList = categories;
        notifyDataSetChanged();
    }
    class CategoryHolder extends  RecyclerView.ViewHolder{
        private TextView textViewName;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_category_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onClickListener != null && position != RecyclerView.NO_POSITION){
                        onClickListener.onItemClick(categoryList.get(position));
                        System.out.println(categoryList.get(position).getCategoryId());
                    }
                }
            });
        }
    }

    /*On click listener*/
    public interface OnClickListener{
        void onItemClick(Category category);
    }
    public void setOnClickListener(OnClickListener listener){
        this.onClickListener = listener;
    }
}
