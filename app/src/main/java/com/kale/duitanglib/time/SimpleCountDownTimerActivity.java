package com.kale.duitanglib.time;

import com.kale.lib.activity.KaleBaseActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

/**
 * @author Jack Tony
 * @brief 官方提供的CountDownTimer来实现
 * 在activity退出后还会持续计时，所以结束时需要判断当前activity是否在前台
 * @date 2015/4/26
 */
public class SimpleCountDownTimerActivity extends KaleBaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startCountDownTime(6);

    }

    private void startCountDownTime(long time) {
        /**
         * 最简单的倒计时类，实现了官方的CountDownTimer类（没有特殊要求的话可以使用）
         * 即使退出activity，倒计时还能进行，因为是创建了后台的线程。
         * 有onTick，onFinsh、cancel和start方法
         */
        CountDownTimer timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔countDownInterval秒会回调一次onTick()方法
                Log.d(TAG, "onTick  " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish -- 倒计时结束");
            }
        };
        timer.start();// 开始计时
        //timer.cancel(); // 取消
    }
}
