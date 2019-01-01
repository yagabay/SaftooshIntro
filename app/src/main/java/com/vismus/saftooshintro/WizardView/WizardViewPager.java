package com.vismus.saftooshintro.WizardView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

public class WizardViewPager extends ViewPager {

    static final double SCROLL_DURATION = 5;
    Context _context;

    public WizardViewPager(Context context) {
        super(context);
        _context = context;
        setScrollDuration(SCROLL_DURATION);
    }

    public WizardViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScrollDuration(SCROLL_DURATION);
        _context = context;
    }

    /* disable manual swipe */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
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
