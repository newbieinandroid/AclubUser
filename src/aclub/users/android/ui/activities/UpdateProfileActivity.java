/**
 * 
 */
package aclub.users.android.ui.activities;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.ui.cusimageview.RoundedImageView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class UpdateProfileActivity extends BaseActivity {

	private RelativeLayout actionBarLayout;
	private RoundedImageView avatarProfile;
	private final int FROM_CAMERA = 1;
	private final int FROM_GALARY = 2;

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
		setContentView(R.layout.update_profile_activity);
		initActionBarTitle(getString(R.string.register_title));
		initUI();
	}

	private void initUI() {
		avatarProfile = (RoundedImageView) findViewById(R.id.avatar_profile);
		avatarProfile.setOnClickListener(this);
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
		actionBarLayout = (RelativeLayout) findViewById(R.id.action_bar_update_profile_layout);
		super.initActionBarLayout(actionBarLayout);
		super.initActionBarTitle(titleActionBar);
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
		case R.id.avatar_profile:
			selectImageToAvatar();
			break;

		default:
			break;
		}
	}
	
	private void selectImageToAvatar() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };
		AlertDialog.Builder builder = new AlertDialog.Builder(
				UpdateProfileActivity.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, 1);
				} else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(
							Intent.createChooser(intent, "Select File"), 2);
				} else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
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
		if (resultCode == RESULT_OK) {
			if (requestCode == FROM_CAMERA) {
				// File f = new File(Environment.getExternalStorageDirectory()
				// .toString());
				// for (File temp : f.listFiles()) {
				// if (temp.getName().equals("temp.jpg")) {
				// f = temp;
				// break;
				// }
				// }
				// try {
				// Bitmap bm;
				// BitmapFactory.Options btmapOptions = new
				// BitmapFactory.Options();
				//
				// bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
				// btmapOptions);
				//
				// // bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
				// avatarProfile.setImageBitmap(bm);
				//
				// String path = android.os.Environment
				// .getExternalStorageDirectory()
				// + File.separator
				// + "Phoenix" + File.separator + "default";
				// f.delete();
				// OutputStream fOut = null;
				// File file = new File(path, String.valueOf(System
				// .currentTimeMillis()) + ".jpg");
				// try {
				// fOut = new FileOutputStream(file);
				// bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
				// fOut.flush();
				// fOut.close();
				// } catch (FileNotFoundException e) {
				// e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// } catch (Exception e) {
				// e.printStackTrace();
				// }

				Bitmap photo = (Bitmap) data.getExtras().get("data");
				avatarProfile.setImageBitmap(photo);
			} else if (requestCode == FROM_GALARY) {
				Uri selectedImageUri = data.getData();

				String tempPath = getPath(selectedImageUri,
						UpdateProfileActivity.this);
				Bitmap bm;
				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
				avatarProfile.setImageBitmap(bm);
			}
		}
	}

	public String getPath(Uri uri, Activity activity) {
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = activity
				.managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}
