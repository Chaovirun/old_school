//package com.android.oldschool.expandable_tab;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.drawable.Drawable;
//import android.os.Build;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewStub;
//import android.view.animation.Animation;
//import android.view.animation.RotateAnimation;
//import android.view.animation.Transformation;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.util.HashMap;
//
//public final class ExpandableCardView extends LinearLayout {
//    private String title;
//    private View innerView;
//    private TypedArray typedArray;
//    private int innerViewRes;
//    private Drawable iconDrawable;
//    private long animDuration;
//    private boolean isExpanded;
//    private boolean isExpanding;
//    private boolean isCollapsing;
//    private boolean expandOnClick;
//    private boolean startExpanded;
//    private int previousHeight;
//    private ExpandableCardView.OnExpandedListener listener;
//    private final OnClickListener defaultClickListener;
//    private static final int DEFAULT_ANIM_DURATION = 350;
//    private static final int COLLAPSING = 0;
//    private static final int EXPANDING = 1;
//    public static final ExpandableCardView.Companion Companion = new ExpandableCardView.Companion((DefaultConstructorMarker)null);
//    private HashMap _$_findViewCache;
//
//    public final long getAnimDuration() {
//        return this.animDuration;
//    }
//
//    public final void setAnimDuration(long var1) {
//        this.animDuration = var1;
//    }
//
//    public final boolean isExpanded() {
//        return this.isExpanded;
//    }
//
//    private final boolean isMoving() {
//        return this.isExpanding || this.isCollapsing;
//    }
//
//    private final void initView(Context context) {
//        LayoutInflater.from(context).inflate(layout.expandable_cardview, (ViewGroup)this);
//    }
//
//    private final void initAttributes(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, styleable.ExpandableCardView);
//        this.typedArray = typedArray;
//        this.title = typedArray.getString(styleable.ExpandableCardView_title);
//        this.iconDrawable = typedArray.getDrawable(styleable.ExpandableCardView_icon);
//        this.innerViewRes = typedArray.getResourceId(styleable.ExpandableCardView_inner_view, -1);
//        this.expandOnClick = typedArray.getBoolean(styleable.ExpandableCardView_expandOnClick, false);
//        this.animDuration = (long)typedArray.getInteger(styleable.ExpandableCardView_animationDuration, 350);
//        this.startExpanded = typedArray.getBoolean(styleable.ExpandableCardView_startExpanded, false);
//        typedArray.recycle();
//    }
//
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        if (!TextUtils.isEmpty((CharSequence)this.title)) {
//            TextView var10000 = (TextView)this._$_findCachedViewById(id.card_title);
//            Intrinsics.checkExpressionValueIsNotNull(var10000, "card_title");
//            var10000.setText((CharSequence)this.title);
//        }
//
//        Drawable var4 = this.iconDrawable;
//        if (var4 != null) {
//            Drawable var1 = var4;
//            int var3 = false;
//            RelativeLayout var5 = (RelativeLayout)this._$_findCachedViewById(id.card_header);
//            Intrinsics.checkExpressionValueIsNotNull(var5, "card_header");
//            var5.setVisibility(0);
//            ImageButton var6 = (ImageButton)this._$_findCachedViewById(id.card_icon);
//            Intrinsics.checkExpressionValueIsNotNull(var6, "card_icon");
//            var6.setBackground(var1);
//        }
//
//        this.setInnerView(this.innerViewRes);
//        if (Build.VERSION.SDK_INT >= 21) {
//            this.setElevation(Utils.convertDpToPixels(this.getContext(), 4.0F));
//        }
//
//        if (this.startExpanded) {
//            this.animDuration = 0L;
//            this.expand();
//        }
//
//        if (this.expandOnClick) {
//            ((CardView)this._$_findCachedViewById(id.card_layout)).setOnClickListener(this.defaultClickListener);
//            ((ImageButton)this._$_findCachedViewById(id.card_arrow)).setOnClickListener(this.defaultClickListener);
//        }
//
//    }
//
//    public final void expand() {
//        CardView var10000 = (CardView)this._$_findCachedViewById(id.card_layout);
//        Intrinsics.checkExpressionValueIsNotNull(var10000, "card_layout");
//        int initialHeight = var10000.getHeight();
//        if (!this.isMoving()) {
//            this.previousHeight = initialHeight;
//        }
//
//        ((CardView)this._$_findCachedViewById(id.card_layout)).measure(-1, -2);
//        var10000 = (CardView)this._$_findCachedViewById(id.card_layout);
//        Intrinsics.checkExpressionValueIsNotNull(var10000, "card_layout");
//        int targetHeight = var10000.getMeasuredHeight();
//        if (targetHeight - initialHeight != 0) {
//            this.animateViews(initialHeight, targetHeight - initialHeight, 1);
//        }
//
//    }
//
//    public final void collapse() {
//        CardView var10000 = (CardView)this._$_findCachedViewById(id.card_layout);
//        Intrinsics.checkExpressionValueIsNotNull(var10000, "card_layout");
//        int initialHeight = var10000.getMeasuredHeight();
//        if (initialHeight - this.previousHeight != 0) {
//            this.animateViews(initialHeight, initialHeight - this.previousHeight, 0);
//        }
//
//    }
//
//    private final void animateViews(final int initialHeight, final int distance, final int animationType) {
//      <undefinedtype> expandAnimation = new Animation() {
//            protected void applyTransformation(float interpolatedTime, @NotNull Transformation t) {
//                Intrinsics.checkParameterIsNotNull(t, "t");
//                if (interpolatedTime == 1.0F) {
//                    ExpandableCardView.this.isExpanding = false;
//                    ExpandableCardView.this.isCollapsing = false;
//                    ExpandableCardView.OnExpandedListener var10000 = ExpandableCardView.this.listener;
//                    if (var10000 != null) {
//                        ExpandableCardView.OnExpandedListener var3 = var10000;
//                        int var5 = false;
//                        if (animationType == 1) {
//                            var3.onExpandChanged((CardView)ExpandableCardView.this._$_findCachedViewById(id.card_layout), true);
//                        } else {
//                            var3.onExpandChanged((CardView)ExpandableCardView.this._$_findCachedViewById(id.card_layout), false);
//                        }
//                    }
//                }
//
//                CardView var6 = (CardView)ExpandableCardView.this._$_findCachedViewById(id.card_layout);
//                Intrinsics.checkExpressionValueIsNotNull(var6, "card_layout");
//                var6.getLayoutParams().height = animationType == 1 ? (int)((float)initialHeight + (float)distance * interpolatedTime) : (int)((float)initialHeight - (float)distance * interpolatedTime);
//                ((LinearLayout)ExpandableCardView.this._$_findCachedViewById(id.card_container)).requestLayout();
//                LinearLayout var7 = (LinearLayout)ExpandableCardView.this._$_findCachedViewById(id.card_container);
//                Intrinsics.checkExpressionValueIsNotNull(var7, "card_container");
//                var7.getLayoutParams().height = animationType == 1 ? (int)((float)initialHeight + (float)distance * interpolatedTime) : (int)((float)initialHeight - (float)distance * interpolatedTime);
//            }
//
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };
//        RotateAnimation arrowAnimation = animationType == 1 ? new RotateAnimation(0.0F, 180.0F, 1, 0.5F, 1, 0.5F) : new RotateAnimation(180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
//        arrowAnimation.setFillAfter(true);
//        arrowAnimation.setDuration(this.animDuration);
//        expandAnimation.setDuration(this.animDuration);
//        this.isExpanding = animationType == 1;
//        this.isCollapsing = animationType == 0;
//        this.startAnimation((Animation)expandAnimation);
//        Log.d("SO", "Started animation: " + (animationType == 1 ? "Expanding" : "Collapsing"));
//        ((ImageButton)this._$_findCachedViewById(id.card_arrow)).startAnimation((Animation)arrowAnimation);
//        this.isExpanded = animationType == 1;
//    }
//
//    public final void setOnExpandedListener(@NotNull ExpandableCardView.OnExpandedListener listener) {
//        Intrinsics.checkParameterIsNotNull(listener, "listener");
//        this.listener = listener;
//    }
//
//    public final void setOnExpandedListener(@NotNull final Function2 method) {
//        Intrinsics.checkParameterIsNotNull(method, "method");
//        this.listener = (ExpandableCardView.OnExpandedListener)(new ExpandableCardView.OnExpandedListener() {
//            public void onExpandChanged(@Nullable View v, boolean isExpanded) {
//                method.invoke(v, isExpanded);
//            }
//        });
//    }
//
//    public final void removeOnExpandedListener() {
//        this.listener = (ExpandableCardView.OnExpandedListener)null;
//    }
//
//    public final void setTitle(@StringRes int titleRes, @NotNull String titleText) {
//        Intrinsics.checkParameterIsNotNull(titleText, "titleText");
//        if (titleRes != -1) {
//            ((TextView)this._$_findCachedViewById(id.card_title)).setText(titleRes);
//        } else {
//            TextView var10000 = (TextView)this._$_findCachedViewById(id.card_title);
//            Intrinsics.checkExpressionValueIsNotNull(var10000, "card_title");
//            var10000.setText((CharSequence)titleText);
//        }
//
//    }
//
//    // $FF: synthetic method
//    public static void setTitle$default(ExpandableCardView var0, int var1, String var2, int var3, Object var4) {
//        if ((var3 & 1) != 0) {
//            var1 = -1;
//        }
//
//        if ((var3 & 2) != 0) {
//            var2 = "";
//        }
//
//        var0.setTitle(var1, var2);
//    }
//
//    public final void setIcon(@DrawableRes int drawableRes, @Nullable Drawable drawable) {
//        ImageButton var10000;
//        if (drawableRes != -1) {
//            this.iconDrawable = ContextCompat.getDrawable(this.getContext(), drawableRes);
//            var10000 = (ImageButton)this._$_findCachedViewById(id.card_icon);
//            Intrinsics.checkExpressionValueIsNotNull(var10000, "card_icon");
//            var10000.setBackground(this.iconDrawable);
//        } else {
//            var10000 = (ImageButton)this._$_findCachedViewById(id.card_icon);
//            Intrinsics.checkExpressionValueIsNotNull(var10000, "card_icon");
//            var10000.setBackground(drawable);
//            this.iconDrawable = drawable;
//        }
//
//    }
//
//    // $FF: synthetic method
//    public static void setIcon$default(ExpandableCardView var0, int var1, Drawable var2, int var3, Object var4) {
//        if ((var3 & 1) != 0) {
//            var1 = -1;
//        }
//
//        if ((var3 & 2) != 0) {
//            var2 = (Drawable)null;
//        }
//
//        var0.setIcon(var1, var2);
//    }
//
//    private final void setInnerView(int resId) {
//        ViewStub var10000 = (ViewStub)this.findViewById(id.card_stub);
//        Intrinsics.checkExpressionValueIsNotNull(var10000, "card_stub");
//        var10000.setLayoutResource(resId);
//        this.innerView = ((ViewStub)this.findViewById(id.card_stub)).inflate();
//    }
//
//    public void setOnClickListener(@Nullable OnClickListener l) {
//        ((ImageButton)this._$_findCachedViewById(id.card_arrow)).setOnClickListener(l);
//        super.setOnClickListener(l);
//    }
//
//    public ExpandableCardView(@NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        Intrinsics.checkParameterIsNotNull(context, "context");
//        super(context, attrs, defStyleAttr);
//        this.animDuration = (long)350;
//        this.defaultClickListener = (OnClickListener)(new OnClickListener() {
//            public final void onClick(View it) {
//                if (ExpandableCardView.this.isExpanded()) {
//                    ExpandableCardView.this.collapse();
//                } else {
//                    ExpandableCardView.this.expand();
//                }
//
//            }
//        });
//        this.initView(context);
//        if (attrs != null) {
//            int var6 = false;
//            this.initAttributes(context, attrs);
//        }
//
//    }
//
//    // $FF: synthetic method
//    public ExpandableCardView(Context var1, AttributeSet var2, int var3, int var4, DefaultConstructorMarker var5) {
//        if ((var4 & 2) != 0) {
//            var2 = (AttributeSet)null;
//        }
//
//        if ((var4 & 4) != 0) {
//            var3 = 0;
//        }
//
//        this(var1, var2, var3);
//        defaultClickListener = null;
//    }
//
//    public ExpandableCardView(@NotNull Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0, 4, (DefaultConstructorMarker)null);
//    }
//
//    public ExpandableCardView(@NotNull Context context) {
//        this(context, (AttributeSet)null, 0, 6, (DefaultConstructorMarker)null);
//    }
//
//    // $FF: synthetic method
//    public static final boolean access$isExpanding$p(ExpandableCardView $this) {
//        return $this.isExpanding;
//    }
//
//    // $FF: synthetic method
//    public static final boolean access$isCollapsing$p(ExpandableCardView $this) {
//        return $this.isCollapsing;
//    }
//
//    // $FF: synthetic method
//    public static final void access$setListener$p(ExpandableCardView $this, ExpandableCardView.OnExpandedListener var1) {
//        $this.listener = var1;
//    }
//
//    // $FF: synthetic method
//    public static final boolean access$isExpanded$p(ExpandableCardView $this) {
//        return $this.isExpanded;
//    }
//
//    // $FF: synthetic method
//    public static final void access$setExpanded$p(ExpandableCardView $this, boolean var1) {
//        $this.isExpanded = var1;
//    }
//
//    public View _$_findCachedViewById(int var1) {
//        if (this._$_findViewCache == null) {
//            this._$_findViewCache = new HashMap();
//        }
//
//        View var2 = (View)this._$_findViewCache.get(var1);
//        if (var2 == null) {
//            var2 = this.findViewById(var1);
//            this._$_findViewCache.put(var1, var2);
//        }
//
//        return var2;
//    }
//
//    public void _$_clearFindViewByIdCache() {
//        if (this._$_findViewCache != null) {
//            this._$_findViewCache.clear();
//        }
//
//    }
//
//    public interface OnExpandedListener {
//        void onExpandChanged(@Nullable View var1, boolean var2);
//    }
//
//    public static final class Companion {
//        private Companion() {
//        }
//
//        // $FF: synthetic method
//        public Companion(DefaultConstructorMarker $constructor_marker) {
//            this();
//        }
//    }
//}
