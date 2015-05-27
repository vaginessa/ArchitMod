package com.whatsapp.architjn;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by architjn on 24/03/15.
 */
public class PrimaryBgColorView extends RelativeLayout {

    private Context context;

    public PrimaryBgColorView(Context context) {
        super(context);
        this.context = context;
    }

    public PrimaryBgColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PrimaryBgColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public PrimaryBgColorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        SharedPreferences shp = context.getApplicationContext().getSharedPreferences("architMod", Context.MODE_PRIVATE);
        int primaryColor = shp.getInt("architModConColor",
                ColorStore.getActionBarColor());
        if (Build.VERSION.SDK_INT < 16)
            setBackgroundDrawable(new ColorDrawable(primaryColor));
        else
            setBackgroundColor(primaryColor);
    }
}
