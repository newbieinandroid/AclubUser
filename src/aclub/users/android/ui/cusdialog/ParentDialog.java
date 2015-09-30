/**
 * 
 */
package aclub.users.android.ui.cusdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class ParentDialog extends Dialog implements
		android.view.View.OnClickListener {

	public EventDialog myEvent = null;

	public ParentDialog(Context context) {
		super(context);
	}

	public ParentDialog(Context context, int style) {
		super(context, style);
	}

	public void setEvendialog(EventDialog event) {
		myEvent = event;
	}

	public void onClick(View v) {

	}
}