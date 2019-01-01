package com.vismus.saftooshintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vismus.saftooshintro.WizardView.WizardPage;
import com.vismus.saftooshintro.WizardView.WizardView;

import java.util.ArrayList;
import java.util.List;

public class SetUpWizardFragment extends Fragment implements WizardView.Listener{

    WizardView _wizardView;
    Listener _listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_set_up_wizard, container, false);
        initWizardView(rootView);
        return rootView;
    }

    @Override
    public void onWizardComplete(){
        if(_listener != null) {
            _listener.onWizardComplete();
        }
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    public Fragment getCurrentPage(){
        return _wizardView.getCurrentPage();
    }

    public interface Listener{
        void onWizardComplete();
    }

    /* private methods */

    void initWizardView(ViewGroup rootView){
        _wizardView = rootView.findViewById(R.id.wizard_view);
        List<WizardPage> pages = new ArrayList<>();
        pages.add(new WelcomeWizardPage());
        pages.add(new SetProfilePictureWizardPage());
        _wizardView.setPages(pages);
        _wizardView.setListener(this);
    }


}
