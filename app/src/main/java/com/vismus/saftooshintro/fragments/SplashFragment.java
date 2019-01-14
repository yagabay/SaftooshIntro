package com.vismus.saftooshintro.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vismus.saftooshintro.R;

public class SplashFragment extends Fragment {

    static final int SPLASH_DURATION = 2000;

    Listener _listener; // listened by MainActivity

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_splash, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _listener.onSplashEnded();
            }
        }, SPLASH_DURATION);
        return rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Listener){
            _listener = (Listener) context;
        }
    }

    public interface Listener {
        void onSplashEnded();
    }

}
