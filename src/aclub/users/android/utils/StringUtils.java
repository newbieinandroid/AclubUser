package aclub.users.android.utils;

public class StringUtils {

	public static boolean isEmpty(String str) {
		if (str != null) {
			if (str.length() > 0) {
				return false;
			}
		}
		return true;
	}
}
