package com.whatsapp.architjn;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by architjn on 06/01/15.
 */
public class ArchitModConPickView extends LinearLayout {

    private Context context;

    public ArchitModConPickView(Context context) {
        super(context);
        this.context = context;
    }

    public ArchitModConPickView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public ArchitModConPickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public ArchitModConPickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        SharedPreferences shp = context.getSharedPreferences("architMod", Context.MODE_PRIVATE);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            View a = new View(context);
            addView(a, 0);
            setStatusBarColor((Activity) context, a, shp.getInt("architModDarkConPickColor", ColorStore.getStatusBarColor()), shp);
        }
    }

    public static void setStatusBarColor(Activity activity, View statusBar, int color, SharedPreferences shp) {
        Window w = activity.getWindow();
//        if (shp.getBoolean("architModConPickTransNav", true)) {
//            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        if (shp.getBoolean("architModConPickTransStat", false)) {
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
