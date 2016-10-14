package ggikko.me.behaviorsamples.ui.scroll.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by ggikko on 2016. 10. 13..
 */

//this source ref https://github.com/bherbst/QuickReturnFooterSample
public class CustomScrollBehavior extends CoordinatorLayout.Behavior<View> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private int mDySinceDirectionChange;
    private boolean mIsShowing;
    private boolean mIsHiding;

    public CustomScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        if (dy > 0 && mDySinceDirectionChange < 0 || dy < 0 && mDySinceDirectionChange > 0) {
            child.animate().cancel();
            mDySinceDirectionChange = 0;
        }

        mDySinceDirectionChange += dy;

        if (mDySinceDirectionChange > child.getHeight() && child.getVisibility() == View.VISIBLE && !mIsHiding) {
            hide(child);
        } else if (mDySinceDirectionChange < 0 && child.getVisibility() == View.GONE && !mIsShowing) {
            show(child);
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    private void hide(final View view) {
        mIsHiding = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(view.getHeight())
                .setInterpolator(INTERPOLATOR)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animator) {}
            @Override public void onAnimationEnd(Animator animator) {
                mIsHiding = false;
                view.setVisibility(View.GONE);
            }
            @Override public void onAnimationCancel(Animator animator) {
                mIsHiding = false;
                if (!mIsShowing) {
                    show(view);
                }
            }
            @Override public void onAnimationRepeat(Animator animator) {}
        });

        animator.start();
    }

    private void show(final View view) {
        mIsShowing = true;
        ViewPropertyAnimator animator = view.animate()
                .translationY(0)
                .setInterpolator(INTERPOLATOR)
                .setDuration(200);

        animator.setListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }
            @Override public void onAnimationEnd(Animator animator) {
                mIsShowing = false;
            }
            @Override public void onAnimationCancel(Animator animator) {
                mIsShowing = false;
                if (!mIsHiding) {
                    hide(view);
                }
            }
            @Override public void onAnimationRepeat(Animator animator) {}
        });

        animator.start();
    }
}
