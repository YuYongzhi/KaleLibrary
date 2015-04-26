package com.kale.lib.utils;

import android.content.Intent;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/24
 */
public class IntentUtil {

    public static boolean isBundleEmpty(Intent intent) {
        return (intent == null) && (intent.getExtras() == null);
    }
}
