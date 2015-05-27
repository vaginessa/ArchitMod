package com.whatsapp.architjn;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.whatsapp.Main;

/**
 * Created by architjn on 20/12/14.
 */
public class others {

    public static boolean requirePass = true;
    private static Context con;

    public static void architModCons(ActionBar actionBar, final Activity activity) {
        con = activity.getApplicationContext();
        SharedPreferences shp = activity.getApplicationContext().getSharedPreferences("architMod", Context.MODE_PRIVATE);
        actionBar.setBackgroundDrawable(new ColorDrawable(shp.getInt("architModConColor", ColorStore.getActionBarColor())));
        activity.getWindow().setBackgroundDrawable(new ColorDrawable(shp.getInt("architModConBackColor", ColorStore.getConsBackColor())));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(shp.getInt("architModConDarkColor", ColorStore.getStatusBarColor()));
            activity.getWindow().setNavigationBarColor(shp.getInt("architModConNavColor", ColorStore.getNavigationBarColor()));
            activity.getActionBar().setElevation(0);
        }
    }

    public static void setPassWord(final Activity activity) {
        if (others.requirePass) {
            SharedPreferences shp = activity.getApplicationContext().getSharedPreferences("architMod", Context.MODE_PRIVATE);
            final int REQ_CHECK_PATTERN = 18876;
            if (shp.getBoolean("architModPassSet", false)) {
                Intent intent = new Intent(LockPatternActivity.ACTION_COMPARE_PATTERN, null,
                        activity.getApplicationContext(), LockPatternActivity.class);
                intent.putExtra(LockPatternActivity.EXTRA_PATTERN, readPass(activity.getApplicationContext()));
                activity.startActivityForResult(intent, REQ_CHECK_PATTERN);
            }
        }
    }

    public static void checkPasswordActivityResult(Context context, int requestCode, int resultCode) {
        final int REQ_CHECK_PATTERN = 18876;
        switch (requestCode) {
            case REQ_CHECK_PATTERN: {
                if (resultCode == ((Activity) context).RESULT_OK) {
                    requirePass = false;
                } else if (resultCode == ((Activity) context).RESULT_CANCELED) {
                    Toast.makeText(context, "PsswordCanceled", Toast.LENGTH_LONG).show();
                    Intent mStartActivity = new Intent(context, Main.class);
                    int mPendingIntentId = 347988;
                    PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                    System.exit(0);
                } else if (resultCode == LockPatternActivity.RESULT_FAILED) {
                    Toast.makeText(context, "Wrong Pass", Toast.LENGTH_LONG).show();
                    Intent mStartActivity = new Intent(context, Main.class);
                    int mPendingIntentId = 347788;
                    PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                    AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                    System.exit(0);
                } else if (resultCode == LockPatternActivity.RESULT_FORGOT_PATTERN) {
                    Toast.makeText(context, "Feature to generate password is coming soon", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    public CharSequence getPageTitle(int position) {
        Context context;
        int[] imageResId = {
                R.drawable.ic_action_call,
                R.drawable.ic_action_message,
                R.drawable.ic_action_message
        };
        Drawable image = con.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public static Drawable architModChatBubbleColorLeft(Drawable drawable, Context context) {
        SharedPreferences shp = context.getSharedPreferences("architMod", Context.MODE_PRIVATE);
        drawable.setColorFilter(new
                PorterDuffColorFilter(shp.getInt("architModChatLeftBubble",
                ColorStore.getChatBubbleLeftColor()), PorterDuff.Mode.MULTIPLY));
        return drawable;
    }

    public static Drawable architModChatBubbleColorRight(Drawable drawable, Context context) {
        SharedPreferences shp = context.getSharedPreferences("architMod", Context.MODE_PRIVATE);
        drawable.setColorFilter(new
                PorterDuffColorFilter(shp.getInt("architModChatRightBubble",
                ColorStore.getChatBubbleRightColor()), PorterDuff.Mode.MULTIPLY));
        return drawable;
    }

    public static void architModChat(ActionBar actionBar, final Activity activity) {
        SharedPreferences shp = activity.getApplicationContext().getSharedPreferences("architMod", Context.MODE_PRIVATE);
        actionBar.setBackgroundDrawable(new ColorDrawable(shp.getInt("architModChatColor", ColorStore.getActionBarColor())));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(shp.getInt("architModChatDarkColor", ColorStore.getStatusBarColor()));
            activity.getWindow().setNavigationBarColor(shp.getInt("architModChatNavColor", ColorStore.getNavigationBarColor()));
        }
    }

    public static void architModInfo(Activity activity) {
        SharedPreferences shp = activity.getApplicationContext().getSharedPreferences("architMod", Context.MODE_PRIVATE);
        activity.getActionBar().setBackgroundDrawable(new ColorDrawable(shp.getInt("architModConColor", ColorStore.getActionBarColor())));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(shp.getInt("architModConDarkColor", ColorStore.getStatusBarColor()));
            activity.getWindow().setNavigationBarColor(shp.getInt("architModConNavColor", ColorStore.getNavigationBarColor()));
            activity.getActionBar().setElevation(0);
        }
    }

    public static void architModContPick(ActionBar actionBar, final Activity activity) {
        SharedPreferences shp = activity.getApplicationContext().getSharedPreferences("architMod", Context.MODE_PRIVATE);
        actionBar.setBackgroundDrawable(new ColorDrawable(shp.getInt("architModConPickColor", ColorStore.getActionBarColor())));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(shp.getInt("architModDarkConPickColor", ColorStore.getStatusBarColor()));
            activity.getWindow().setNavigationBarColor(shp.getInt("architModNavConPickColor", ColorStore.getNavigationBarColor()));
        }
    }

    public static void setStatusBarColor(Activity activity, View statusBar, int color) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window w = activity.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
            int actionBarHeight = getActionBarHeight(activity);
            int statusBarHeight = getStatusBarHeight(activity);
            //action bar height
            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight + 10;
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

    public static char[] readPass(Context context) {
        SharedPreferences shp = context.getSharedPreferences("architMod", Context.MODE_PRIVATE);
        char[] chars = new char[shp.getInt("architModPassCount", 0)];
        for (int i = 0; i < shp.getInt("architModPassCount", 0); i++) {
            chars[i] = shp.getString("architModPass" + i, "a").charAt(0);
        }
        return chars;
    }

    public static void savePass(Context context, char[] chars) {
        SharedPreferences shp = context.getSharedPreferences("architMod", Context.MODE_PRIVATE);
        for (int i = 0; i < chars.length; i++) {
            shp.edit().putString("architModPass" + i, chars[i] + "").apply();
        }
        shp.edit().putInt("architModPassCount", chars.length).apply();
    }

    public static int getResId(Context context, String s1, String s2) {
        return context.getResources().getIdentifier(s1, s2, context.getPackageName());
    }
}
