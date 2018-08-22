package com.worldsills.wscolorapp;

import android.os.Bundle;
import android.preference.PreferenceFragment;



public class Pref extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefenrecias);
        //
    }
}
