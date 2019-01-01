package com.vismus.saftooshintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vismus.saftooshintro.Swiper.Swiper;

public class MainActivity extends AppCompatActivity implements SignInUpFragment.Listener, SetUpWizardFragment.Listener{

    FrameLayout _layMain;
    SignInUpFragment _signInUpFragment;
    SetUpWizardFragment _setUpWizardFragment;
    GalleryFragment _galleryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _layMain = findViewById(R.id.lay_main);
        _signInUpFragment = new SignInUpFragment();
        _signInUpFragment.setListener(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_main, _signInUpFragment)
                .commitNow();
    }

    @Override
    public void onSignInUpComplete(){
        _setUpWizardFragment = new SetUpWizardFragment();
        _setUpWizardFragment.setListener(this);
        Swiper swiper = new Swiper(this, R.id.lay_main, _signInUpFragment.getCurrentItem(), _setUpWizardFragment);
        swiper.swipe();
    }

    @Override
    public void onWizardComplete(){
        _galleryFragment = new GalleryFragment();
        Swiper swiper = new Swiper(this, R.id.lay_main, _setUpWizardFragment.getCurrentPage(), _galleryFragment);
        swiper.swipe();
    }
}
