package com.codingwithmitch.foodrecipes.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalSpacingItemDecorator extends RecyclerView.ItemDecoration {

    private final int verticalSpacingHeight;

    public VerticalSpacingItemDecorator(int verticalSpacingHeight) {
        this.verticalSpacingHeight = verticalSpacingHeight;
    }

    /**
     * We use this to set certain offsets on the recyclerview
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
            @NonNull RecyclerView.State state) {
        outRect.top = verticalSpacingHeight;
    }
}
