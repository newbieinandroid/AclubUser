/**
 * 
 */
package aclub.users.android.ui.activities;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.StringUtils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class PhoneLoginActivity extends BaseActivity {

	private EditText mNumPhonEdt;
	private Button mConfirmBtn;
	private TextView mEmailLgTv;
	private RelativeLayout mActionBarLayout;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#onCreate(android.os
	 * .Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_login_activity);
		initUI();
	}

	private void initUI() {
		mNumPhonEdt = (EditText) findViewById(R.id.put_your_num_edt);
		mConfirmBtn = (Button) findViewById(R.id.login_confirm_btn);
		mEmailLgTv = (TextView) findViewById(R.id.login_by_email_tv);
		mConfirmBtn.setOnClickListener(this);
		mEmailLgTv.setOnClickListener(this);
		mActionBarLayout = (RelativeLayout) findViewById(R.id.action_bar_phone_login_layout);
		initActionBarLayout(mActionBarLayout);
		initActionBarTitle(getString(R.string.login_title));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#onClick(android.view
	 * .View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_action_bar_iv:
			finish();
			break;
		case R.id.login_by_email_tv:
			doLoginByEmail();
			break;
		case R.id.login_confirm_btn:
			doLogin();
			break;
		default:
			break;
		}
	}

	private void doLogin() {
		String num = mNumPhonEdt.getText().toString();

		if (StringUtils.isEmpty(num)) {
			mNumPhonEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(PhoneLoginActivity.this, mNumPhonEdt);
		} else {
			// TODO : Login verify
		}
	}

	private void doLoginByEmail() {
		Intent emailLgIntent = new Intent(PhoneLoginActivity.this,
				EmailLoginActivity.class);
		startActivity(emailLgIntent);
		finish();
	}
}
