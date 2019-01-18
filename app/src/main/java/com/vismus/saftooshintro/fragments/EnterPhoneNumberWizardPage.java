package com.vismus.saftooshintro.fragments;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.vismus.saftooshintro.R;
import com.vismus.saftooshintro.WizardView.WizardView;

public class EnterPhoneNumberWizardPage extends WizardView.WizardPage implements WizardView.Listener{

    EditText _edtPhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.enter_phone_number_wizard_page, container, false);
        _edtPhoneNumber = rootView.findViewById(R.id.edt_phone_number);
        _edtPhoneNumber.addTextChangedListener(new PhoneNumberEditTextWatcher());
        return rootView;
    }


    @Override
    public void init(Bundle args) {
        setNextButtonEnabled(isValidPhoneNumber(_edtPhoneNumber.getText().toString()));
    }

        @Override
    public Bundle term() {
        if(!isValidPhoneNumber(_edtPhoneNumber.getText().toString())){
            showDialogInvalidPhoneNumber();
            return null;
        }
        Bundle data = new Bundle();
        data.putString("phoneNumber", _edtPhoneNumber.getText().toString());
        return data;
    }

    public void onWizardFinished(){} // CHECK

    /* private methods */

    boolean isValidPhoneNumber(String string){
        if(string.length() != 10){
            return false;
        }
        if(!string.startsWith("05")){
            return false;
        }
        return true;
    }

    void showDialogInvalidPhoneNumber() {
        AlertDialog.Builder dlgAlertBuilder = new AlertDialog.Builder(getContext());
        dlgAlertBuilder.setMessage("Invalid phone number entered");
        dlgAlertBuilder.setPositiveButton("OK", null);
        AlertDialog dlgAlert = dlgAlertBuilder.create();
        dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlgAlert.show();
    }

    class PhoneNumberEditTextWatcher implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void afterTextChanged(Editable editable) {
            setNextButtonEnabled(isValidPhoneNumber(_edtPhoneNumber.getText().toString()));
        }
    }
}


