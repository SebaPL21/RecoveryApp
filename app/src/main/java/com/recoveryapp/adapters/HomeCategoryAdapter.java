package com.recoveryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.recoveryapp.R;
import com.recoveryapp.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.HomeCategoryHolder> {
    private List<Category> categoryList = new ArrayList<>();
    private CategoryAdapter.OnClickListener onClickListener;

    @NonNull
    @Override
    public HomeCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_category_item,parent,false);
        return new HomeCategoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryHolder holder, int position) {
        Category currentCategory= categoryList.get(position);

        holder.textViewName.setText(currentCategory.getName());

        Context context =  holder.imageViewCategory.getContext();
        String iconName = currentCategory.getImagePath();
        int resID = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
        holder.imageViewCategory.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public void setCategoryList(List<Category> categories){
        this.categoryList = categories;
        notifyDataSetChanged();
    }
    class HomeCategoryHolder extends  RecyclerView.ViewHolder{
        private TextView textViewName;
        private ImageView imageViewCategory;

        public HomeCategoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_home_category_name);
            imageViewCategory = itemView.findViewById(R.id.image_view_home_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(onClickListener != null && position != RecyclerView.NO_POSITION){
                        onClickListener.onItemClick(categoryList.get(position));
                    }
                }
            });
        }
    }

    /*On click listener*/
    public interface OnClickListener{
        void onItemClick(Category category);
    }
    public void setOnClickListener(CategoryAdapter.OnClickListener listener){
        this.onClickListener = listener;
    }
}