package com.whatsapp.architjn;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by architjn on 23/03/15.
 */
public class TabBadgeTextView extends TextView {

    private Context context;

    public TabBadgeTextView(Context context) {
        super(context);
        this.context = context;
    }

    public TabBadgeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TabBadgeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public TabBadgeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        SharedPreferences shp = context.getApplicationContext().getSharedPreferences("architMod", Context.MODE_PRIVATE);
        Drawable drawable = context.getResources().getDrawable(KeyStore.getBadgeDrawable());
        drawable.setColorFilter(shp.getInt("architModFabNormalColor",
                ColorStore.getFabColorNormal()), PorterDuff.Mode.SRC_ATOP);
        if (Build.VERSION.SDK_INT < 16)
            setBackgroundDrawable(drawable);
        else
            setBackground(drawable);
    }
}
