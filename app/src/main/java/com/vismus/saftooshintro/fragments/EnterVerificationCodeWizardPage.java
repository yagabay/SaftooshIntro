package com.vismus.saftooshintro.fragments;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vismus.saftooshintro.PreferenceData;
import com.vismus.saftooshintro.R;
import com.vismus.saftooshintro.WizardView.WizardView;

import java.util.Random;

public class EnterVerificationCodeWizardPage extends WizardView.WizardPage {

    // for debugging
    enum RunningMode{
        ON_DEVICE,
        ON_EMULATOR
    }

    TextView _txvPhoneNumber;
    EditText _edtVerificationCode;
    Button _btnSendAgain;
    Button _btnChangePhoneNumber;

    String _phoneNumber;
    String _verificationCode;

    PreferenceData _prefData = PreferenceData.getInstance(getContext());
    final static RunningMode _runningMode = RunningMode.ON_EMULATOR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.enter_verification_code_wizard_page, container, false);
        _txvPhoneNumber = rootView.findViewById(R.id.txv_phone_num);
        _edtVerificationCode = rootView.findViewById(R.id.edt_verification_code);
        _edtVerificationCode.addTextChangedListener(new VerificationCodeEditTextWatcher());
        _btnSendAgain = rootView.findViewById(R.id.btn_send_again);
        _btnSendAgain.setOnClickListener(new OnSendAgainButtonClicked());
        _btnChangePhoneNumber = rootView.findViewById(R.id.btn_change_phone_num);
        _btnChangePhoneNumber.setOnClickListener(new OnChangePhoneNumberButtonClicked());
        return rootView;
    }

    @Override
    public void init(Bundle data) {
        _phoneNumber = data.getString("phoneNumber");
        _txvPhoneNumber.setText(_phoneNumber);
        setNextButtonEnabled(false);
        sendVerificationCodeMessage();
    }

    @Override
    public Bundle term() {
        if(!_edtVerificationCode.getText().toString().equals(_verificationCode)){
            showDialogWrongVerificationCode();
            return null;
        }
        _prefData.setSignedIn(true);
        return new Bundle();
    }

    /* inner classes */

    class OnSendAgainButtonClicked implements View.OnClickListener{

        @Override
        public void onClick(View view){
            sendVerificationCodeMessage();
        }
    }

    class OnChangePhoneNumberButtonClicked implements View.OnClickListener{

        @Override
        public void onClick(View view){
            back();
        }
    }

    /* private methods */

    void sendVerificationCodeMessage(){
        _verificationCode = createVerificationCode();
        String message = "Your Saftoosh verification code is: " + _verificationCode;
        if(_runningMode == RunningMode.ON_DEVICE) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(_phoneNumber, null, message, null, null);
        }
        else{
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    String createVerificationCode(){
        String verificationCode = new String();
        for(int i = 0; i < 4; ++i){
            verificationCode += getRandomDigit();
        }
        return verificationCode;
    }

    int getRandomDigit(){
        Random rand = new Random();
        return rand.nextInt(10);
    }

    void showDialogWrongVerificationCode() {
        AlertDialog.Builder dlgAlertBuilder = new AlertDialog.Builder(getContext());
        dlgAlertBuilder.setMessage("Wrong verification code entered");
        dlgAlertBuilder.setPositiveButton("OK", null);
        AlertDialog dlgAlert = dlgAlertBuilder.create();
        dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlgAlert.show();
    }

    class VerificationCodeEditTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            setNextButtonEnabled(_edtVerificationCode.getText().toString().length() == 4);
        }
    }

}
