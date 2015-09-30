/**
 * 
 */
package aclub.users.android.utils;

import java.util.regex.Pattern;

import aclub.users.android.log.DLog;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class Tools {

	private final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

	public static boolean isEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	public static void showInputMethod(View view, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, 0);
	}

	public static void closeKeyBoard(View v, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static String getCurrentPhoneNum(Context context) {
		TelephonyManager phoneManager = (TelephonyManager) context.getApplicationContext()
				.getSystemService(Context.TELEPHONY_SERVICE);
		String phoneNumber = phoneManager.getLine1Number();
		DLog.d(phoneNumber);
		return phoneNumber;
	}
}
