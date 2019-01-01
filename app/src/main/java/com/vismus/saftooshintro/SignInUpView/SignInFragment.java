package com.vismus.saftooshintro.SignInUpView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vismus.saftooshintro.R;

public class SignInFragment extends Fragment {

    // views
    EditText _edtEmail;
    EditText _edtPassword;
    Button _btnSignIn;
    Button _btnCreateNewAccount;

    Listener _listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in, container, false);
        initViews(rootView);
        return rootView;
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    /* inner classes */

    class OnSignInButtonButtonClick implements Button.OnClickListener{
        @Override
        public void onClick(View view){
            if(_listener != null) {
                _listener.signInButtonClicked(_edtEmail.toString(), _edtPassword.toString());
            }
        }
    }

    class OnCreateNewAccountButtonClick implements Button.OnClickListener{
        @Override
        public void onClick(View view){
            if(_listener != null) {
                _listener.createAccountButtonClicked();
            }
        }
    }

    public interface Listener{
        void signInButtonClicked(String email, String password);
        void createAccountButtonClicked();
    }

    /* private methods */

    void initViews(ViewGroup rootView){
        _edtEmail = rootView.findViewById(R.id.edt_email);
        _edtPassword = rootView.findViewById(R.id.edt_password);
        _btnSignIn = rootView.findViewById(R.id.btn_sign_in);
        _btnCreateNewAccount = rootView.findViewById(R.id.btn_create_new_account);
        _btnSignIn.setOnClickListener(new OnSignInButtonButtonClick());
        _btnCreateNewAccount.setOnClickListener(new OnCreateNewAccountButtonClick());
    }

}

