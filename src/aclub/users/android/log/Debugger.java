package aclub.users.android.log;


import aclub.users.android.MainActivity;
import aclub.users.android.utils.CommonValues;
import android.util.Log;

public final class Debugger {

	private static final String DEBUG_TAG = "Debugger";

	public static void d(String msg) {
		if (CommonValues.DEBUG_MODE) {
			Log.d(DEBUG_TAG, MainActivity.TAG + " > " + msg);
		}
	}

	public static void e(String msg) {
		if (CommonValues.DEBUG_MODE) {
			Log.e(DEBUG_TAG, MainActivity.TAG + " > " + msg);
		}
	}

	public static void w(String msg) {
		if (CommonValues.DEBUG_MODE) {
			Log.w(DEBUG_TAG, MainActivity.TAG + " > " + msg);
		}
	}

	public static void i(String msg) {
		if (CommonValues.DEBUG_MODE) {
			Log.i(DEBUG_TAG, MainActivity.TAG + " > " + msg);
		}
	}
}
