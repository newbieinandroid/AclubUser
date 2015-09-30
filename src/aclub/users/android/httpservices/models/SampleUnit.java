/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author ntdong2012
 *
 */
public class SampleUnit extends SerializableClass{
	
	@SerializedName(value = "dv_id")
    public String dv_id;
    @SerializedName(value = "ten_dv")
    public String ten_dv;
    @SerializedName(value = "rate")
    public int rate;
}
