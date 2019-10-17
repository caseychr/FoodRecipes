package com.codingwithmitch.foodrecipes;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Always a good idea to include a BaseActivity regardless of architecture.
 * This will help reduce code duplication and will improve readablility.
 *
 * RecipeListActivity will extend this activity
 *
 * NOTE: We do not add this to the manifest since it's not a normal activity. Instead we make it abstract which
 * removes the ability to instantiate it.
 *
 * Why to use MVVM?
 * 1) Better Readability
 * 2) Decouples business logic from UI
 * 3) Provides from better Unit Testing
 * 4) Makes the business logic lifecycle independent
 *
 * 6 reasons to use LiveData?
 * 1) LiveData ensures your UI matches your data state with observers
 * 2) Less likely memory leaks. Observers are bound to lifecycle objects and those objects are cleaned up after
 *    the lifecycle is destroyed.
 * 3) No crashes due to stopped activity. If an observers lifecycle is inactive (like when a view is onPause())
 *    it won't receive any lifecycle events.
 * 4) No more manual lifecycle handling. LiveData is lifecycle aware and manages all relevant lifecycle changes.
 * 5) Your data is always up to date -> Lifecycles that are inactive will automatically be updated again.
 * 6) Proper config changes are handled and updated with the latest data
 */
public abstract class BaseActivity extends AppCompatActivity {

    public ProgressBar mProgressBar;

    @Override
    public void setContentView(int layoutResID) {

        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);
        super.setContentView(constraintLayout);
    }

    public void showProgressBar(boolean visibility){
        mProgressBar.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }
}
