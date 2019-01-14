package com.vismus.saftooshintro.fragments;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.vismus.saftooshintro.R;
import com.vismus.saftooshintro.WizardView.WizardPage;
import com.vismus.saftooshintro.WizardView.WizardView;

public class EnterPhoneNumberWizardPage extends WizardPage implements WizardView.Listener{

    EditText _edtPhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.enter_phone_number_wizard_page, container, false);
        _edtPhoneNumber = rootView.findViewById(R.id.edt_phone_number);
        _edtPhoneNumber.setText("0526007522"); // for debugging
        return rootView;
    }

    @Override
    public Bundle term() {
        if(!isValidPhoneNumber(_edtPhoneNumber.getText().toString())){
            showDialogInvalidPhoneNumber();
            return null;
        }
        Bundle data = new Bundle();
        data.putString("PhoneNumber", _edtPhoneNumber.getText().toString());
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

}


