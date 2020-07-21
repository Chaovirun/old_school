package com.android.oldschool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.oldschool.TimePicker.RageTimePickerDialog;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;

public class TimePickerActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        TimePicker tp = findViewById(R.id.timePicker);
        tp.setOnTimeChangedListener(timeChangedListener);

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });

//        ImageView anchor = findViewById(R.id.anchor);
//
//        BadgeDrawable badgeDrawable = BadgeDrawable.create(this);
//        badgeDrawable.setBadgeGravity(BadgeDrawable.TOP_END);
//        badgeDrawable.setBackgroundColor(Color.RED);
//        badgeDrawable.setNumber(20);
//        BadgeUtils.attachBadgeDrawable(badgeDrawable, anchor, null);

        tp.setIs24HourView(false);
        tp.setCurrentHour(06);
        tp.setCurrentMinute(6);

        //new RageTimePickerDialog(this, android.R.style.Theme_Holo_Dialog_NoActionBar, null, 10, 0, false);

        findViewById(R.id.sc).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getActionMasked() == MotionEvent.ACTION_MOVE;
            }
        });

//        RoundRectShape rss = new RoundRectShape(new float[] { 12f, 12f, 12f,
//                12f, 12f, 12f, 12f, 12f }, null, null);
//        ShapeDrawable sds = new ShapeDrawable(rss);
//        sds.setShaderFactory(new ShapeDrawable.ShaderFactory() {
//
//            @Override
//            public Shader resize(int width, int height) {
//                LinearGradient lg = new LinearGradient(0, 0, 0, height,
//                        new int[] { Color.parseColor("#e5e5e5"),
//                                Color.parseColor("#e5e5e5"),
//                                Color.parseColor("#e5e5e5"),
//                                Color.parseColor("#e5e5e5") }, new float[] { 0,
//                        0.50f, 0.50f, 1 }, Shader.TileMode.REPEAT);
//                return lg;
//            }
//        });
//
//        LayerDrawable ld = new LayerDrawable(new Drawable[] { sds, sd });
//        ld.setLayerInset(0, 5, 5, 0, 0); // inset the shadow so it doesn't start right at the left/top
//        ld.setLayerInset(1, 0, 0, 5, 5); // inset the top drawable so we can leave a bit of space for the shadow to use

        linearLayout = findViewById(R.id.anchor1);
        linearLayout.setOutlineProvider(new OutLineProvider(100));
        linearLayout.setClipToOutline(true);

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerDialogFragment();
        newFragment.show(getSupportFragmentManager(), "time picker");
    }

    private TimePicker.OnTimeChangedListener timeChangedListener =
            new TimePicker.OnTimeChangedListener(){
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                        Toast.makeText(TimePickerActivity.this,
                                "picked time is " + hourOfDay +
                                        " / " + minute
                                , Toast.LENGTH_SHORT).show();


                }
            };

    private LayerDrawable setLayerShadow(String color) {
        GradientDrawable shadow;
        int strokeValue = 20;
        int radiousValue = 50;
        try{
            int[] colors1 = {Color.parseColor(color), Color.parseColor("#FFFFFF")};
            shadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors1);
            shadow.setCornerRadius(radiousValue);
        }
        catch(Exception e){
            int[] colors1 = {Color.parseColor("#419ED9"), Color.parseColor("#419ED9")};
            shadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors1);
            shadow.setCornerRadius(radiousValue);
            e.printStackTrace();
        }


        int[] colors = {Color.parseColor("#D8D8D8"), Color.parseColor("#E5E3E4")};

        GradientDrawable backColor = new GradientDrawable(GradientDrawable.Orientation.BL_TR, colors);
        backColor.setCornerRadius(radiousValue);
        backColor.setStroke(strokeValue, Color.parseColor("#D8D8D8"));

        //finally c.reate a layer list and set them as background.
        Drawable[] layers = new Drawable[3];
        layers[0] = backColor;
        layers[1] = shadow;
        layers[2] = shadow;

        LayerDrawable layerList = new LayerDrawable(layers);
        layerList.setLayerInset(0, 0, 0, 0, 0);
        layerList.setLayerInset(1, strokeValue, strokeValue, strokeValue, strokeValue);
        layerList.setLayerInset(2, strokeValue, strokeValue, strokeValue, strokeValue);
        return layerList;
    }
}
