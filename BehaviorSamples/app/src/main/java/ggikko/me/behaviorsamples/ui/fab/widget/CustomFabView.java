package ggikko.me.behaviorsamples.ui.fab.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by ggikko on 2016. 10. 14..
 */

public class CustomFabView extends FloatingActionButton {

    public CustomFabView(Context context) {
        super(context);
    }

    public CustomFabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e("ggikko", "CustomFabView's onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("ggikko", "CustomFabView's onLayout");
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("ggikko", "CustomFabView's onDraw");
        super.onDraw(canvas);
    }
}
