package com.whatsapp.architjn;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

/**
 * Created by architjn on 20/12/14.
 */

public class IconChoose extends ActionBarActivity {

    RadioButton iconOne, iconTwo, iconThree, iconFour;
    Button applyChangeButton;
    int choosenIcon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(others.getResId(IconChoose.this, "activity_icon_choose", "layout"));
        iconOne = (RadioButton) findViewById(others.getResId(IconChoose.this, "iconOne_radio", "id"));
        iconTwo = (RadioButton) findViewById(others.getResId(IconChoose.this, "iconTwo_radio", "id"));
        iconThree = (RadioButton) findViewById(others.getResId(IconChoose.this, "iconThree_radio", "id"));
        iconFour = (RadioButton) findViewById(others.getResId(IconChoose.this, "iconFour_radio", "id"));
        applyChangeButton = (Button) findViewById(others.getResId(IconChoose.this, "applyIcon", "id"));
        iconOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenIcon = 1;
                    iconOne.setChecked(true);
                    iconTwo.setChecked(false);
                    iconThree.setChecked(false);
                    iconFour.setChecked(false);
                }
            }
        });
        iconTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenIcon = 2;
                    iconOne.setChecked(false);
                    iconTwo.setChecked(true);
                    iconThree.setChecked(false);
                    iconFour.setChecked(false);
                }
            }
        });
        iconThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenIcon = 3;
                    iconOne.setChecked(false);
                    iconTwo.setChecked(false);
                    iconThree.setChecked(true);
                    iconFour.setChecked(false);
                }
            }
        });
        iconFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    choosenIcon = 4;
                    iconOne.setChecked(false);
                    iconTwo.setChecked(false);
                    iconThree.setChecked(false);
                    iconFour.setChecked(true);
                }
            }
        });
        applyChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choosenIcon == 1) {
                    removeAll();
                    getPackageManager().setComponentEnabledSetting(
                            new ComponentName("com.whatsapp", "com.whatsapp.iconone"),
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                } else if (choosenIcon == 2) {
                    removeAll();
                    getPackageManager().setComponentEnabledSetting(
                            new ComponentName("com.whatsapp", "com.whatsapp.icontwo"),
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                } else if (choosenIcon == 3) {
                    removeAll();
                    getPackageManager().setComponentEnabledSetting(
                            new ComponentName("com.whatsapp", "com.whatsapp.iconthree"),
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                } else if (choosenIcon == 4) {
                    removeAll();
                    getPackageManager().setComponentEnabledSetting(
                            new ComponentName("com.whatsapp", "com.whatsapp.iconfour"),
                            PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
                startActivity(new Intent(IconChoose.this, MainActivity.class));
                finish();
            }
        });
    }

    public void removeAll() {
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.whatsapp", "com.whatsapp.iconone"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.whatsapp", "com.whatsapp.icontwo"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.whatsapp", "com.whatsapp.iconthree"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.whatsapp", "com.whatsapp.iconfour"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    @Override
    public void onBackPressed() {
//        startActivity(new Intent(IconChoose.this, ModActivity.class));
        finish();
    }

}
