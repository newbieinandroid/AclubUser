/**
 * 
 */
package aclub.users.android.httpservices;

import org.json.JSONObject;

import aclub.users.android.httpservices.models.SerializableClass;
import aclub.users.android.httpservices.models.UserRegister;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * @author ntdong2012
 *
 */
public class JsonHelper {

	/**
	 * This method serializes the specified object into its equivalent Json
	 * representation
	 *
	 * @param src
	 *            the object for which Json representation is to be created
	 *            setting for Gson
	 * @return Json representation of src
	 */
	public static String toJson(Object src) {
		Gson gson = new Gson();
		try {
			return gson.toJson(src);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method deserializes the specified Json into an object of the
	 * specified class.
	 *
	 * @param json
	 *            the string from which the object is to be deserialized
	 * @param classOfT
	 *            the class of T
	 * @param <T>
	 *            class which is serializable
	 * @return an object of type T from the string
	 */
	public static <T extends SerializableClass> T fromJson(String json,
			Class<T> classOfT) {
		Gson gson = new Gson();
		try {
			return gson.fromJson(json, classOfT);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
//	public static <T extends SerializableClass> T objectFromJson(String json, Class<T> classOfT) {
//		
//	}

	public static String toJson(UserRegister user) {
		try {

			JSONObject jsonObj = new JSONObject();
			JSONObject jsonAdd = new JSONObject();
			jsonAdd.put("phone", user.user.getPhone());
			jsonObj.put("user", jsonAdd);

			return jsonObj.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";

	}

}
