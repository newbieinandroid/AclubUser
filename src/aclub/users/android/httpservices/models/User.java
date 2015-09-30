/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author ntdong2012
 *
 */
public class User extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SerializedName(value = "id")
	private int id;
	@SerializedName(value = "name")
	private String name;
	@SerializedName(value = "phone")
	private String phone;

	/**
	 * 
	 */
	public User(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
