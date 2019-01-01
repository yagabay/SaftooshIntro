package com.vismus.saftooshintro.SignInUpView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

public class VerticalViewPager extends ViewPager {

    static final double SCROLL_DURATION = 5;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setPageTransformer(true, new VerticalPageTransformer());
        setScrollDuration(SCROLL_DURATION);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) {
                view.setAlpha(0);

            }
            else if (position <= 1){
                view.setAlpha(1);
                view.setTranslationX(view.getWidth() * -position);
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            }
            else {
                view.setAlpha(0);
            }
        }
    }

    /* customize scroll duration */

    void setScrollDuration(double duration) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            Field sInterpolator = ViewPager.class.getDeclaredField("sInterpolator");
            sInterpolator.setAccessible(true);
            ScrollerCustomDuration scroller = new ScrollerCustomDuration(getContext(), (Interpolator) sInterpolator.get(null));
            scroller.setScrollDurationFactor(duration);
            mScroller.set(this, scroller);
        } catch (Exception e) {
        }
    }

    class ScrollerCustomDuration extends Scroller {

        double _scrollFactor = 1;

        public ScrollerCustomDuration(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public void setScrollDurationFactor(double scrollFactor) {
            _scrollFactor = scrollFactor;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, (int) (duration * _scrollFactor));
        }

    }
}

/* to enabled swipe (probably) */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev){
//        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
//        swapXY(ev);
//        return intercepted;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(swapXY(ev));
//    }
