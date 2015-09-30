package aclub.users.android.utils;

import aclub.users.android.R;
import android.content.Context;
import android.view.View;

public class AnimationUtils {

	public static void shake(Context context, View view) {
		android.view.animation.Animation shake = android.view.animation.AnimationUtils
				.loadAnimation(context, R.anim.shake);
		view.startAnimation(shake);
		view.requestFocus();
	}
}
