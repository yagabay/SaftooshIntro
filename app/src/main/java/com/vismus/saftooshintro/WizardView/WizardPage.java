package com.vismus.saftooshintro.WizardView;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class WizardPage extends Fragment {

    protected List<WizardView.NavButtonType> _buttons; // TODO: replace with Set

    public WizardPage(){
        _buttons = new ArrayList<>();
        addButton(WizardView.NavButtonType.BACK);
        addButton(WizardView.NavButtonType.NEXT);
    }

    /* override to provide actual page input */
    public void init(Bundle data){}

    /* override to receive actual page output */
    public Bundle term(){
        return new Bundle();
    }

    public void addButton(WizardView.NavButtonType type){
        if(!_buttons.contains(type)) {
            _buttons.add(type);
        }
    }

    public void removeButton(WizardView.NavButtonType type){
        _buttons.remove(type);
    }

    public boolean isButtonExist(WizardView.NavButtonType type){
        for(Iterator<WizardView.NavButtonType> it = _buttons.iterator(); it.hasNext(); ) {
            if (it.next().equals(type)){
                return true;
            }
        }
        return false;
    }

}
