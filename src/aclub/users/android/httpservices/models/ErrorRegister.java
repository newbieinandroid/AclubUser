/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class ErrorRegister extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SerializedName(value = "errors")
	private String errors;

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

}
