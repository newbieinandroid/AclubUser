/**
 * 
 */
package aclub.users.android.ui.cusdialog;

import aclub.users.android.R;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class CusDialogQuestion extends ParentDialog {

	private String mTitle;
	private String mContent;
	private String mCancel;
	private String mOk;

	/**
	 * @param context
	 */
	public CusDialogQuestion(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param title
	 * @param content
	 * @param okTitle
	 * @param cancelTitle
	 */
	public CusDialogQuestion(Context context, String title, String content,
			String okTitle, String cancelTitle) {
		super(context);
		this.mTitle = title;
		this.mContent = content;
		this.mOk = okTitle;
		this.mCancel = cancelTitle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Dialog#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_custom_question);
		this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(0x11000000));

		TextView tvTitle = (TextView) findViewById(R.id.tv_title_dialog_custom_question);
		if (mTitle != null) {
			tvTitle.setText(mTitle);
		} else {
			tvTitle.setVisibility(View.GONE);
		}
		TextView tvContent = (TextView) findViewById(R.id.tv_content_dialog_custom_question);
		if (mContent != null) {
			tvContent.setText(mContent);
		} else {
			tvContent.setVisibility(View.GONE);
		}

		TextView tvOk = (TextView) findViewById(R.id.tv_ok_dialog_custom_question);
		tvOk.setText(mOk);
		tvOk.setOnClickListener(this);
		TextView tvCancel = (TextView) findViewById(R.id.tv_cancel_dialog_custom_question);
		tvCancel.setText(mCancel);
		tvCancel.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Dialog#dismiss()
	 */
	@Override
	public void dismiss() {
		if (myEvent != null) {
			myEvent.onDismiss(0);
		}
		super.dismiss();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.ui.cusdialog.ParentDialog#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tv_ok_dialog_custom_question:
			onSubmit();
			break;
		case R.id.tv_cancel_dialog_custom_question:
			onCancel();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

	private void onSubmit() {
		if (myEvent != null) {
			myEvent.onSubmit("0");
		}
	}

	private void onCancel() {
		if (myEvent != null) {
			myEvent.onCancel();
		}
	}

}
