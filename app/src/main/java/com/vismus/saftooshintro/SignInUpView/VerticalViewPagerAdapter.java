package com.vismus.saftooshintro.SignInUpView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

class VerticalViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> _items;

    public VerticalViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        _items = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return _items.get(position);
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    public void setItems(List<Fragment> items) {
        _items = items;
    }

    public void clear(){
        _items.clear();
    }

}

