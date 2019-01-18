package com.vismus.saftooshintro;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceData {

    static PreferenceData _instance = null;

    SharedPreferences _sharedPrefs;
    SharedPreferences.Editor _sharedPrefsEditor;

    public static PreferenceData getInstance(Context context){
        if(_instance == null){
            _instance = new PreferenceData(context);
        }
        return _instance;
    }

    private PreferenceData(Context context){
        _sharedPrefs = context.getSharedPreferences("AppyPreferenceData", Context.MODE_PRIVATE);
        _sharedPrefsEditor = _sharedPrefs.edit();
    }

    /* isSignedIn */

    public void setSignedIn(boolean value){
        _sharedPrefsEditor.putBoolean("isSignedIn", value);
        _sharedPrefsEditor.commit();
    }

    public boolean getIsSignedIn(){
        return _sharedPrefs.getBoolean("isSignedIn", false);
    }

}
