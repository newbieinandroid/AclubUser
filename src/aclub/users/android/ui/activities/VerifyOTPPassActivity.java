/**
 * 
 */
package aclub.users.android.ui.activities;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.utils.StringUtils;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VerifyOTPPassActivity extends BaseActivity {

	private RelativeLayout actionBarVerifyOtpLayout;
	private TextView resentPassTv;
	private TextView phoneNumTv;
	private TextView otpPassTv;
	private Button confirmBtn;
	private String numberPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verify_otp_pass_layout);
		initActionBar();
		Intent intent = getIntent();
		if (intent != null) {
			numberPhone = intent.getStringExtra("numberphone");
		}
		initUI();
	}

	@SuppressLint("DefaultLocale")
	private void initActionBar() {
		actionBarVerifyOtpLayout = (RelativeLayout) findViewById(R.id.action_bar_verify_otp_layout);
		super.initActionBarLayout(actionBarVerifyOtpLayout);
		titleActionBarTv.setText(getString(R.string.register_title)
				.toUpperCase());
	}

	private void initUI() {
		
		resentPassTv = (TextView) findViewById(R.id.resent_otp_tv);
		confirmBtn = (Button) findViewById(R.id.verify_otp_buton);
		confirmBtn.setOnClickListener(this);
		phoneNumTv = (TextView) findViewById(R.id.phone_num_verify_otp_pass_tv);
		otpPassTv = (TextView) findViewById(R.id.verify_otp_pass_tv);
		phoneNumTv.setText(numberPhone);

		resentPassTv
				.setText(Html
						.fromHtml("<a href=\"https://play.google.com/store/search?q=dinostudio8891&c=apps\">"
								+ getString(R.string.send_otp_pass_again)
								+ "</a>"));
		Linkify.addLinks(resentPassTv, Linkify.ALL);
		resentPassTv.setMovementMethod(LinkMovementMethod.getInstance());

		otpPassTv.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (!StringUtils.isEmpty(s.toString())) {
					confirmBtn.setClickable(true);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
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
		case R.id.verify_otp_buton:
			Intent intent = new Intent(VerifyOTPPassActivity.this,
					UpdateProfileActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.back_action_bar_iv:
			finish();
			break;
		default:
			break;
		}
	}

	BroadcastReceiver smsReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				String address = messages[i].getOriginatingAddress();
				Toast.makeText(VerifyOTPPassActivity.this, address,
						Toast.LENGTH_SHORT).show();
				String bodySms = messages[i].getDisplayMessageBody();
				if (!StringUtils.isEmpty(address)/* && address.contains("ACLUB") */) {
					otpPassTv.setText(getOTPPassFromSms(bodySms));
				}
			}
		}
	};

	/**
	 * Get OTP Pass from Sms body
	 *
	 * @param string
	 *            body
	 */
	public String getOTPPassFromSms(String body) {
		String pass = null;

		if (body != null) {
			int location = body.lastIndexOf(":");
			if (location != -1) {
				pass = body.substring(location + 1, body.length());
			}
		}

		return pass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(smsReceiver, new IntentFilter(
				"android.provider.Telephony.SMS_RECEIVED"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(smsReceiver);
	}
}
