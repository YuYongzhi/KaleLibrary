package com.kale.lib;


import com.orhanobut.logger.Logger;

import android.app.Application;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/25
 */
public class KaleApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
    }
}
