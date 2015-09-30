/**
 * 
 */
package aclub.users.android.httpservices;

/**
 * Class represent error
 * 
 * @author ntdong2012
 *
 */
public class ErrorMessage {

	private int httpStatusCode;
	private int resultCode;
	private String errorMessage;
	private String errorBody;

	public ErrorMessage() {
		httpStatusCode = 200;
		errorMessage = "";
	}

	public ErrorMessage(int status, String msg) {
		httpStatusCode = status;
		errorMessage = msg;
	}

	public ErrorMessage(int status, int resultCode, String msg) {
		httpStatusCode = status;
		this.setResultCode(resultCode);
		errorMessage = msg;
	}

	public void setStatusCode(int status) {
		httpStatusCode = status;
	}

	public int getStatusCode() {
		return httpStatusCode;
	}

	public String getMessage() {
		return errorMessage;
	}

	public void setMessage(String msg) {
		errorMessage = msg;
	}

	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorBody() {
		return errorBody;
	}

	public void setErrorBody(String errorBody) {
		this.errorBody = errorBody;
	}
}
