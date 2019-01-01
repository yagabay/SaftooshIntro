package com.vismus.saftooshintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vismus.saftooshintro.SignInUpView.SignInUpView;

public class SignInUpFragment extends Fragment implements SignInUpView.Listener {

    SignInUpView _signInUpView;
    Listener _listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in_up, container, false);
        _signInUpView = rootView.findViewById(R.id.sign_in_up_view);
        _signInUpView.setListener(this);
        return rootView;
    }

    @Override
    public void onSignUpComplete(){
        if(_listener != null) {
            _listener.onSignInUpComplete();
        }
    }

    @Override
    public void onSignInComplete(){
        if(_listener != null) {
            _listener.onSignInUpComplete();
        }
    }

    public Fragment getCurrentItem(){
        return _signInUpView.getCurrentItem();
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    /* inner classes */

    interface Listener{
        void onSignInUpComplete();
    }

}
