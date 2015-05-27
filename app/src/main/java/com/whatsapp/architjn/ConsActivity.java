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
public class ConsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(others.getResId(ConsActivity.this, "acj_cons", "xml"));
        Preference ab = findPreference("acj_con_actionbar");
        Preference st = findPreference("acj_con_stat");
        Preference na = findPreference("acj_con_nav");
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

        ab.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ConsActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConColor", color).apply();
                            }
                        }, shp.getInt("architModConColor", ColorStore.getActionBarColor()));
                dlg.show();
                return true;
            }
        });

        st.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ConsActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConDarkColor", color).apply();
                            }
                        }, shp.getInt("architModConDarkColor", ColorStore.getStatusBarColor()));
                dlg.show();
                return true;
            }
        });

        na.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ConsActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConNavColor", color).apply();
                            }
                        }, shp.getInt("architModConNavColor", ColorStore.getNavigationBarColor()));
                dlg.show();
                return true;
            }
        });

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            na.setEnabled(false);
            st.setEnabled(false);
        }

    }
}
