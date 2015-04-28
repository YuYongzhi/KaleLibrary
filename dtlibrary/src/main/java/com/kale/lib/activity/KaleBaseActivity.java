package com.kale.lib.activity;

import android.app.Activity;
import android.util.Log;
import android.view.View;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/25
 */
public abstract class KaleBaseActivity extends Activity {

    protected String TAG = getClass().getSimpleName();

    public final <E extends View> E getView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }


}
