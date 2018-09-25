package com.androidstuff.fatsecret;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.fatsecret.platform.services.Response;
import com.fatsecret.platform.services.android.Request;
import com.fatsecret.platform.services.android.ResponseListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String key = "fae2f0f9cc26468db46cce465ea012ec";
        String secret = "c5c9cdf16c584584ac23599c74528409";
        String query = "pasta";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Listener listener = new Listener();

        Request req = new Request(key, secret, listener);

        //This response contains the list of food items at zeroth page for your query
        req.getFoods(requestQueue, query,1);

        //This response contains the list of food items at page number 3 for your query
        //If total results are less, then this response will have empty list of the food items
        req.getFoods(requestQueue, query, 3);

        //This food object contains detailed information about the food item
        req.getFood(requestQueue, 29304L);

        //This response contains the list of recipe items at zeroth page for your query
        req.getRecipes(requestQueue, query,1);

        //This response contains the list of recipe items at page number 2 for your query
        //If total results are less, then this response will have empty list of the recipe items
        req.getRecipes(requestQueue, query, 2);

        //This recipe object contains detailed information about the recipe item
        req.getRecipe(requestQueue, 315L);
    }
    class Listener implements ResponseListener {
        @Override
        public void onFoodListRespone(Response<CompactFood> response) {
            Log.i("responce","onFoodListRespone  "+response.toString());
            System.out.println("TOTAL FOOD ITEMS: " + response.getTotalResults());

            List<CompactFood> foods = response.getResults();

            //This list contains summary information about the food items

            System.out.println("=========FOODS============");
            for (CompactFood food: foods) {
                System.out.println(food.getName());
                System.out.println(food.getDescription());
            }
        }

        @Override
        public void onRecipeListRespone(Response<CompactRecipe> response) {
            Log.i("responce","onRecipeListRespone  "+response.toString());
            System.out.println("TOTAL RECIPES: " + response.getTotalResults());

            List<CompactRecipe> recipes = response.getResults();
            System.out.println("=========RECIPES==========");
            for (CompactRecipe recipe: recipes) {
                System.out.println(recipe.getName());
            }
        }

        @Override
        public void onFoodResponse(Food food) {
            Log.i("responce","onFoodResponse  "+food.toString());
            System.out.println("FOOD NAME: " + food.getName());
        }

        @Override
        public void onRecipeResponse(Recipe recipe) {
            Log.i("responce","onRecipeResponse  "+recipe.toString());
            System.out.println("RECIPE NAME: " + recipe.getName());
        }
    }
}
