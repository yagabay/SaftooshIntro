package com.vismus.saftooshintro.WizardView;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.vismus.saftooshintro.R;

import java.util.List;


public class WizardView extends LinearLayout {

    // views
    WizardViewPager _wizardViewPager;
    Button _btnBack;
    Button _btnNext;

    WizardViewPagerAdapter _wizardViewPagerAdapter;
    boolean _showBackButton;

    Listener _listener;

    public WizardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.wizard_view, this);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.com_vismus_appy_WizardView, 0, 0);
        try{
            _showBackButton = array.getBoolean(R.styleable.com_vismus_appy_WizardView_showBackButton, true);
        }
        finally{
            array.recycle();
        }
        initViews(context);
    }

    public void setListener(Listener listener) {
        _listener = listener;
    }

    public void setPages(List<WizardPage> pages) {
        for(WizardPage page : pages){
            page._container = this;
        }
        _wizardViewPagerAdapter.setItems(pages);
        _wizardViewPagerAdapter.notifyDataSetChanged();
        updateButtons();
    }

    public WizardPage getCurrentPage() {
        return _wizardViewPagerAdapter.getItem(_wizardViewPager.getCurrentItem());
    }

    /* inner classes */

    class OnBackButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            back();
        }
    }

    class OnNextButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            next();
        }
    }

    class OnFinishButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
        }
    }

    public interface Listener {
        void onWizardFinished();
    }

    /* private methods */

    void initViews(Context context) {
        _wizardViewPager = findViewById(R.id.wizard_view_pager);
        _btnBack = findViewById(R.id.btn_back);
        _btnNext = findViewById(R.id.btn_next);
        _wizardViewPagerAdapter = new WizardViewPagerAdapter(((FragmentActivity) context).getSupportFragmentManager());
        _wizardViewPager.setAdapter(_wizardViewPagerAdapter);
    }

    void back() {
        int currPageIndex = _wizardViewPager.getCurrentItem();
        if(currPageIndex == 0) {
            return;
        }
        _wizardViewPagerAdapter.getItem(currPageIndex - 1).init(null);
        _wizardViewPager.setCurrentItem(currPageIndex - 1);
        updateButtons();
    }

    void next() {
        int currPageIndex = _wizardViewPager.getCurrentItem();
        if(currPageIndex == _wizardViewPagerAdapter.getCount() - 1){
            return;
        }
        WizardPage currPage = _wizardViewPagerAdapter.getItem(currPageIndex);
        WizardPage nextPage = _wizardViewPagerAdapter.getItem(currPageIndex + 1);
        Bundle currPageData = currPage.term();
        if(currPageData != null) {
            nextPage.init(currPageData);
            _wizardViewPager.setCurrentItem(currPageIndex + 1);
            updateButtons();
        }
    }

    void finish() {
        if (_listener != null) {
            _listener.onWizardFinished();
        }
    }

    void updateButtons() {
        int currPageIndex = _wizardViewPager.getCurrentItem();

        // BACK button
        if (_showBackButton && currPageIndex != 0) {
            _btnBack.setVisibility(VISIBLE);
            _btnBack.setOnClickListener(new OnBackButtonClickListener());
        } else {
            _btnBack.setVisibility(INVISIBLE);
            _btnBack.setOnClickListener(null);
        }

        // NEXT button
        if(currPageIndex != _wizardViewPagerAdapter.getCount() - 1) {
            _btnNext.setText("NEXT");
            _btnNext.setOnClickListener(new OnNextButtonClickListener());
        }
        else {
            _btnNext.setText("FINISH");
            _btnNext.setOnClickListener(new OnFinishButtonClickListener());
        }
    }

    public static abstract class WizardPage extends Fragment {

        protected WizardView _container;

        /* override to pass input data to page */
        public void init(Bundle data){}

        /* override to receive output data from page */
        public Bundle term(){
            return new Bundle();
        }

        protected void setNextButtonEnabled(boolean enabled){
            _container._btnNext.setEnabled(enabled);
        }

        protected void back(){
            _container.back();
        }
    }

}