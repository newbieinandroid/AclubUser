/**
 * 
 */
package aclub.users.android.ui.activities;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import android.os.Bundle;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class EmailLoginActivity extends BaseActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#onCreate(android.os
	 * .Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		facebookLogin(savedInstanceState);
		setContentView(R.layout.email_register_layout);
	}

}
