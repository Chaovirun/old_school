package com.android.oldschool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.autofill.AutofillManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;


import com.android.oldschool.comm.FDRButton;

import java.util.ArrayList;

public class TabGroup extends LinearLayout {

    private Context context;
    private AttributeSet attrs;
    private ArrayList<ViewGroup> views;
    private int deviceWidth;
    private TypedArray a;
    private setChildListener listener;
    private int selectedChild = 0;
    private LinearLayout linearLayout;
    // holds the checked id; the selection is empty by default
    private int mCheckedId = -1;

    public static final int HORIZONTAL = 0;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public TabGroup(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TabGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        this.context = context;
        init();
    }

    public TabGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        views = new ArrayList<>();
        a = context.obtainStyledAttributes(attrs, R.styleable.TabGroup, 0,0);
        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        deviceWidth = deviceDisplay.x;


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnHierarchyChangeListener(OnHierarchyChangeListener listener) {
        // the user listener is delegated to our pass-through listener
//        mPassThroughListener.mOnHierarchyChangeListener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // checks the appropriate radio button as requested in the XML file
        if (mCheckedId != -1) {
//            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
//            mProtectFromCheckedChange = false;
            setCheckedId(mCheckedId);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof FDRButton){
            FDRButton button = (FDRButton) child;
            if (button.isChecked()){
                setCheckedId(button.getId());
            }
        }
        super.addView(child, index, params);
    }

    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        super.updateViewLayout(view, params);
        Log.d(">>>>>>", "init: context " + getChildCount());
    }

    private void setCheckedId(@IdRes int id) {
        boolean changed = id != mCheckedId;
        mCheckedId = id;

        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
        }
        if (changed) {
            AutofillManager afm = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    afm = context.getSystemService(AutofillManager.class);
                }
            }
            if (afm != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    afm.notifyValueChanged(this);
                }
            }
        }
    }

    private void setCheckedStateForView(int viewId, boolean checked) {
        View checkedView = findViewById(viewId);
        if (checkedView != null && checkedView instanceof FDRButton) {
            ((FDRButton) checkedView).setChecked(checked);
        }
    }

    //    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        final int count = getChildCount();
//        Log.d(">>>>>>", "init: context " + count);
//        int curWidth, curHeight, curLeft, curTop, maxHeight;
//
//        //get the available size of child view
//        final int childLeft = this.getPaddingLeft();
//        final int childTop = this.getPaddingTop();
//        final int childRight = this.getMeasuredWidth() - this.getPaddingRight();
//        final int childBottom = this.getMeasuredHeight() - this.getPaddingBottom();
//        final int childWidth = childRight - childLeft;
//        final int childHeight = childBottom - childTop;
//
//        maxHeight = 0;
//        curLeft = childLeft;
//        curTop = childTop;
//
//        for (int i = 0; i < count; i++) {
//            View child= getChildAt(i);
//            views.add((ViewGroup) child);
////            if (child.getVisibility() == GONE)
////                return;
////
//            child.setId(i);
//
//            //Get the maximum size of the child
//            child.measure(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY));
//            curWidth = child.getMeasuredWidth();
//            curHeight = child.getMeasuredHeight();
////            //wrap is reach to the end
////            if (curLeft + curWidth >= childRight) {
////                curLeft = childLeft;
////                curTop += maxHeight;
////                maxHeight = 0;
////            }
////            //do the layout
//            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
//            //store the max height
////            if (maxHeight < curHeight)
////                maxHeight = curHeight;
////            curLeft += curWidth;
//        }
//        Log.d(">>>>>>", "init: context " + childWidth + " h- " + childHeight);
//        setColor(selectedChild, views,count);
//
//    }
    private void setColor(final int id, final ArrayList<ViewGroup> views, final int count){
        for(int i=0;i<count; i++){
            final int finalI = i;
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getId() == finalI){
                        views.get(finalI).setBackgroundColor(a.getColor(R.styleable.TabGroup_focusColor,Color.BLACK));
                    }
                    selectTab(finalI,count);

                    if (listener!=null){
                        listener.onPressChanged(view.getId());
                    }
                    Log.d(">>>>>>", "init: context in" + finalI);
                }
            });

        }
    }

    private void selectTab(int finalI, int count){
        for(int i=0;i<count;i++){
            if (views.get(i).getId() == finalI){

            }else{
                views.get(i).setBackgroundColor(a.getColor(R.styleable.TabGroup_childBackground,Color.BLACK));
            }

        }

    }

    /**
     * <p>Register a callback to be invoked when the checked radio button
     * changes in this group.</p>
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    /**
     * <p>Interface definition for a callback to be invoked when the checked
     * radio button changed in this group.</p>
     */
    public interface OnCheckedChangeListener {
        /**
         * <p>Called when the checked radio button has changed. When the
         * selection is cleared, checkedId is -1.</p>
         *
         * @param group the group in which the checked radio button has changed
         * @param checkedId the unique identifier of the newly checked radio button
         */
        public void onCheckedChanged(TabGroup group, @IdRes int checkedId);
    }

    interface setChildListener{
        void onPressChanged(int index);
    }

    public void setOnTabGroupChildListener(setChildListener listener){
        this.listener = listener;
    }

}
