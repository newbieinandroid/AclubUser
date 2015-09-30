/**
 * 
 */
package aclub.users.android.httpservices;

import aclub.users.android.httpservices.models.BaseResponse;

/**
 * @author ntdong2012
 *
 */
public interface ResponseHandler {

	/**
	 * Handler in case of successful request
	 * 
	 * @param response
	 *            response by HTTP response
	 */
	void onSuccess(BaseResponse response);

	/**
	 * Handler in case of failure request
	 * 
	 * @param error
	 *            error return by HTTP response
	 */
	void onError(ErrorMessage error);
}
