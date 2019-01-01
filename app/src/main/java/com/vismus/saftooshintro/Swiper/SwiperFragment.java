package com.vismus.saftooshintro.Swiper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.vismus.saftooshintro.R;

public class SwiperFragment extends Fragment{

    SwiperViewPager _viewPager;
    SwiperViewPagerAdapter _viewPagerAdapter;
    Listener _listener;
    boolean _swipeCalled;
    boolean _viewPagerDrawn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_swiper, container, false);
        initSwiperViewPager(rootView);
        _swipeCalled = false;
        _viewPagerDrawn = false;
        return rootView;
    }

    public void setOrigAndDest(Fragment origFragment, Fragment destFragment){
        _viewPagerAdapter.addItem(origFragment);
        _viewPagerAdapter.addItem(destFragment);
        _viewPagerAdapter.notifyDataSetChanged();
    }

    public void swipe(){
        _swipeCalled = true;
        if(_viewPagerDrawn) {
            _viewPager.setCurrentItem(1);
        }
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    /* inner classes */

    interface Listener{
        void onSwipeComplete();
    }

    class ViewPagerOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            _viewPagerDrawn = true;
            if(_swipeCalled){
                _viewPager.setCurrentItem(1);
            }
        }
    }

    class OnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position){}

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_IDLE){
                if(_listener != null) {
                    _listener.onSwipeComplete();
                }
            }
        }
    }

    /* private methods */

    void initSwiperViewPager(ViewGroup rootView){
        _viewPager = rootView.findViewById(R.id.swiper_view_pager);
        _viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewPagerOnGlobalLayoutListener());
        _viewPager.addOnPageChangeListener(new OnPageChangeListener());
        _viewPagerAdapter = new SwiperViewPagerAdapter(getActivity().getSupportFragmentManager());
        _viewPager.setAdapter(_viewPagerAdapter);
    }
}
