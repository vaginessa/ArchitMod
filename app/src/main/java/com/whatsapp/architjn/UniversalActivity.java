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

public class UniversalActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(others.getResId(UniversalActivity.this, "acj_universal", "xml"));
        Preference bg = (Preference) findPreference("acj_uni_bg");
        Preference ab = (Preference) findPreference("acj_uni_actionbar");
        Preference st = (Preference) findPreference("acj_uni_stat");
        Preference na = (Preference) findPreference("acj_uni_nav");
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
                ColorSelectorDialog dlg = new ColorSelectorDialog(UniversalActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConBackColor", color).apply();
                                shp.edit().putInt("architModConPickBackColor", color).apply();
                            }
                        }, shp.getInt("architModConBackColor", ColorStore.getUniBackColor()));
                dlg.show();
                return true;
            }
        });

        ab.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(UniversalActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConColor", color).apply();
                                shp.edit().putInt("architModChatColor", color).apply();
                                shp.edit().putInt("architModConPickColor", color).apply();
                            }
                        }, shp.getInt("architModConColor", ColorStore.getUniActionColor()));
                dlg.show();
                return true;
            }
        });

        st.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(UniversalActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConDarkColor", color).apply();
                                shp.edit().putInt("architModChatDarkColor", color).apply();
                                shp.edit().putInt("architModDarkConPickColor", color).apply();
                            }
                        }, shp.getInt("architModConDarkColor", ColorStore.getUniStatColor()));
                dlg.show();
                return true;
            }
        });

        na.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(UniversalActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModConNavColor", color).apply();
                                shp.edit().putInt("architModChatNavColor", color).apply();
                                shp.edit().putInt("architModNavConPickColor", color).apply();
                            }
                        }, shp.getInt("architModConNavColor", ColorStore.getUniNavColor()));
                dlg.show();
                return true;
            }
        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            na.setEnabled(false);
            st.setEnabled(false);
        }

    }

    private void updateColorsValues() {
//        lightCon = shp.getInt("architModConColor", ColorStore.getActionBarColor());
//        darkCon = shp.getInt("architModConDarkColor", ColorStore.getStatusBarColor());
//        navCon = shp.getInt("architModConNavColor", ColorStore.getNavigationBarColor());
//        lightChat = shp.getInt("architModChatColor", ColorStore.getActionBarColor());
//        darkChat = shp.getInt("architModChatDarkColor", ColorStore.getStatusBarColor());
//        navChat = shp.getInt("architModChatNavColor", ColorStore.getNavigationBarColor());
//        lightConPick = shp.getInt("architModConPickColor", ColorStore.getActionBarColor());
//        darkConPick = shp.getInt("architModDarkConPickColor", ColorStore.getStatusBarColor());
//        navConPick = shp.getInt("architModNavConPickColor", ColorStore.getNavigationBarColor());
//        normalFab = shp.getInt("architModFabNormalColor", ColorStore.getFabColorNormal());
//        clickedFab = shp.getInt("architModFabPressedColor", ColorStore.getFabColorPressed());
//        fabBackColor = shp.getInt("architModFabBgColor", ColorStore.getFabBgColor());
//        leftBubbleColor = shp.getInt("architModChatLeftBubble", ColorStore.getChatBubbleLeftColor());
//        rightBubbleColor = shp.getInt("architModChatRightBubble", ColorStore.getChatBubbleRightColor());
//        leftBubbleTextColor = shp.getInt("architModChatLeftTextBubble", ColorStore.getChatBubbleLeftTextColor());
//        rightBubbleTextColor = shp.getInt("architModChatRightTextBubble", ColorStore.getChatBubbleRightTextColor());
//        backCon = shp.getInt("architModConBackColor", ColorStore.getChatBubbleRightTextColor());
    }
}
