package com.vismus.saftooshintro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vismus.saftooshintro.R;
import com.vismus.saftooshintro.WizardView.WizardPage;

public class IntroPart3WizardPage extends WizardPage {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.intro_part3_wizard_page, container, false);
        return rootView;
    }
}
