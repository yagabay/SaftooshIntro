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

public class SignUpFragment  extends Fragment {

    // views
    EditText _edtEmail;
    EditText _edtPassword;
    EditText _edtSecurityCode;
    Button _btnSignUp;
    Button _btnAlreadyHaveAccount;

    Listener _listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up, container, false);
        initViews(rootView);
        return rootView;
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    /* inner classes */

    class OnSignUpButtonClick implements Button.OnClickListener{
        @Override
        public void onClick(View view){
            _listener.signUpButtonClicked(_edtEmail.toString(), _edtPassword.toString(), _edtSecurityCode.toString());
        }
    }

    class OnAlreadyHaveAccountButtonClick implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            if(_listener != null) {
                _listener.alreadyHaveAccountButtonClicked();
            }
        }
    }

    public interface Listener{
        void signUpButtonClicked(String email, String password, String securityCode);
        void alreadyHaveAccountButtonClicked();
    }

    /* private methods */

    void initViews(ViewGroup rootView){
        _edtEmail = rootView.findViewById(R.id.edt_email);
        _edtPassword = rootView.findViewById(R.id.edt_password);
        _edtSecurityCode = rootView.findViewById(R.id.edt_security_code);
        _btnSignUp = rootView.findViewById(R.id.btn_sign_up);
        _btnSignUp.setOnClickListener(new OnSignUpButtonClick());
        _btnAlreadyHaveAccount = rootView.findViewById(R.id.btn_already_have_account);
        _btnAlreadyHaveAccount.setOnClickListener(new OnAlreadyHaveAccountButtonClick());
    }

}

