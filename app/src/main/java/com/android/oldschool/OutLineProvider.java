package com.android.oldschool;

import android.graphics.Outline;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class OutLineProvider extends ViewOutlineProvider {

    int roundCorner;

    public OutLineProvider(int round) {
        roundCorner = round;
    }

    @Override
    public void getOutline(View view, Outline outline) {
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), roundCorner);
    }
}