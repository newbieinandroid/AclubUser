/**
 * 
 */
package aclub.users.android.httpservices;

import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import aclub.users.android.log.DLog;
import aclub.users.android.utils.StringUtils;
import android.content.Context;

import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author ntdong2012
 *
 */
public class RestClient {
	private static final String BASE_URL = "http://aclub.vn/api/v1/";
	public static String TAG = "RestClient";
	private static AsyncHttpClient _asyncClient = new AsyncHttpClient();
	static {
		_asyncClient.setTimeout(10 * 1000);
	}

	/**
	 * Build header for HTTP request
	 *
	 * @return header that need for request. It's usually contain authenticate
	 *         info such as token, user name...
	 */
	private static Header[] buildHeader() {
		// Header[] header = { new BasicHeader("Content-Type",
		// "application/json;charset=Windows-31J"),
		Header[] header = {
				new BasicHeader("Content-Type",
						"application/json;charset=UTF-8"),
				new BasicHeader("application_token",
						"b0bb724a0c6a6654742a6b667de939998ef70c6b2569516054a4ee"),
		// new BasicHeader("X-REST-APP-TOKEN", PosApplication.getAccessToken()),
		// new BasicHeader("X-REST-APP", "My app"),
		// new BasicHeader("X-REST-SHOP", PosApplication.getShopId())
		};

		return header;

	}

	/**
	 * Create absolute URL to send the request to.
	 *
	 * @param relativeUrl
	 *            The relative URL. If This parameter contain http:// -> this
	 *            case is considered as absolute path
	 * @return Full URL to send the request to.
	 */
	private static String getAbsoluteUrl(String relativeUrl) {
		if (!StringUtils.isEmpty(relativeUrl)
				&& (relativeUrl.contains("http://") || relativeUrl
						.contains("http://"))) {
			return relativeUrl;
		}
		return BASE_URL + relativeUrl;
	}

	/**
	 * Cancel the HTTP request
	 *
	 * @param context
	 *            context that associated with request
	 */
	public static void cancelRequest(Context context) {
		_asyncClient.cancelRequests(context, true);
	}

	/**
	 * Send GET method
	 * 
	 * @param context
	 *            Context to execute request against
	 * @param url
	 *            the relative URL to send the request to.
	 * @param params
	 *            additional GET parameters to send with the request.
	 * @param responseHandler
	 *            the response handler instance that should handle the response.
	 */
	public static void get(Context context, String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		DLog.d(TAG, url);
		DLog.d(TAG, "param: " + params);
		Header[] header = buildHeader();

		_asyncClient.get(context, getAbsoluteUrl(url), header, params,
				responseHandler);
	}

	/**
	 * Send POST method
	 *
	 * @param context
	 *            Context to execute request against
	 * @param url
	 *            the relative URL to send the request to.
	 * @param params
	 *            serialized request param object.
	 * @param responseHandler
	 *            the response handler instance that should handle the response.
	 */
	public static void post(Context context, String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		DLog.d(TAG, url);
		Header[] header = buildHeader();

		_asyncClient.post(context, getAbsoluteUrl(url), header, params, null,
				responseHandler);
	}

	/**
	 * Send POST method with custom body encoded
	 *
	 * @param context
	 *            Context to execute request against
	 * @param url
	 *            the relative URL to send the request to.
	 * @param jsonParams
	 *            request parameters in JSON format.
	 * @param responseHandler
	 *            the response handler instance that should handle the response.
	 */
	public static void post(Context context, String url, String jsonParams,
			AsyncHttpResponseHandler responseHandler) {
		DLog.d(TAG, url);
		Header[] header = buildHeader();

		StringEntity entity = null;
		try {
			entity = new StringEntity(jsonParams, HTTP.UTF_8);
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		}
		_asyncClient.post(context, getAbsoluteUrl(url), header, entity, null,
				responseHandler);
	}

	/**
	 * Send POST method without header and custom body encoded
	 *
	 * @param context
	 *            Context to execute request against
	 * @param url
	 *            the relative URL to send the request to.
	 * @param jsonParams
	 *            request parameters in JSON format.
	 * @param responseHandler
	 *            the response handler instance that should handle the response.
	 */
	public static void postWithoutHeader(Context context, String url,
			String jsonParams, AsyncHttpResponseHandler responseHandler) {
		DLog.d(TAG, url);
		// Header[] header = buildHeader();

		StringEntity entity = null;
		try {
			entity = new StringEntity(jsonParams, HTTP.UTF_8);
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		}

		_asyncClient.post(context, getAbsoluteUrl(url), entity, null,
				responseHandler);
	}

	/**
	 * Send DELETE method
	 *
	 * @param context
	 *            Context to execute request against
	 * @param url
	 *            the relative URL to send the request to.
	 * @param params
	 *            additional GET parameters to send with the request.
	 * @param responseHandler
	 *            the response handler instance that should handle the response.
	 */
	public static void delete(Context context, String url,
			RequestParams params, AsyncHttpResponseHandler responseHandler) {
		Header[] header = buildHeader();
		DLog.d(TAG, url);
		_asyncClient.delete(context, getAbsoluteUrl(url), header, params,
				responseHandler);
	}

	/**
	 * Send PUT method
	 *
	 * @param context
	 *            Context to execute request against
	 * @param url
	 *            the relative URL to send the request to.
	 * @param params
	 *            additional PUT parameters to send with the request.
	 * @param jsonParams
	 *            request parameters in JSON format.
	 * @param responseHandler
	 *            the response handler instance that should handle the response.
	 */
	public static void put(Context context, String url, RequestParams params,
			String jsonParams, AsyncHttpResponseHandler responseHandler) {
		DLog.d(TAG, url);
		// Create the request header
		Header[] header = buildHeader();

		StringEntity entity = null;
		try {
			// entity = new StringEntity(jsonParams, "Shift_JIS");
			entity = new StringEntity(jsonParams, HTTP.UTF_8);
			// entity.setContentType("application/json;charset=Shift_JIS");
		} catch (UnsupportedCharsetException e) {
			e.printStackTrace();
		}

		_asyncClient.put(context, getAbsoluteUrl(url), header, entity, null,
				responseHandler);
	}
}
