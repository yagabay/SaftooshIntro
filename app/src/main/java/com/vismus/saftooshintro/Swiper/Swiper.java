package com.vismus.saftooshintro.Swiper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class Swiper implements SwiperFragment.Listener{

    SwiperFragment _swiperFragment;
    Listener _listener;

    public Swiper(Context context, int frameLayoutId, Fragment origFragment, Fragment destFragment){
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        _swiperFragment = new SwiperFragment();
        fragmentManager
                .beginTransaction()
                .remove(origFragment)
                .replace(frameLayoutId, _swiperFragment)
                .commitNow();
        _swiperFragment.setOrigAndDest(origFragment, destFragment);
        _swiperFragment.setListener(this);
    }

    @Override
    public void onSwipeComplete(){
        if(_listener != null) {
            _listener.onSwipeComplete();
        }
    }

    public void swipe(){
        _swiperFragment.swipe();
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    /* inner classes */

    public interface Listener{
        void onSwipeComplete();
    }

}

// HOW TO FIND FRAGMENT:
// Fragment origFragment = _fragmentManager.findFragmentById(frameLayoutId);