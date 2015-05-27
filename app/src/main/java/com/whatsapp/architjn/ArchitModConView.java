package com.whatsapp.architjn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by architjn on 06/01/15.
 */
public class ArchitModConView extends LinearLayout {

    private Context context;
    private boolean unlocked;

    public ArchitModConView(Context context) {
        super(context);
        this.context = context;
    }

    public ArchitModConView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public ArchitModConView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public ArchitModConView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final SharedPreferences shp = context.getSharedPreferences("architMod", Context.MODE_PRIVATE);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            View a = new View(context);
            addView(a, 0);
            setStatusBarColor((Activity) context, a, shp.getInt("architModConDarkColor", ColorStore.getStatusBarColor()), shp);
        }
        if (shp.getBoolean("isPasswordSet", false)) {
            setDialogPass(shp);
        }
    }

    private void setDialogPass(final SharedPreferences shp) {
        final View backDialog = new View(context);
        backDialog.setBackgroundColor(Color.parseColor("#ffffff"));
        addView(backDialog);
        float scale = getResources().getDisplayMetrics().density;
        final int dpAsPixelsTop = (int) (25 * scale + 0.5f);
        final int dpAsPixelsLeft = (int) (10 * scale + 0.5f);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        LinearLayout layout = new LinearLayout(context);
        layout.setPadding(dpAsPixelsLeft, dpAsPixelsTop, dpAsPixelsLeft, dpAsPixelsTop);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(context);
        titleBox.setHint("password");
        titleBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout.addView(titleBox);

        dialog.setView(layout);
        dialog.setPositiveButton("Validate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (titleBox.getText().toString().matches(shp.getString("architModPass", "*38ue2uhd38x-3%3#hu38"))) {
                    dialog.dismiss();
                    unlocked = true;
                    backDialog.setVisibility(View.GONE);
                } else {
                    Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show();
                    unlocked = false;
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface mDialog) {
                if (!unlocked) {
                    backDialog.setVisibility(View.GONE);
                    setDialogPass(shp);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 17)
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface mDialog) {
                    if (!unlocked) {
                        backDialog.setVisibility(View.GONE);
                        setDialogPass(shp);
                    }
                }
            });
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void setStatusBarColor(Activity activity, View statusBar, int color, SharedPreferences shp) {
        Window w = activity.getWindow();
//        if (shp.getBoolean("architModConTransNav", true)) {
//            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        if (shp.getBoolean("architModConTransStat", false)) {
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = getActionBarHeight(activity);
            int statusBarHeight = getStatusBarHeight(activity);
            //action bar height
            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
            statusBar.setBackgroundColor(color);
        }
    }

    public static int getActionBarHeight(Activity activity) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
