package com.vismus.saftooshintro.WizardView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.vismus.saftooshintro.R;

import java.util.List;

import static java.lang.Thread.sleep;

public class WizardView extends LinearLayout {

    public enum NavButtonType {
        BACK,
        NEXT
    }

    // views
    WizardViewPager _wizardViewPager;
    WizardViewPagerAdapter _wizardViewPagerAdapter;
    Button _btnBack;
    Button _btnNext;

    Listener _listener;

    public WizardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.wizard_view, this);
        initViews(context);
    }

    public void setListener(Listener listener) {
        _listener = listener;
    }

    public void setPages(List<WizardPage> pages) {
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
        int currentItem = _wizardViewPager.getCurrentItem();
        assert (currentItem != 0);
        _wizardViewPager.setCurrentItem(currentItem - 1);
        updateButtons();
    }

    void next() {
        int currPageIndex = _wizardViewPager.getCurrentItem();
        assert (currPageIndex != _wizardViewPagerAdapter.getCount() - 1);
        WizardPage currPage = _wizardViewPagerAdapter.getItem(currPageIndex);
        WizardPage nextPage = _wizardViewPagerAdapter.getItem(currPageIndex + 1);
        Bundle currPageOutput = currPage.term();
        if(currPageOutput != null) {
            nextPage.init(currPageOutput);
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
        WizardPage currPage = getCurrentPage();

        // button BACK
        if (currPage.isButtonExist(NavButtonType.BACK) && _wizardViewPager.getCurrentItem() != 0) {
            _btnBack.setVisibility(VISIBLE);
            _btnBack.setOnClickListener(new OnBackButtonClickListener());
        } else {
            _btnBack.setVisibility(INVISIBLE);
            _btnBack.setOnClickListener(null);
        }

        // button NEXT/FINISH
        if (currPage.isButtonExist(NavButtonType.NEXT)) {
            _btnNext.setVisibility(VISIBLE);
            boolean isLastPage = (_wizardViewPager.getCurrentItem() == _wizardViewPagerAdapter.getCount() - 1);
            _btnNext.setText(!isLastPage ? "NEXT" : "FINISH");
            _btnNext.setOnClickListener(!isLastPage ? new OnNextButtonClickListener() : new OnFinishButtonClickListener());
        } else {
            _btnNext.setVisibility(INVISIBLE);
            _btnBack.setOnClickListener(null);
        }
    }
}