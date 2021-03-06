package com.kale.lib.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

/**
 * @author Jack Tony
 * @date 2015/4/29
 */
public class EasyToast {

    @IntDef({Toast.LENGTH_SHORT, Toast.LENGTH_LONG})
    private @interface Length {}

    private Toast mToast = null;

    private Handler mHandler = null;

    private int duration = 0;

    private int currDuration = 0;

    private final int DEFAULT = 2000;

    public EasyToast(Context context) {
        currDuration = DEFAULT;
        mHandler = new Handler(context.getMainLooper());
        mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
    }

    private Runnable mToastThread = new Runnable() {

        public void run() {
            mToast.show();
            mHandler.postDelayed(mToastThread, DEFAULT);// 每隔2秒显示一次
            if (duration != 0) {
                if (currDuration <= duration) {
                    currDuration += DEFAULT;
                } else {
                    cancel();
                }
            }
        }
    };

    /**
     * 返回内部的toast对象。可以进行多样化的设置
     */
    public Toast getToast() {
        return mToast;
    }

    /**
     * 设置toast的文字
     */
    public void setText(String text) {
        mToast.setText(text);
    }

    /**
     * 显示toast
     *
     * @param duration toast显示的时间（单位：ms）
     */
    public void show(int duration) {
        this.duration = duration;
        mHandler.post(mToastThread);
    }

    /**
     * 设置toast显示的位置
     *
     * @param gravity 位置，可以是Gravity.CENTER等
     * @param xOffset x轴的偏移量
     * @param yOffset y轴的偏移量
     */
    public void setGravity(int gravity, int xOffset, int yOffset) {
        mToast.setGravity(gravity, xOffset, yOffset);
    }

    /**
     * 设置toast的view
     */
    public void setView(View view) {
        mToast.setView(view);
    }

    /**
     * 让toast消失的方法
     */
    public void cancel() {
        mHandler.removeCallbacks(mToastThread);// 先把显示线程删除
        mToast.cancel();// 把最后一个线程的显示效果cancel掉，就一了百了了
        currDuration = DEFAULT;
    }

    //// 封装的静态方法 //////

    public static void makeText(@NonNull Context context, int msg) {
        makeText(context, String.valueOf(msg));
    }

    public static void makeText(@NonNull Context context, String msg) {
        if (context != null) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void makeText(@NonNull Context context, int msg, @Length int length) {
        makeText(context, String.valueOf(msg), length);
    }

    public static void makeText(@NonNull Context context, String msg, @Length int length) {
        if (length == Toast.LENGTH_SHORT || length == Toast.LENGTH_LONG) {
            if (context != null) {
                Toast.makeText(context, msg, length).show();
            }
        }
    }

    /**
     * 当你在线程中使用toast时，请使用这个方法
     */
    public static void makeTextInThread(@NonNull final Context context, int msg) {
        makeTextInThread(context, String.valueOf(msg));
    }

    /**
     * 当你在线程中使用toast时，请使用这个方法
     */
    public static void makeTextInThread(@NonNull Context context, String msg) {
        makeTextInThread(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 当你在线程中使用toast时，请使用这个方法
     */
    public static void makeTextInThread(@NonNull Context context, int msg, @Length int length) {
        makeTextInThread(context, String.valueOf(msg), length);
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
