package com.example.downdown_first_app_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ConfigHelper {
	
	
	// Preference �б�
	public static String getConfigValue(Context context, String key){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return pref.getString(key, null);
	}
		
	// Preference ����
	public static void setConfigValue(Context context, String key, String value){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	 }
}
