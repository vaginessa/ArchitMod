package com.whatsapp.architjn;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

public class ColoPreference extends Preference {

    public ColoPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//
//    @Override
//    protected View onCreateView( ViewGroup parent )
//    {
//        LayoutInflater li = (LayoutInflater)getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
//        return li.inflate( R.layout.seekbar_preference, parent, false);
//    }
//
//    @Override
//    protected void onBindView(View view) {
//        super.onBindView(view);
//        mImage = (ImageView) view.findViewById(R.id.pref_imageView);
//        mImage.setImageBitmap(mPhoto);
//    }

}