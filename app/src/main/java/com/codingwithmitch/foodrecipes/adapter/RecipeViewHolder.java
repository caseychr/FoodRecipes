package com.codingwithmitch.foodrecipes.adapter;

import android.view.View;
import android.widget.TextView;

import com.codingwithmitch.foodrecipes.R;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, publisher, socialScore;
    AppCompatImageView image;
    OnRecipeListener onRecipeListener;

    public RecipeViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
        super(itemView);

        this.onRecipeListener = onRecipeListener;
        /** Instantiate and pass through an OnRecipeClickListener*/
        title = itemView.findViewById(R.id.recipe_title);
        publisher = itemView.findViewById(R.id.recipe_publisher);
        socialScore = itemView.findViewById(R.id.recipe_social_score);
        image = itemView.findViewById(R.id.recipe_image);

        /** Set the onClickListener to this entire itemview  so it then passes on to the onClick() callback
         * right below here */
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /**
         * Used the callback OnRecipeClickListener and called the onRecipeClick callback method inside the onClick.
         * here. Now we will specify in the ViewModel what to do when this is called and it will know to execute
         * whatever is specified in the Override method HERE.
         */
        onRecipeListener.onRecipeClick(getAdapterPosition());
    }
}
