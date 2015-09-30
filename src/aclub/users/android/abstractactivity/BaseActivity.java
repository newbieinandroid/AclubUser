package aclub.users.android.abstractactivity;

import aclub.users.android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

@SuppressLint("DefaultLocale")
public class BaseActivity extends Activity implements OnClickListener {

	protected ImageView backActionBarIv, helpActionBarIv;
	protected TextView titleActionBarTv;
	protected ProgressDialog dialog; // Loading dialog

	// Facebook SDK
	protected CallbackManager callbackManager;
	protected ProfileTracker profileTracker;
	protected ShareDialog shareDialog;

	protected enum PendingAction {
		NONE, POST_PHOTO, POST_STATUS_UPDATE
	}

	protected final String PENDING_ACTION_BUNDLE_KEY = "aclub.users.android.ui.activities:PendingAction";

	protected PendingAction pendingAction = PendingAction.NONE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	protected void initActionBarLayout(View view) {
		backActionBarIv = (ImageView) findViewById(R.id.back_action_bar_iv);
		helpActionBarIv = (ImageView) findViewById(R.id.help_action_bar_iv);
		titleActionBarTv = (TextView) findViewById(R.id.title_action_bar_tv);
		backActionBarIv.setOnClickListener(this);
		helpActionBarIv.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		AppEventsLogger.activateApp(BaseActivity.this);
		updateUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		AppEventsLogger.deactivateApp(BaseActivity.this);
	}

	@Override
	public void onClick(View v) {

	}

	protected void initActionBarTitle(String titleActionBar) {
		titleActionBarTv.setText(titleActionBar.toUpperCase());
	}

	protected void showDialogLoading() {
		if (dialog == null) {
			dialog = new ProgressDialog(this);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Loading. Please wait...");
			dialog.setIndeterminate(true);
			dialog.setCanceledOnTouchOutside(false);
		}
		if (dialog != null && !dialog.isShowing()) {
			dialog.show();
		}
	}

	public void hideDialogLoading() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	/**
	 * Facebook SDK
	 */
	public void facebookLogin(Bundle savedInstanceState) {
		FacebookSdk.sdkInitialize(BaseActivity.this);

		callbackManager = CallbackManager.Factory.create();

		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
					@Override
					public void onSuccess(LoginResult loginResult) {
						handlePendingAction();
						updateUI();
					}

					@Override
					public void onCancel() {
						if (pendingAction != PendingAction.NONE) {
							showAlert();
							pendingAction = PendingAction.NONE;
						}
						updateUI();
					}

					@Override
					public void onError(FacebookException exception) {
						if (pendingAction != PendingAction.NONE
								&& exception instanceof FacebookAuthorizationException) {
							showAlert();
							pendingAction = PendingAction.NONE;
						}
						updateUI();
					}

					private void showAlert() {
						new AlertDialog.Builder(BaseActivity.this)
								.setTitle(R.string.cancelled)
								.setMessage(R.string.permission_not_granted)
								.setPositiveButton(R.string.ok_aclub_label,
										null).show();
					}
				});

		shareDialog = new ShareDialog(this);
		shareDialog.registerCallback(callbackManager, shareCallback);

		if (savedInstanceState != null) {
			String name = savedInstanceState
					.getString(PENDING_ACTION_BUNDLE_KEY);
			pendingAction = PendingAction.valueOf(name);
		}
	}

	public void updateFacebook(View view) {
		profileTracker = new ProfileTracker() {
			@Override
			protected void onCurrentProfileChanged(Profile oldProfile,
					Profile currentProfile) {
				updateUI();
				// It's possible that we were waiting for Profile to be
				// populated in order to
				// post a status update.
				handlePendingAction();
			}
		};

		// profilePictureViewBar = (ProfilePictureView) view
		// .findViewById(R.id.profilePicture_bar);
		// greetingBar = (TextView) view.findViewById(R.id.greeting_bar);
		//
		// postStatusUpdateButtonBar = (Button) view
		// .findViewById(R.id.postStatusUpdateButton_bar);
		// postStatusUpdateButtonBar
		// .setOnClickListener(new View.OnClickListener() {
		// public void onClick(View view) {
		// onClickPostStatusUpdate();
		// }
		// });
		//
		// postPhotoButtonBar = (Button) view
		// .findViewById(R.id.postPhotoButton_bar);
		// postPhotoButtonBar.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View view) {
		// onClickPostPhoto();
		// }
		// });
		//
		// // Can we present the share dialog for regular links?
		// canPresentShareDialogBar =
		// ShareDialog.canShow(ShareLinkContent.class);
		//
		// // Can we present the share dialog for photos?
		// canPresentShareDialogWithPhotosBar = ShareDialog
		// .canShow(SharePhotoContent.class);
	}

	private void updateUI() {
		boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
		// postStatusUpdateButtonBar.setEnabled(enableButtons
		// || canPresentShareDialogBar);
		// postPhotoButtonBar.setEnabled(enableButtons
		// || canPresentShareDialogWithPhotosBar);

		Profile profile = Profile.getCurrentProfile();
		// Constants.currentProfile = profile;
		if (enableButtons && profile != null) {
			// profilePictureViewBar.setProfileId(profile.getId());
			// greetingBar.setText(getString(R.string.hello_user,
			// profile.getFirstName()));
			// postPhotoButtonBar.setEnabled(true);
			// postStatusUpdateButtonBar.setEnabled(true);
		} else {
			// postPhotoButtonBar.setEnabled(false);
			// postStatusUpdateButtonBar.setEnabled(false);
			// Constants.currentProfile = null;
			// profilePictureViewBar.setProfileId(null);
			// greetingBar.setText(null);
		}

		// MainActivity activity = (MainActivity) getActivity();
		// activity.getDrawer().getApdapter().notifyDataSetChanged();
	}

	protected FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
		@Override
		public void onCancel() {
			// Log.d("HelloFacebook", "Canceled");
		}

		@Override
		public void onError(FacebookException error) {
			// Log.d("HelloFacebook", String.format("Error: %s",
			// error.toString()));
			String title = getString(R.string.error);
			String alertMessage = error.getMessage();
			showResult(title, alertMessage);
		}

		@Override
		public void onSuccess(Sharer.Result result) {
			// Log.d("HelloFacebook", "Success!");
			if (result.getPostId() != null) {
				String title = getString(R.string.success);
				String id = result.getPostId();
				String alertMessage = getString(
						R.string.successfully_posted_post, id);
				showResult(title, alertMessage);
			}
		}

		private void showResult(String title, String alertMessage) {
			new AlertDialog.Builder(BaseActivity.this).setTitle(title)
					.setMessage(alertMessage)
					.setPositiveButton(R.string.ok_aclub_label, null).show();
		}
	};

	protected void handlePendingAction() {
		PendingAction previouslyPendingAction = pendingAction;
		// These actions may re-set pendingAction if they are still pending, but
		// we assume they
		// will succeed.
		pendingAction = PendingAction.NONE;
		switch (previouslyPendingAction) {
		case NONE:
			break;
		case POST_PHOTO:
			// postPhoto();
			break;
		case POST_STATUS_UPDATE:
			// postStatusUpdate();
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		if (profileTracker != null) {
			profileTracker.stopTracking();
		}
		super.onDestroy();
	}

	public boolean hasPublishPermission() {
		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		return accessToken != null
				&& accessToken.getPermissions().contains("publish_actions");
	}
}
