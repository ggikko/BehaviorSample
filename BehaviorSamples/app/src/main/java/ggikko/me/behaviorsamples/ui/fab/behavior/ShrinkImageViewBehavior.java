package ggikko.me.behaviorsamples.ui.fab.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import ggikko.me.behaviorsamples.ui.fab.widget.CustomImageView;

/**
 * Created by ggikko on 2016. 10. 13..
 */

//this source ref "https://github.com/cstew/CustomBehavior"
public class ShrinkImageViewBehavior extends CoordinatorLayout.Behavior<CustomImageView> {

    public ShrinkImageViewBehavior() { }

    public ShrinkImageViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * callBack 순서
     * layoutDependsOn, onDependentViewChanged -> onDependentViewRemoved
     */
    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, CustomImageView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        Log.e("ggikko", "ShrinkImageViewBehavior's onMeasureChild");
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, CustomImageView child, int layoutDirection) {
        Log.e("ggikko", "ShrinkImageViewBehavior's onLayoutChild");
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    /**
     * Determine whether the supplied child view has another specific sibling view as a layout dependency.
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CustomImageView child, View dependency) {
        Log.e("ggikko", "ShrinkImageViewBehavior's layoutDependsOn");
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    /**
     * Respond to a change in a child's dependent view
     * 여기서 CIV는 CustomImageView의 줄임말
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CustomImageView child, View dependency) {
        float translationY = getCIVTranslationYForSnackBar(parent, child);
        float percentComplete = -translationY / dependency.getHeight();
        float scaleFactor = 1 - percentComplete;

        child.setScaleX(scaleFactor);
        child.setScaleY(scaleFactor);
        return false;
    }

    /**
     * Respond to a child's dependent view being removed.
     * @param parent
     * @param child
     * @param dependency
     */
    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, CustomImageView child, View dependency) {
        Log.e("ggikko", "ShrinkImageViewBehavior's onDependentViewRemoved");
        super.onDependentViewRemoved(parent, child, dependency);
    }

    /**
     * dependency 체크하고 minOffset 반환
     * @param parent
     * @param child
     * @return
     */
    private float getCIVTranslationYForSnackBar(CoordinatorLayout parent, CustomImageView child) {
        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(child);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(child, view)) {
                minOffset = Math.min(minOffset, ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }
        return minOffset;
    }
}