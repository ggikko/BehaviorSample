package ggikko.me.behaviorsamples.util;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by ggikko on 2016. 10. 13..
 */

//this source ref "https://github.com/cstew/CustomBehavior"
public class ShrinkBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public ShrinkBehavior() { }

    public ShrinkBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * callBack 순서
     * layoutDependsOn -> onDependentViewChanged
     */

    /**
     * Determine whether the supplied child view has another specific sibling view as a layout dependency.
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    /**
     * Respond to a change in a child's dependent view
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float translationY = getFabTranslationYForSnackBar(parent, child);
        float percentComplete = -translationY / dependency.getHeight();
        float scaleFactor = 1 - percentComplete;

        child.setScaleX(scaleFactor);
        child.setScaleY(scaleFactor);
        return false;
    }

    /**
     * dependency 체크하고 minOffset 반환
     * @param parent
     * @param fab
     * @return
     */
    private float getFabTranslationYForSnackBar(CoordinatorLayout parent, FloatingActionButton fab) {

        float minOffset = 0;
        final List<View> dependencies = parent.getDependencies(fab);
        for (int i = 0, z = dependencies.size(); i < z; i++) {
            final View view = dependencies.get(i);
            if (view instanceof Snackbar.SnackbarLayout && parent.doViewsOverlap(fab, view)) {
                minOffset = Math.min(minOffset, ViewCompat.getTranslationY(view) - view.getHeight());
            }
        }
        return minOffset;
    }
}