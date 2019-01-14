package com.vismus.saftooshintro.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vismus.saftooshintro.PreferenceData;
import com.vismus.saftooshintro.R;
import com.vismus.saftooshintro.Swiper.Swiper;
import com.vismus.saftooshintro.fragments.GalleryFragment;
import com.vismus.saftooshintro.fragments.SetUpWizardFragment;
import com.vismus.saftooshintro.fragments.SplashFragment;

public class MainActivity extends AppCompatActivity implements SplashFragment.Listener, SetUpWizardFragment.Listener{

    FrameLayout _layMain;
    SetUpWizardFragment _setUpWizardFragment;
    GalleryFragment _galleryFragment;

    PreferenceData _prefData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _layMain = findViewById(R.id.lay_main);
        _prefData = PreferenceData.getInstance(this);
        _prefData.setIsSignedIn(false); // for debugging
        showSplash();
    }

    @Override
    public void onSplashEnded() {
        requestPermissions();
        if(_prefData.getIsSignedIn()){
            showGallery();
        }
        else{
            showSetUpWizardFragment();
        }
    }

    @Override
    public void onSetUpComplete(){
        _galleryFragment = new GalleryFragment();
        Swiper swiper = new Swiper(this, R.id.lay_main, _setUpWizardFragment.getCurrentPage(), _galleryFragment);
        swiper.swipe();
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
        for(int res : grantResults){
            if(res != 0){
                showDialogPermissionsMissing();
            }
        }
    }

    /* private methods */

    void showSplash(){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_main, new SplashFragment())
                .commitNow();
    }

    void requestPermissions(){
        String[] permissions = {Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, };
        ActivityCompat.requestPermissions(this, permissions, 0);
    }

    void showDialogPermissionsMissing() {
        AlertDialog.Builder dlgAlertBuilder = new AlertDialog.Builder(this);
        dlgAlertBuilder.setMessage("Necessary permissions are missing");
        dlgAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Exit App here", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dlgAlert = dlgAlertBuilder.create();
        dlgAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlgAlert.show();
    }

    void showSetUpWizardFragment(){
        _setUpWizardFragment = new SetUpWizardFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lay_main, _setUpWizardFragment)
                .commitNow();
    }

    void showGallery(){
        _galleryFragment = new GalleryFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lay_main, _galleryFragment)
                .commitNow();
    }

}
