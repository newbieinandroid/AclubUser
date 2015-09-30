/**
 * 
 */
package aclub.users.android.receivers;

import com.facebook.FacebookBroadcastReceiver;

import android.os.Bundle;
import android.util.Log;

/**
 * @author ntdong2012
 *
 */
public class CusFacebookBroadcastReceiver extends FacebookBroadcastReceiver {

	@Override
	protected void onSuccessfulAppCall(String appCallId, String action,
			Bundle extras) {
		// A real app could update UI or notify the user that their photo was
		// uploaded.
		Log.d("KeyHash",
				String.format("Photo uploaded by call " + appCallId
						+ " succeeded."));
	}

	@Override
	protected void onFailedAppCall(String appCallId, String action,
			Bundle extras) {
		// A real app could update UI or notify the user that their photo was
		// not uploaded.
		Log.d("KeyHash",
				String.format("Photo uploaded by call " + appCallId
						+ " failed."));
	}
}
