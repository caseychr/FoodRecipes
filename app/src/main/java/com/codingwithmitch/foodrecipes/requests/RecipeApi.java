package com.codingwithmitch.foodrecipes.requests;

import com.codingwithmitch.foodrecipes.requests.response.RecipeResponse;
import com.codingwithmitch.foodrecipes.requests.response.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Through ServiceGenerator this class references the Constants.BASE_URL and then includes it in the request here.
 *
 */
public interface RecipeApi {

    /**
     * This is the search request that retrieves a RecipeSearResponse object from the endpoint.
     * When you make a GET request you use @Query when you need to apend ? to the url request and then there after
     * it will apend an & symbol.
     * @param key
     * @param query
     * @param page
     * @return
     */
    // SEARCH
    @GET("api/search")
    Call<RecipeSearchResponse> searchRecipe(
            @Query("key") String key,
            @Query("q") String query,
            @Query("page") String page
    );

    // GET RECIPE REQUEST
    @GET("api/get")
    Call<RecipeResponse> getRecipe(
            @Query("key") String key,
            @Query("rId") String recipe_id
    );


}
