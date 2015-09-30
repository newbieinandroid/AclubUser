/**
 * 
 */
package aclub.users.android.httpservices.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author ntdong2012
 *
 */
public class SampleResponse extends BaseResponse{
	
	@SerializedName(value = "status")
    public String status;
    @SerializedName(value = "data")
    public List<SampleUnit> data;
}
