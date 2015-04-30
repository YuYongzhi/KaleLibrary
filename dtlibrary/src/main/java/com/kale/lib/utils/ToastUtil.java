package com.kale.lib.utils;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * @author Jack Tony
 * @date 2015/4/29
 */
public class ToastUtil {
    
    @IntDef({Toast.LENGTH_SHORT, Toast.LENGTH_LONG})
    public @interface Length {
    }
    
    public static void makeText(@NonNull Context context, String msg) {
        if (context != null) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeText(@NonNull Context context, String msg, @Length int length) {
        if (length == Toast.LENGTH_SHORT || length == Toast.LENGTH_LONG) {
            if (context != null) {
                Toast.makeText(context, msg, length).show();
            }
        }
    }

    public static void makeTextInThread(@NonNull final Context context, final String msg) {
        makeTextInThread(context, msg, Toast.LENGTH_SHORT);
    }

    public static void makeTextInThread(@NonNull final Context context, final String msg, @Length final int length) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();//先移除
                Toast.makeText(context, msg, length).show();
                Looper.loop();// 进入loop中的循环，查看消息队列
            }
        }.start();
    }
}
