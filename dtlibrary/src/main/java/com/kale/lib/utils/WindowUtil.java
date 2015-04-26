package com.kale.lib.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * author Jack Tony
 *
 * @date 2015/4/26
 */
public class WindowUtil {

    /**
     * 推荐的获取屏幕长宽的方式,但需要API13
     *
     * @return 装载了屏幕长宽的数组，int[0] = width,int[1] = height
     */
    @SuppressLint("NewApi")
    public static int[] getWindow_WH(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new int[]{size.x, size.y};
    }

    /**
     * 获取屏幕长宽的方式
     * 
     * @param activity
     * @return 装载了屏幕长宽的数组，int[0] = width,int[1] = height
     */
    @Deprecated
    public static int[] getWindow_wh(Activity activity) {
        int w = activity.getWindowManager().getDefaultDisplay().getWidth();//获得手机屏幕的宽度
        int h = activity.getWindowManager().getDefaultDisplay().getHeight();//获得手机屏幕的高度
        return new int[]{w, h};

    }
}
