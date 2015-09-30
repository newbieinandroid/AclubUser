package aclub.users.android.ui.activities;

import org.json.JSONException;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.httpservices.ErrorMessage;
import aclub.users.android.httpservices.JsonHelper;
import aclub.users.android.httpservices.ResponseHandler;
import aclub.users.android.httpservices.RestHelper;
import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.httpservices.models.ErrorRegister;
import aclub.users.android.httpservices.models.User;
import aclub.users.android.log.DLog;
import aclub.users.android.ui.cusdialog.CusDialogNotify;
import aclub.users.android.ui.cusdialog.CusDialogQuestion;
import aclub.users.android.ui.cusdialog.EventDialog;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.StringUtils;
import aclub.users.android.utils.Tools;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity implements
		OnItemSelectedListener {

	private RelativeLayout actionBarRegisterLayout;
	private Spinner countrySpinner;
	private Button registerBtn;
	private EditText phoneNumEdt;
	private TextView privacyTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);
		initActionBar();
		initUI();
	}

	@SuppressLint("DefaultLocale")
	private void initActionBar() {
		actionBarRegisterLayout = (RelativeLayout) findViewById(R.id.action_bar_register_layout);
		super.initActionBarLayout(actionBarRegisterLayout);
		titleActionBarTv.setText(getString(R.string.register_title)
				.toUpperCase());
	}

	private void initUI() {

		countrySpinner = (Spinner) findViewById(R.id.country_spinner_register_layout);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				RegisterActivity.this, R.array.countries_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		countrySpinner.setAdapter(adapter);

		registerBtn = (Button) findViewById(R.id.register_buton);
		registerBtn.setOnClickListener(this);
		registerBtn.setActivated(false);
		phoneNumEdt = (EditText) findViewById(R.id.phone_number_register_layout_edt);
		phoneNumEdt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (!StringUtils.isEmpty(s.toString())) {
					registerBtn.setEnabled(true);
				} else {
					registerBtn.setEnabled(false);
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
		phoneNumEdt.setHint(Tools.getCurrentPhoneNum(RegisterActivity.this));
		privacyTv = (TextView) findViewById(R.id.privacy_register_tv);
		privacyTv
				.setText(Html
						.fromHtml(getString(R.string.privacy_policy_register)
								+ " "
								+ "<a href=\"https://play.google.com/store/search?q=dinostudio8891&c=apps\">"
								+ getString(R.string.agree_term_con)
								+ "</a>"
								+ " "
								+ getString(R.string.and)
								+ " "
								+ "<a href=\"https://play.google.com/store/search?q=dinostudio8891&c=apps\">"
								+ getString(R.string.security_policy) + "</a>"
								+ getString(R.string.go_next)));
		Linkify.addLinks(privacyTv, Linkify.ALL);
		privacyTv.setMovementMethod(LinkMovementMethod.getInstance());

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_action_bar_iv:
			onBackPressed();
			break;
		case R.id.register_buton:
			registerNumberProcess();
			break;
		default:
			break;
		}
		super.onClick(v);
	}

	private void displaySendSmsDialog(final String num) {
		final CusDialogQuestion dialog = new CusDialogQuestion(
				RegisterActivity.this, num,
				getString(R.string.send_sms_register_noti),
				getString(R.string.ok_aclub_label),
				getString(R.string.cancel_aclub_label));
		dialog.setEvendialog(new EventDialog() {

			@Override
			public void onSubmit(String value) {
				dialog.dismiss();
				try {
					registerNumToServer(num);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onDismiss(int value) {
			}

			@Override
			public void onCancel() {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	private void registerNumToServer(final String num) throws JSONException {
		
		Intent intent = new Intent(RegisterActivity.this,
				VerifyOTPPassActivity.class);
		intent.putExtra("numberphone", num);
		startActivity(intent);
		finish();
		
		/***************************
		 * Comment for testing
		showDialogLoading();
		RestHelper.getInstance().registerPhoneNum(RegisterActivity.this, num,
				new ResponseHandler() {

					@Override
					public void onSuccess(BaseResponse response) {
						hideDialogLoading();
						User userRegister = (User) response;
						DLog.d("ID: " + userRegister.getId() + " Phone: "
								+ userRegister.getPhone());
						Intent intent = new Intent(RegisterActivity.this,
								VerifyOTPPassActivity.class);
						intent.putExtra("numberphone", userRegister.getPhone());
						startActivity(intent);
						finish();
					}

					@Override
					public void onError(ErrorMessage error) {
						hideDialogLoading();

						ErrorRegister errorRegister = JsonHelper.fromJson(
								error.getErrorBody(), ErrorRegister.class);

						final CusDialogNotify dialog = new CusDialogNotify(
								RegisterActivity.this,
								errorRegister.getErrors(),
								getString(R.string.error_title));
						dialog.setEvendialog(new EventDialog() {

							@Override
							public void onSubmit(String value) {
								dialog.dismiss();
							}

							@Override
							public void onDismiss(int value) {

							}

							@Override
							public void onCancel() {
								dialog.dismiss();
							}
						});
						dialog.show();
					}
				});
				*/
	}

	private void registerNumberProcess() {

		String num = phoneNumEdt.getText().toString();
		if (!StringUtils.isEmpty(num)) {
			Tools.closeKeyBoard(phoneNumEdt, RegisterActivity.this);
			displaySendSmsDialog(num);
		} else {
			phoneNumEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(RegisterActivity.this, phoneNumEdt);
			return;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
