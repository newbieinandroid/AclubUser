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
public class CusDialogNotify extends ParentDialog {
	private String mContent;
	private String title;

	public CusDialogNotify(Context context, String content, String title) {
		super(context);
		mContent = content;
		this.title = title;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_custom_notify);
		this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(0x11000000));

		TextView tvContent = (TextView) findViewById(R.id.tv_content_dialog_custom_notify);
		tvContent.setText(mContent);
		TextView tvTitle = (TextView) findViewById(R.id.tv_title_dialog_custome_notify);
		tvTitle.setText(title);

		TextView tvOk = (TextView) findViewById(R.id.tv_ok_dialog_custom_notify);
		tvOk.setOnClickListener(this);

	}

	private void onSubmit() {
		if (myEvent != null) {
			myEvent.onSubmit("0");
		}
	}

	@Override
	public void dismiss() {
		if (myEvent != null) {
			myEvent.onDismiss(0);
		}
		super.dismiss();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tv_ok_dialog_custom_notify:
			onSubmit();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

}
