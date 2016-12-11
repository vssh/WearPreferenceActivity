package me.denley.wearpreferenceactivity.preference.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.denley.wearpreferenceactivity.preference.R;

public class WearPreferenceScreen extends WearPreferenceItem {

    private final List<WearPreferenceItem> childItems = new ArrayList<>();
    @ColorRes private int background = -1;

    public WearPreferenceScreen(@NonNull final Context context, @NonNull final AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PreferenceScreen, 0, 0);
        try {
            background = array.getResourceId(R.styleable.PreferenceScreen_wear_screenBackground, -1);
        } finally {
            array.recycle();
        }
    }

    public int getBackground() {
        return background;
    }

    public void addChild(WearPreferenceItem preference) {
        childItems.add(preference);
    }

    public List<WearPreferenceItem> getChildren() {
        return childItems;
    }

    @Override public void onPreferenceClick(@NonNull final Context context) {
        final Intent intent = new Intent(context, PreferenceScreenActivity.class);
        intent.putExtra(PreferenceScreenActivity.EXTRA_SCREEN, this);

        try {
            context.startActivity(intent);
        }catch(Exception e) {
            Log.d("Cause", "", e.getCause());
            throw e;
        }
    }

}
