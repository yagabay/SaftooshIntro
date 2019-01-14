package com.vismus.saftooshintro.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vismus.saftooshintro.R;
import com.vismus.saftooshintro.WizardView.WizardPage;
import com.vismus.saftooshintro.WizardView.WizardView;

import java.util.ArrayList;
import java.util.List;

public class SetUpWizardFragment extends Fragment implements WizardView.Listener{

    WizardView _wizardView;

    Listener _listener; // listened by MainActivity

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_set_up_wizard, container, false);
        initWizardView(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof SplashFragment.Listener){
            _listener = (SetUpWizardFragment.Listener) context;
        }
    }

    public void onWizardFinished(){
        if(_listener != null){
            _listener.onSetUpComplete();
        }
    }

    public WizardPage getCurrentPage(){
        return _wizardView.getCurrentPage();
    }

    /* inner classes */

    public interface Listener{
        void onSetUpComplete();
    }

    /* private methods */

    void initWizardView(ViewGroup rootView){
        _wizardView = rootView.findViewById(R.id.set_up_wizard_view);
        List<WizardPage> pages = new ArrayList<>();
        pages.add(new IntroPart1WizardPage());
        pages.add(new IntroPart2WizardPage());
        pages.add(new IntroPart3WizardPage());
        pages.add(new EnterPhoneNumberWizardPage());
        pages.add(new EnterVerificationCodeWizardPage());
        SetProfilePictureWizardPage setProfilePictureWizardPage = new SetProfilePictureWizardPage();
        setProfilePictureWizardPage.removeButton(WizardView.NavButtonType.BACK);
        pages.add(setProfilePictureWizardPage);
        pages.add(new ProfileReadyWizardPage());
        _wizardView.setPages(pages);
        _wizardView.setListener(this);
    }

}


