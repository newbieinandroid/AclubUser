/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author ntdong2012
 *
 */
public class UserRegister extends BaseResponse {

	/**
	 * @param number
	 */
	public UserRegister(User user) {
		this.user = user;
	}

	private static final long serialVersionUID = 1L;

	@SerializedName(value = "user")
	public User user;
}
