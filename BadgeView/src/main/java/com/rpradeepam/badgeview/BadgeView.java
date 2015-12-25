package com.rpradeepam.badgeview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class BadgeView extends FrameLayout {
    private String badgeValue;
    private int badgeBgColor, badgeColor;
    private Drawable badgeIcon;
    private int badgePosition;

    private TextView badge;
    private ImageView image;

    //   private TextPaint mTextPaint;
    private GradientDrawable badgeDrawable;
    private ShapeDrawable buttonDrawable, touchDrawable;
    //  private ColorDrawable foreground;
    private StateListDrawable foreground;
    private float mTextWidth;
    private float mTextHeight;

    public BadgeView(Context context) {
        super(context);
        init(context, null, R.attr.badgeViewStyle);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, R.attr.badgeViewStyle);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.BadgeView, defStyle, R.style.BadgeView_Default);

        badgeValue = a.getString(
                R.styleable.BadgeView_badgeValue);
        badgeBgColor = a.getColor(
                R.styleable.BadgeView_bgColor,
                badgeBgColor);
        badgeColor = a.getColor(
                R.styleable.BadgeView_badgeColor,
                badgeColor);
        badgeIcon = a.getDrawable(R.styleable.BadgeView_buttonIcon);
        badgePosition = a.getInt(R.styleable.BadgeView_badgePosition, 0);

        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.badge, this, true);
        buttonDrawable = new ShapeDrawable(new OvalShape());


        buttonDrawable.setIntrinsicHeight(100);
        buttonDrawable.setIntrinsicWidth(100);

        setBackground(buttonDrawable);
        badgeDrawable = new GradientDrawable();
        badgeDrawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));


        this.badge = (TextView) findViewById(R.id.badgeText);
        this.image = (ImageView) findViewById(R.id.badgeImage);

        badge.setBackground(badgeDrawable);
        // Set up a default TextPaint object
        //  mTextPaint = new TextPaint();
        // mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

        touchDrawable = new ShapeDrawable(new OvalShape());
        touchDrawable.setIntrinsicHeight(100);
        touchDrawable.setIntrinsicWidth(100);
        touchDrawable.getPaint().setColor(Color.GRAY);
        touchDrawable.getPaint().setAlpha(100);
        foreground = new StateListDrawable();
        foreground.addState(new int[]{android.R.attr.state_pressed}, touchDrawable);
        // foreground.addState(StateSet.WILD_CARD, @null);
        //   foreground.setAlpha(100);
        //  foreground.setColor(Color.GRAY);

        setForeground(foreground);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {

        //   mTextPaint.setColor(badgeBgColor);

        if (badgeValue != null && !badgeValue.equals("")) {
            badge.setText(badgeValue);
            badge.setVisibility(VISIBLE);
        } else {
            badge.setVisibility(INVISIBLE);
        }
        buttonDrawable.getPaint().setColor(badgeBgColor);
        badgeDrawable.setColor(badgeColor);
        image.setImageDrawable(badgeIcon);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        if (badgePosition == 0) {
            params.gravity = Gravity.TOP | Gravity.RIGHT;
        } else {
            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        }

        badge.setLayoutParams(params);

    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final float dim = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        setMeasuredDimension((int)dim|MeasureSpec.EXACTLY, (int)dim|MeasureSpec.EXACTLY);
    }*/

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    public String getBadgeValue() {
        return badgeValue;
    }

    public void setBadgeValue(String badgeValue) {
        this.badgeValue = badgeValue;
        this.badge.setVisibility(VISIBLE);
        invalidateTextPaintAndMeasurements();
    }

    public int getBadgeColor() {
        return badgeBgColor;
    }

    public void setBadgeColor(int badgeColor) {
        this.badgeBgColor = badgeColor;
        invalidateTextPaintAndMeasurements();
    }

    public Drawable getBadgeIcon() {
        return badgeIcon;
    }

    public void setBadgeIcon(Drawable badgeIcon) {
        this.badgeIcon = badgeIcon;
        invalidateTextPaintAndMeasurements();
    }

    public int getBadgePosition() {
        return badgePosition;
    }

    public void setBadgePosition(int badgePosition) {
        this.badgePosition = badgePosition;
        invalidateTextPaintAndMeasurements();
    }

    public int getBadgeBgColor() {
        return badgeBgColor;
    }

    public void setBadgeBgColor(int badgeBgColor) {
        this.badgeBgColor = badgeBgColor;
        invalidateTextPaintAndMeasurements();
    }
}
