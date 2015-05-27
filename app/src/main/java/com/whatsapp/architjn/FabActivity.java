package com.whatsapp.architjn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import de.devmil.common.ui.color.ColorSelectorDialog;

/**
 * Created by architjn on 05/05/15.
 */
public class FabActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(others.getResId(FabActivity.this, "acj_fab", "xml"));
        Preference ab = findPreference("acj_fab_normal_color");
        Preference st = findPreference("acj_fab_pressed_color");
        Preference na = findPreference("acj_fab_bg_color");
        Preference po = findPreference("acj_fab_anim_pos");
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
                ColorSelectorDialog dlg = new ColorSelectorDialog(FabActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModFabNormalColor", color).apply();
                            }
                        }, shp.getInt("architModFabNormalColor", ColorStore.getFabColorNormal()));
                dlg.show();
                return true;
            }
        });

        st.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(FabActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModFabPressedColor", color).apply();
                            }
                        }, shp.getInt("architModFabPressedColor", ColorStore.getFabColorPressed()));
                dlg.show();
                return true;
            }
        });

        na.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ColorSelectorDialog dlg = new ColorSelectorDialog(FabActivity.this,
                        new ColorSelectorDialog.OnColorChangedListener() {
                            public void colorChanged(int color) {
                                shp.edit().putInt("architModFabBgColor", color).apply();
                            }
                        }, shp.getInt("architModFabBgColor", ColorStore.getFabBgColor()));
                dlg.show();
                return true;
            }
        });

        po.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                dialogForFabAnimationPoints();
                return true;
            }
        });

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            na.setEnabled(false);
            st.setEnabled(false);
        }

    }


    private void dialogForFabAnimationPoints() {
        final SharedPreferences shp = getSharedPreferences("architMod", Context.MODE_PRIVATE);
        Context context = FabActivity.this;
        Configuration config = getResources().getConfiguration();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double screenWidthInPixels = (double) config.screenWidthDp * dm.density;
        double screenHeightInPixels = screenWidthInPixels * dm.heightPixels / dm.widthPixels;
        float scale = getResources().getDisplayMetrics().density;
        final int dpAsPixelsTop = (int) (25 * scale + 0.5f);
        final int dpAsPixelsLeft = (int) (10 * scale + 0.5f);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setPadding(dpAsPixelsLeft, dpAsPixelsTop, dpAsPixelsLeft, dpAsPixelsTop);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(context);
        titleBox.setHint("x point");
        titleBox.setText(shp.getString("architModFabBgPosX", screenWidthInPixels + ""));
        layout.addView(titleBox);

        final EditText descriptionBox = new EditText(context);
        descriptionBox.setHint("y point");
        descriptionBox.setText(shp.getString("architModFabBgPosY", screenHeightInPixels + ""));
        layout.addView(descriptionBox);
        dialog.setView(layout);
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (!titleBox.getText().toString().matches("") && !descriptionBox.getText().toString().matches("")) {
                        Integer.parseInt(titleBox.getText().toString());
                        Integer.parseInt(descriptionBox.getText().toString());

                        shp.edit().putString("architModFabBgPosX", titleBox.getText().toString()).apply();
                        shp.edit().putString("architModFabBgPosY", descriptionBox.getText().toString()).apply();
                    }
                } catch (Exception e) {

                }
            }
        });
        dialog.show();
    }

}
