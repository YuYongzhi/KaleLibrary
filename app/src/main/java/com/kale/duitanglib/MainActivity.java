package com.kale.duitanglib;


import com.kale.lib.activity.KaleBaseActivity;
import com.kale.lib.utils.PreferenceUtil;
import com.orhanobut.logger.Logger;

import android.os.Bundle;


public class MainActivity extends KaleBaseActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*PreferenceUtil util = new PreferenceUtil(this);
        util.setIsFirstTime(true);*/

        
        //startActivity(new Intent(this, GetSimplePhotoHelperActivity.class));
        //Logger.init("-");
        log();
        PreferenceUtil.getInstance(this).putBooleanByApply("key_boolean",true);
    }

    private void log() {
       //Logger.wtf("-------------what the fuck");
        Logger.init("-");
       Logger.e("--------+------error");
    }
    



    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
