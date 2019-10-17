package com.codingwithmitch.foodrecipes.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingwithmitch.foodrecipes.R;
import com.codingwithmitch.foodrecipes.model.Recipe;
import com.codingwithmitch.foodrecipes.util.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * This RecyclerView will hold 4 separate viewholders that need to be implemented on different screens.
 */
public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RECIPE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;
    private static final int EXHAUSTED_TYPE = 4;

    private List<Recipe> mRecipes;
    private OnRecipeListener mOnRecipeListener;

    public RecipeRecyclerAdapter(OnRecipeListener mOnRecipeListener) {
        this.mOnRecipeListener = mOnRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = null;
        switch (i){

            case RECIPE_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }

            case LOADING_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_list_item, viewGroup, false);
                return new LoadingViewHolder(view);
            }

            case EXHAUSTED_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_search_exhausted, viewGroup, false);
                return new SearchExhaustedViewHolder(view);
            }

            case CATEGORY_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_category_list_item, viewGroup, false);
                return new CategoryViewHolder(view, mOnRecipeListener);
            }

            default:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_list_item, viewGroup, false);
                return new RecipeViewHolder(view, mOnRecipeListener);
            }
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int itemViewType = getItemViewType(i);
        if(itemViewType == RECIPE_TYPE){
            /**
             * Downloading and displaying images in a feed is not a simple task but there are 3rd party libraries for
             * that. Picasso and Glide are the forerunners for that. Here we will use Glide to download and display
             * the recipe images in the viewholders as pagination happens
             */
            // set the image
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(viewHolder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mRecipes.get(i).getImage_url())
                    .into(((RecipeViewHolder)viewHolder).image);

            ((RecipeViewHolder)viewHolder).title.setText(mRecipes.get(i).getTitle());
            ((RecipeViewHolder)viewHolder).publisher.setText(mRecipes.get(i).getPublisher());
            ((RecipeViewHolder)viewHolder).socialScore.setText(String.valueOf(Math.round(mRecipes.get(i).getSocial_rank())));
        }
        else if(itemViewType == CATEGORY_TYPE){

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Uri path = Uri.parse("android.resource://com.codingwithmitch.foodrecipes/drawable/" + mRecipes.get(i).getImage_url());
            Glide.with(viewHolder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(path)
                    .into(((CategoryViewHolder)viewHolder).categoryImage);

            ((CategoryViewHolder)viewHolder).categoryTitle.setText(mRecipes.get(i).getTitle());

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(mRecipes.get(position).getSocial_rank() == -1){
            return CATEGORY_TYPE;
        }
        else if(mRecipes.get(position).getTitle().equals("LOADING...")){
            return LOADING_TYPE;
        }
        else if(mRecipes.get(position).getTitle().equals("EXHAUSTED...")){
            return EXHAUSTED_TYPE;
        }
        else if(position == mRecipes.size() - 1
                && position != 0
                && !mRecipes.get(position).getTitle().equals("EXHAUSTED...")){
            return LOADING_TYPE;
        }
        else{
            return RECIPE_TYPE;
        }
    }

    public void setQueryExhausted(){
        hideLoading();
        Recipe exhaustedRecipe = new Recipe();
        exhaustedRecipe.setTitle("EXHAUSTED...");
        mRecipes.add(exhaustedRecipe);
        notifyDataSetChanged();
    }

    private void hideLoading(){
        if(isLoading()){
            for(Recipe recipe: mRecipes){
                if(recipe.getTitle().equals("LOADING...")){
                    mRecipes.remove(recipe);
                }
            }
            notifyDataSetChanged();
        }
    }

    /**
     * Called in the View when we submit a search query. if isLoading is FALSE we create a single List of Recipes
     * with a Title of Loading and return it. getItemViewType sees that the title is LOADING... and discerns to
     * load the LoadingViewHolder.
     */
    public void displayLoading(){
        if(!isLoading()){
            Recipe recipe = new Recipe();
            recipe.setTitle("LOADING...");
            List<Recipe> loadingList = new ArrayList<>();
            loadingList.add(recipe);
            mRecipes = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading(){
        if(mRecipes != null){
            if(mRecipes.size() > 0){
                if(mRecipes.get(mRecipes.size() - 1).getTitle().equals("LOADING...")){
                    return true;
                }
            }
        }
        return false;
    }

    public void displaySearchCategories(){
        List<Recipe> categories = new ArrayList<>();
        for(int i = 0; i< Constants.DEFAULT_SEARCH_CATEGORIES.length; i++){
            Recipe recipe = new Recipe();
            recipe.setTitle(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
            recipe.setImage_url(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
            recipe.setSocial_rank(-1);
            categories.add(recipe);
        }
        mRecipes = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mRecipes != null){
            return mRecipes.size();
        }
        return 0;
    }

    public void setRecipes(List<Recipe> recipes){
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    public Recipe getSelectedRecipe(int position){
        if(mRecipes != null){
            if(mRecipes.size() > 0){
                return mRecipes.get(position);
            }
        }
        return null;
    }

}
