package com.codingwithmitch.foodrecipes.requests;

import com.codingwithmitch.foodrecipes.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Our Retrofit instance object created in a Singleton pattern for app wide use.
 */
public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    /**
     * Creating and adding RecipeApi object to retrofit Singleton class. We will run our CRUD operations in
     * that interface.
     */
    private static RecipeApi recipeApi = retrofit.create(RecipeApi.class);

    public static RecipeApi getRecipeApi(){
        return recipeApi;
    }
}
