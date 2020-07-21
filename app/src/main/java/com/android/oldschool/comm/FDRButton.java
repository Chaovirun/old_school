package com.android.oldschool.comm;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.autofill.AutofillManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.oldschool.R;

import java.util.ArrayList;

public class FDRButton extends RelativeLayout {

    private boolean mChecked;

    public FDRButton(Context context) {
        super(context);
    }

    public FDRButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FDRButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FDRButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean isChecked(){
        return mChecked;
    }

    /**
     * <p>Changes the checked state of this button.</p>
     *
     * @param checked true to check the button, false to uncheck it
     */
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
//            mCheckedFromResource = false;
            mChecked = checked;
            refreshDrawableState();
//            notifyViewAccessibilityStateChangedIfNeeded(
//                    AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED);

            // Avoid infinite recursions if setChecked() is called from a listener
//            if (mBroadcasting) {
//                return;
//            }
//
//            mBroadcasting = true;
//            if (mOnCheckedChangeListener != null) {
//                mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
//            }
//            if (mOnCheckedChangeWidgetListener != null) {
//                mOnCheckedChangeWidgetListener.onCheckedChanged(this, mChecked);
//            }
//            final AutofillManager afm = mContext.getSystemService(AutofillManager.class);
//            if (afm != null) {
//                afm.notifyValueChanged(this);
//            }

//            mBroadcasting = false;
        }
    }

    private void init() {
//        views = new ArrayList<>();
//        a = context.obtainStyledAttributes(attrs, R.styleable.TabGroup, 0,0);
//        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//        Point deviceDisplay = new Point();
//        display.getSize(deviceDisplay);
//        deviceWidth = deviceDisplay.x;
//        Log.d(">>>>>>", "init: contex " +  context.toString());
    }
}
