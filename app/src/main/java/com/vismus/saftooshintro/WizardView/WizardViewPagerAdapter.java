package com.vismus.saftooshintro.WizardView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

class WizardViewPagerAdapter extends FragmentStatePagerAdapter {

    List<WizardPage> _items;

    public WizardViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        _items = new ArrayList<>();
    }

    @Override
    public WizardPage getItem(int position) {
        return _items.get(position);
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    public void setItems(List<WizardPage> items){
        _items = items;
    }

}
