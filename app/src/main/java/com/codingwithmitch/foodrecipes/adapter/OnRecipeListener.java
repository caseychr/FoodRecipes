package com.codingwithmitch.foodrecipes.adapter;

/**
 * Since we're reusing this recyclerview to create several viewholders we need 2 separate callbacks
 * for click events. onCategoryClick() when displaying categories and onRecipeClick() when displaying recipes.
 */
public interface OnRecipeListener {

    void onRecipeClick(int position);

    void onCategoryClick(String category);
}
