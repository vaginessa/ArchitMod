package com.whatsapp.architjn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import de.devmil.common.ui.color.ColorSelectorDialog;

/**
 * Created by architjn on 05/05/15.
 */
public class ConPickActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(others.getResId(ConPickActivity.this, "acj_con_pick", "xml"));
        Preference bg = (Preference) findPreference("acj_cp_bg");
        Preference ab = (Preference) findPreference("acj_cp_actionbar");
        Preference st = (Preference) findPreference("acj_cp_stat");
        Preference na = (Preference) findPreference("acj_cp_nav");
        final SharedPreferences shp = getSharedPreferences("architMod", Context.MODE_PRIVATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(R.attr.colorPrimaryDark);
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.acj_settings_toolbar, root, false);
        bar.setBackgroundColor(R.attr.colorPrimary);
        root.addView(bar, 0);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bg.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ConPickActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConPickBackColor", color).apply();
                            }
                        }, shp.getInt("architModConPickBackColor", ColorStore.getConPickBackColor()));
                dlg.show();
                return true;
            }
        });

        ab.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ConPickActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConPickColor", color).apply();
                            }
                        }, shp.getInt("architModConPickColor", ColorStore.getActionBarColor()));
                dlg.show();
                return true;
            }
        });

        st.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ConPickActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModDarkConPickColor", color).apply();
                            }
                        }, shp.getInt("architModDarkConPickColor", ColorStore.getStatusBarColor()));
                dlg.show();
                return true;
            }
        });

        na.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ConPickActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModNavConPickColor", color).apply();
                            }
                        }, shp.getInt("architModNavConPickColor", ColorStore.getNavigationBarColor()));
                dlg.show();
                return true;
            }
        });
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            na.setEnabled(false);
            st.setEnabled(false);
        }

    }

}
