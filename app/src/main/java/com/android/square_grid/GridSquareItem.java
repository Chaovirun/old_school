package com.android.square_grid;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class GridSquareItem extends LinearLayout {
    public GridSquareItem(Context context) {
        super(context);
    }

    public GridSquareItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GridSquareItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}
