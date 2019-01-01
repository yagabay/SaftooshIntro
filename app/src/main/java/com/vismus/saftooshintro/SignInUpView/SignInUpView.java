package com.vismus.saftooshintro.SignInUpView;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.vismus.saftooshintro.R;

import java.util.ArrayList;
import java.util.List;

public class SignInUpView extends LinearLayout implements SignInFragment.Listener, SignUpFragment.Listener {

    VerticalViewPager _viewPager;
    VerticalViewPagerAdapter _viewPagerAdapter;
    Listener _listener;
    SignUpFragment _signUpFragment;
    SignInFragment _signInFragment;

    public SignInUpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.sign_in_up_view, this);
        _viewPager = findViewById(R.id.ver_view_pager);
        _viewPagerAdapter = new VerticalViewPagerAdapter(((FragmentActivity) context).getSupportFragmentManager());
        _viewPager.setAdapter(_viewPagerAdapter);
        List<Fragment> items = new ArrayList<>();
        _signUpFragment = new SignUpFragment();
        _signUpFragment.setListener(this);
        items.add(_signUpFragment);
        _signInFragment = new SignInFragment();
        _signInFragment.setListener(this);
        items.add(_signInFragment);
        _viewPagerAdapter.setItems(items);
        _viewPagerAdapter.notifyDataSetChanged();
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    public Fragment getCurrentItem(){
        if(_viewPager.getCurrentItem() == 0) {
            return _signUpFragment;
        }
        else{
            return _signInFragment;
        }
    }

    @Override
    public void signUpButtonClicked(String email, String password, String securityCode){
        signUp(email, password, securityCode);
    }

    @Override
    public void alreadyHaveAccountButtonClicked() {
        _viewPager.setCurrentItem(1);
    }

    @Override
    public void signInButtonClicked(String email, String password){
        signIn(email, password);
    }

    @Override
    public void createAccountButtonClicked(){
        _viewPager.setCurrentItem(0);
    }

    void signUp(String email, String password, String securityCode){
        _viewPagerAdapter.clear();
        _listener.onSignUpComplete();
    }

    void signIn(String email, String password){
        _viewPagerAdapter.clear();
        _listener.onSignInComplete();
    }

    public interface Listener{
        void onSignUpComplete();
        void onSignInComplete();
    }

}

