package com.example.david.ud1_davidtoba;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;


import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class Configuracion extends PreferenceActivity {
    /**
     * Determines whether to always show the simplified settings UI, where
     * settings are presented in a single list. When false, settings are shown
     * as a master/detail two-pane view on tablets. When true, a single pane is
     * shown on tablets.
     */
    CheckBoxPreference chkbPref;
    EditTextPreference edPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);
        //No caso de que o Checkbox estea marcado, poñemos a ruta por defecto
        chkbPref=(CheckBoxPreference) findPreference("rut_def");
        edPreference= (EditTextPreference) findPreference("ed_ruta");
        if(chkbPref.isChecked()) edPreference.setText(getFilesDir().getAbsolutePath());
        chkbPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if(!chkbPref.isChecked()) return true;
                edPreference.setText(getFilesDir().getAbsolutePath());
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(chkbPref.isChecked()) edPreference.setText(getFilesDir().getAbsolutePath());
        super.onDestroy();
    }
}