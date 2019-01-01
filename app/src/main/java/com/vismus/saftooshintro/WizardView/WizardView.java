package com.vismus.saftooshintro.WizardView;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.vismus.saftooshintro.R;

import java.util.List;

public class WizardView extends LinearLayout{

    // views
    WizardViewPager _wizardViewPager;
    Button _btnPrev;
    Button _btnNext;

    WizardViewPagerAdapter _wizardViewPagerAdapter;
    Listener _listener;

    public WizardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.wizard_view, this);
        initViews(context);
        updateNavigationButtons();
    }

    public void setPages(List<WizardPage> pages){
        _wizardViewPagerAdapter.setItems(pages);
        _wizardViewPagerAdapter.notifyDataSetChanged();
        updateNavigationButtons();
    }

    public void prevPage(){
        int currentItem = _wizardViewPager.getCurrentItem();
        assert(currentItem != 0);
        _wizardViewPager.setCurrentItem(currentItem - 1);
        updateNavigationButtons();
    }

    public void nextPage(){
        int currPageIndex = _wizardViewPager.getCurrentItem();
        assert(currPageIndex != _wizardViewPagerAdapter.getCount() - 1);
        WizardPage currPage = (WizardPage) _wizardViewPagerAdapter.getItem(currPageIndex);
        WizardPage nextPage = (WizardPage) _wizardViewPagerAdapter.getItem(currPageIndex + 1);
        nextPage.setData(currPage.getData());
        _wizardViewPager.setCurrentItem(currPageIndex + 1);
        updateNavigationButtons();
    }

    public Fragment getCurrentPage() {
        return _wizardViewPagerAdapter.getItem(_wizardViewPager.getCurrentItem());
    }

    public void setListener(Listener listener){
        _listener = listener;
    }

    /* inner classes */

    class OnPrevButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view){
            prevPage();
        }
    }

    class OnNextButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view){
            if(_wizardViewPager.getCurrentItem() < _wizardViewPagerAdapter.getCount() - 1){
                nextPage();
            }
            else{
                if(_listener != null) {
                    _listener.onWizardComplete();
                }
            }
        }
    }

    public interface Listener{
        void onWizardComplete();
    }

    /* private methods */

    void initViews(Context context){
        _wizardViewPager = findViewById(R.id.wizard_view_pager);
        _btnPrev = findViewById(R.id.btn_prev);
        _btnPrev.setOnClickListener(new OnPrevButtonClickListener());
        _btnNext = findViewById(R.id.btn_next);
        _btnNext.setOnClickListener(new OnNextButtonClickListener());
        _wizardViewPagerAdapter = new WizardViewPagerAdapter(((FragmentActivity) context).getSupportFragmentManager());
        _wizardViewPager.setAdapter(_wizardViewPagerAdapter);
    }

    void updateNavigationButtons(){
        _btnPrev.setVisibility(_wizardViewPager.getCurrentItem() != 0 ? VISIBLE : GONE);
        _btnNext.setText(_wizardViewPager.getCurrentItem() != _wizardViewPagerAdapter.getCount() - 1 ? "NEXT" : "FINISH");
    }

}

