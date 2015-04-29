package com.kale.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author:Jack Tony
 * @tips :这里是得到设置好的数据的工具类，之所以只有get方法，没有set方法。是因为set方法在preference控件中进行了设置
 * @date :2014-10-8
 */
public class PreferenceUtil {
	private Context mContext;
	private static SharedPreferences sharedPreferences;
	private String preferencesName = "com.kale.duitanglib_preferences";

	public PreferenceUtil(Context mContext) {
		this.mContext = mContext;
		sharedPreferences = mContext.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
	}


	public void setIsFirstTime(boolean isFirstTime) {
		sharedPreferences.edit().putBoolean("isFristTime", isFirstTime).commit();
	}


}
