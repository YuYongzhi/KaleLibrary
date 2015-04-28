package com.kale.lib.activity;

import com.kale.lib.utils.InputUtil;

/**
 * author Jack Tony
 * 所有用到editText并且可以手动调用finish()的activity都需要继承这它。<br>
 * 它可以在finish的时候强制关闭输入法
 *
 * @date 2015/4/27
 */
public class BaseEditTextActivity extends KaleBaseActivity {

    @Override
    public void finish() {
        super.finish();
        InputUtil.getInstance(this).hide();
    }
}
