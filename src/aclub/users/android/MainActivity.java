package aclub.users.android;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.log.DLog;
import aclub.users.android.login.ui.activities.PhoneLoginActivity;
import aclub.users.android.login.ui.activities.RegisterActivity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.facebook.appevents.AppEventsLogger;
//test
public class MainActivity extends BaseActivity implements
		BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,
		View.OnClickListener {

	private SliderLayout mDemoSlider;
	private TextView loginTv, registerTv;
	private Button loginFbBtn;
	private TextView hotSaleTv;
	
	public static String TAG = "MainActivity";
	{
		TAG = this.getClass().getSimpleName();
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		facebookLogin(savedInstanceState);
		setContentView(R.layout.activity_main);
		initImageSlider();
		initUI();

	}

	private void initUI() {
		loginTv = (TextView) findViewById(R.id.login_tv);
		registerTv = (TextView) findViewById(R.id.register_tv);
		loginFbBtn = (Button) findViewById(R.id.facebook_login_btn);
		hotSaleTv = (TextView) findViewById(R.id.title_hot_sale_tv);
		hotSaleTv.setText(getString(R.string.main_title_sale_every_day).toUpperCase());
		loginTv.setOnClickListener(this);
		registerTv.setOnClickListener(this);
		loginFbBtn.setOnClickListener(this);
		printKeyHash();
	}

	private void printKeyHash() {
		// Add code to print out the key hash
		try {
			PackageInfo info = getPackageManager()
					.getPackageInfo("aclub.users.android",
							PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				DLog.d("KeyHash: " +
						Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (NameNotFoundException e) {
			DLog.d("KeyHash: " +  e.toString());
		} catch (NoSuchAlgorithmException e) {
			DLog.d("KeyHash: " +  e.toString());
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// Logs 'install' and 'app activate' App Events.
		AppEventsLogger.activateApp(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// Logs 'app deactivate' App Event.
		AppEventsLogger.deactivateApp(this);
		super.onPause();
	}

	private void initImageSlider() {
		mDemoSlider = (SliderLayout) findViewById(R.id.slider);

//		HashMap<String, String> url_maps = new HashMap<String, String>();
//		url_maps.put(
//				"Hannibal",
//				"http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
//		url_maps.put("Big Bang Theory",
//				"http://tvfiles.alphacoders.com/100/hdclearart-10.png");
//		url_maps.put("House of Cards",
//				"http://cdn3.nflximg.net/images/3093/2043093.jpg");
//		url_maps.put(
//				"Game of Thrones",
//				"http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

		HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
		file_maps.put("Hannibal", R.drawable.slider_1);
		file_maps.put("Big Bang Theory", R.drawable.slider_2);
//		file_maps.put("House of Cards", R.drawable.house);
//		file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

		for (String name : file_maps.keySet()) {
			TextSliderView textSliderView = new TextSliderView(this);
			// initialize a SliderLayout
			textSliderView.description(name).image(file_maps.get(name))
					.setScaleType(BaseSliderView.ScaleType.Fit)
					.setOnSliderClickListener(this);

			// add your extra information
			textSliderView.bundle(new Bundle());
			textSliderView.getBundle().putString("extra", name);

			mDemoSlider.addSlider(textSliderView);
		}
		mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
//		 mDemoSlider
//		 .setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
		mDemoSlider.setCustomAnimation(new DescriptionAnimation());
		mDemoSlider.setDuration(10000);
		mDemoSlider.addOnPageChangeListener(this);
		mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator)); 
	}

	@Override
	protected void onStop() {
		// To prevent a memory leak on rotation, make sure to call
		// stopAutoCycle() on the slider before activity or fragment is
		// destroyed
		mDemoSlider.stopAutoCycle();
		super.onStop();
	}

	@Override
	public void onSliderClick(BaseSliderView slider) {
		// Toast.makeText(this, slider.getBundle().get("extra") + "",
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.action_custom_indicator:
//			mDemoSlider
//					.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
//			break;
//		case R.id.action_custom_child_animation:
//			mDemoSlider.setCustomAnimation(new ChildAnimationExample());
//			break;
//		case R.id.action_restore_default:
//			mDemoSlider
//					.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//			mDemoSlider.setCustomAnimation(new DescriptionAnimation());
//			break;
//		case R.id.action_github:
//			Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//					Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
//			startActivity(browserIntent);
//			break;
//		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		Log.d("Slider Demo", "Page Changed: " + position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_tv:
			Intent registerIntent = new Intent(MainActivity.this,
					RegisterActivity.class);
			startActivity(registerIntent);
			break;
		case R.id.login_tv:
			Intent loginIntent = new Intent(MainActivity.this,
					PhoneLoginActivity.class);
			startActivity(loginIntent);
			break;
		default:
			break;
		}
	}
}
