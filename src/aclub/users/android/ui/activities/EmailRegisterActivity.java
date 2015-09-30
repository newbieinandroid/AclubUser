/**
 * 
 */
package aclub.users.android.ui.activities;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class EmailRegisterActivity extends BaseActivity {

	private RelativeLayout actionBarEmailRegisterLayout;

	
	private EditText emailEdt, passwordEdt;
	private TextView forgotPassTv;
	private Button emailLoginBtn, notYetRegisterEmailBtn;

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
		initActionBarTitle(getString(R.string.login_action_bar_title));
		initUI();
		updateFacebook(emailLoginBtn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#initActionBar(java.
	 * lang.String)
	 */
	@Override
	protected void initActionBarTitle(String titleActionBar) {
		actionBarEmailRegisterLayout = (RelativeLayout) findViewById(R.id.action_bar_email_register_layout);
		initActionBarLayout(actionBarEmailRegisterLayout);
		super.initActionBarTitle(titleActionBar);
	}

	protected void initUI() {
		emailEdt = (EditText) findViewById(R.id.email_edt);
		passwordEdt = (EditText) findViewById(R.id.password_edt);
		forgotPassTv = (TextView) findViewById(R.id.forgot_pass_tv);
		emailLoginBtn = (Button) findViewById(R.id.login_register_email_btn);
		notYetRegisterEmailBtn = (Button) findViewById(R.id.not_yet_register_email_btn);

		emailLoginBtn.setEnabled(false);
		emailLoginBtn.setClickable(false);
		notYetRegisterEmailBtn.setEnabled(false);
		notYetRegisterEmailBtn.setClickable(false);
	}

	

	
}
