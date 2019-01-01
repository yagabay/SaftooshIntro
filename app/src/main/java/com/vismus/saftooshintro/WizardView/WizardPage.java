package com.vismus.saftooshintro.WizardView;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class WizardPage extends Fragment {

    abstract public void setData(Bundle data);

    /* override to create actual page output */
    public Bundle getData(){
        return null;
    }

}
