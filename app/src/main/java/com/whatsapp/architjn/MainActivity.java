package com.whatsapp.architjn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by architjn on 04/05/15.
 */
public class MainActivity extends PreferenceActivity {

    private static final int REQ_CREATE_PATTERN = 1, REQ_CHECK_PATTERN = 2, REQ_CHECK_PATTERN_REMOVE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(others.getResId(MainActivity.this, "acj_modpref", "xml"));
        Preference updater = (Preference) findPreference("acjupdater");
        Preference icons = (Preference) findPreference("acjicons");
        Preference otherApps = (Preference) findPreference("acjoa");
        Preference reset = (Preference) findPreference("acjreset");
        Preference donate = (Preference) findPreference("acjdonate");
        Preference universal = (Preference) findPreference("acjuniversal");
        Preference cons = (Preference) findPreference("acjcons");
        Preference chat = (Preference) findPreference("acjchat");
        Preference conPick = (Preference) findPreference("acjcont");
        Preference fab = (Preference) findPreference("acjfab");
        Preference pass = (Preference) findPreference("acjpass");
        final SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(R.attr.colorPrimaryDark);
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.acj_settings_toolbar, root, false);
        bar.setBackgroundColor(R.attr.colorPrimary);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RestartAppActivity.class));
            }
        });
        updater.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                new updateChecker(MainActivity.this).execute();
                return true;
            }
        });
        icons.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(MainActivity.this, IconChoose.class));
                return true;
            }
        });
        otherApps.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=AcjMods")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=AcjMods")));
                }
                return true;
            }
        });
        reset.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                dialog.setMessage("Are you sure you want to reset all ArchitMod preference?");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetArchitMod();
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
                return true;
            }
        });
        donate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://goo.gl/F3wuUB")));
                return true;
            }
        });
        universal.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(MainActivity.this, UniversalActivity.class));
                return true;
            }
        });
        cons.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(MainActivity.this, ConsActivity.class));
                return true;
            }
        });
        chat.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
                return true;
            }
        });
        conPick.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(MainActivity.this, ConPickActivity.class));
                return true;
            }
        });
        fab.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(MainActivity.this, FabActivity.class));
                return true;
            }
        });
        pass.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (shp.getBoolean("architModPassSet", false)) {
                    ArrayList<String> arrayString = new ArrayList<String>();
                    arrayString.add("Change Password");
                    arrayString.add("Remove Password");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                            KeyStore.getAndroidSimpleItemListLayoutId(), KeyStore.getAndroidSimpleListTextItemId(), arrayString);
                    ListView lv = new ListView(MainActivity.this);
                    lv.setDividerHeight(0);
                    lv.setAdapter(adapter);
                    final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                if (shp.getBoolean("architModPassSet", false)) {
                                    Toast.makeText(MainActivity.this, "Enter old Password", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LockPatternActivity.ACTION_COMPARE_PATTERN, null,
                                            MainActivity.this, LockPatternActivity.class);
                                    intent.putExtra(LockPatternActivity.EXTRA_PATTERN, readPass());
                                    startActivityForResult(intent, REQ_CHECK_PATTERN);
                                } else {
                                    Intent intent = new Intent(LockPatternActivity.ACTION_CREATE_PATTERN, null,
                                            MainActivity.this, LockPatternActivity.class);
                                    startActivityForResult(intent, REQ_CREATE_PATTERN);
                                }
                            } else {
                                Intent intent = new Intent(LockPatternActivity.ACTION_COMPARE_PATTERN, null,
                                        MainActivity.this, LockPatternActivity.class);
                                intent.putExtra(LockPatternActivity.EXTRA_PATTERN, readPass());
                                startActivityForResult(intent, REQ_CHECK_PATTERN_REMOVE);
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.setView(lv);
                    dialog.setCancelable(true);
                    dialog.show();
                } else {
                    Intent intent = new Intent(LockPatternActivity.ACTION_CREATE_PATTERN, null,
                            MainActivity.this, LockPatternActivity.class);
                    startActivityForResult(intent, 1);
                }
                return true;
            }
        });
    }

    private void resetArchitMod() {
        SharedPreferences shp = getSharedPreferences("architMod", Context.MODE_PRIVATE);
        shp.edit().clear().commit();
    }

    public char[] readPass() {
        SharedPreferences shp = getSharedPreferences("architMod", Context.MODE_PRIVATE);
        char[] chars = new char[shp.getInt("architModPassCount", 0)];
        for (int i = 0; i < shp.getInt("architModPassCount", 0); i++) {
            chars[i] = shp.getString("architModPass" + i, "a").charAt(0);
        }
        return chars;
    }

    public void savePass(char[] chars) {
        SharedPreferences shp = getSharedPreferences("architMod", Context.MODE_PRIVATE);
        for (int i = 0; i < chars.length; i++) {
            shp.edit().putString("architModPass" + i, chars[i] + "").apply();
        }
        shp.edit().putInt("architModPassCount", chars.length).apply();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        final SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        switch (requestCode) {
            case REQ_CREATE_PATTERN: {
                if (resultCode == RESULT_OK) {
                    char[] pattern = data.getCharArrayExtra(
                            LockPatternActivity.EXTRA_PATTERN);
                    savePass(pattern);
                    shp.edit().putBoolean("architModPassSet", true).apply();
                }
                break;
            }
            case REQ_CHECK_PATTERN: {
                switch (resultCode) {
                    case RESULT_OK:
                        Toast.makeText(MainActivity.this, "Enter new Password", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LockPatternActivity.ACTION_CREATE_PATTERN, null,
                                MainActivity.this, LockPatternActivity.class);
                        startActivityForResult(intent, REQ_CREATE_PATTERN);
                        break;
                    case RESULT_CANCELED:
                        break;
                    case LockPatternActivity.RESULT_FAILED:
                        Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        break;
                    case LockPatternActivity.RESULT_FORGOT_PATTERN:
                        Toast.makeText(MainActivity.this, "Feature to generate password is coming soon", Toast.LENGTH_LONG).show();
                        break;
                }
            }
            case REQ_CHECK_PATTERN_REMOVE: {
                switch (resultCode) {
                    case RESULT_OK:
                        shp.edit().putBoolean("architModPassSet", false).apply();
                        break;
                    case RESULT_CANCELED:
                        break;
                    case LockPatternActivity.RESULT_FAILED:
                        Toast.makeText(MainActivity.this, "Old Password is required to remove Password lock!", Toast.LENGTH_SHORT).show();
                        break;
                    case LockPatternActivity.RESULT_FORGOT_PATTERN:
                        Toast.makeText(MainActivity.this, "Feature is coming soon", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    }

}
