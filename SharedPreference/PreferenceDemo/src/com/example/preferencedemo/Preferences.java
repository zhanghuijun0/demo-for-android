package com.example.preferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public final class Preferences {

	private static SharedPreferences sSHARED_REFERENCES = null;
	private static Context sAPPLICATION_CONTEXT;

	public Preferences() {
	}

	public static void init(Context context) {
		if (sSHARED_REFERENCES == null) {
			sAPPLICATION_CONTEXT = context.getApplicationContext();
			sSHARED_REFERENCES = PreferenceManager
					.getDefaultSharedPreferences(sAPPLICATION_CONTEXT);
		}
	}

	public static SharedPreferences getSharedPreferences() {
		return sSHARED_REFERENCES;
	}

	public static boolean setTitle(String title) {
		Editor editor = sSHARED_REFERENCES.edit();
		editor.putString("Title", title);
		return editor.commit();
	}

	public static String getTitle() {
		return sSHARED_REFERENCES.getString("Title", "默认值");
	}
	
	public static boolean setExitTime(String time) {
		Editor editor = sSHARED_REFERENCES.edit();
		editor.putString("time", time);
		return editor.commit();
	}

	public static String getExitTime() {
		return sSHARED_REFERENCES.getString("time", "默认值");
	}

}
