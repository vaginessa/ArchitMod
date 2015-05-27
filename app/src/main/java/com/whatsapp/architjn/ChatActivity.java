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
public class ChatActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(others.getResId(ChatActivity.this, "acj_chat", "xml"));
        Preference ab = findPreference("acj_chat_actionbar");
        Preference st = findPreference("acj_chat_stat");
        Preference na = findPreference("acj_chat_nav");
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
                ColorSelectorDialog dlg = new ColorSelectorDialog(ChatActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModChatColor", color).apply();
                            }
                        }, shp.getInt("architModChatColor", ColorStore.getActionBarColor()));
                dlg.show();
                return true;
            }
        });

        st.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ChatActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModChatDarkColor", color).apply();
                            }
                        }, shp.getInt("architModChatDarkColor", ColorStore.getStatusBarColor()));
                dlg.show();
                return true;
            }
        });

        na.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(ChatActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModChatNavColor", color).apply();
                            }
                        }, shp.getInt("architModChatNavColor", ColorStore.getNavigationBarColor()));
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
