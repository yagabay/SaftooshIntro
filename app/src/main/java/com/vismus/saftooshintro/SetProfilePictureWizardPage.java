package com.vismus.saftooshintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vismus.saftooshintro.WizardView.WizardPage;

public class SetProfilePictureWizardPage extends WizardPage{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.set_profile_picture_wizard_page, container, false);
        return rootView;
    }

    @Override
    public void setData(Bundle data){}

}
