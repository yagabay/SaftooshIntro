package com.vismus.saftooshintro.Swiper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class Swiper implements SwiperFragment.Listener{

    SwiperFragment _swiperFragment;
    Listener _listener;

    public Swiper(Context context, int frameLayoutId, Fragment origFragment, Fragment destFragment){
        _swiperFragment = new SwiperFragment();
        ((FragmentActivity) context).getSupportFragmentManager()
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

    /* inner classes */

    public interface Listener{
        void onSwipeComplete();
    }

}

// HOW TO FIND FRAGMENT:
// Fragment origFragment = _fragmentManager.findFragmentById(frameLayoutId);