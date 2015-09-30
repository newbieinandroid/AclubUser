/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author ntdong2012
 *
 */
public class BaseResponse extends SerializableClass {
	
	@SerializedName(value = "result")
	public int result;
}
