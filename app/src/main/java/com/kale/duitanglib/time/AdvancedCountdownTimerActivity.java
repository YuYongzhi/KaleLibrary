package com.kale.duitanglib.time;

import com.kale.lib.activity.KaleBaseActivity;

import android.os.Bundle;
import android.util.Log;

/**
 * @author Jack Tony
 * @brief
 * @date 2015/4/26
 */
public class AdvancedCountdownTimerActivity extends KaleBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 8秒倒计时
        com.kale.lib.time.AdvancedCountdownTimer countdownTimer = new com.kale.lib.time.AdvancedCountdownTimer(8 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished, int percent) {
                //每隔countDownInterval秒会回调一次onTick()方法
                Log.d(TAG,"onTick   " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                // 倒计时结束
                Log.d(TAG, "onFinish -- 倒计时结束");
            }
        };
        countdownTimer.start();
    }
}
