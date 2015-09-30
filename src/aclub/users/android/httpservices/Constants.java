/**
 * 
 */
package aclub.users.android.httpservices;

/**
 * @author ntdong2012
 *
 */
public class Constants {
	
	interface HttpConst {
		// Base URL
		String BASE_URL = "http://localhost:8081/api";
		String WEATHER_ICON_URL = "http://openweathermap.org/img/w/%s.png";
		String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=Hanoi,vn&units=metric&APPID=4ae142f2206efa219d95e6de1b624664";

		// Login path
		String LOGIN_API = "/users/login";
		// Get To-do list path
		String GET_TODO_LIST_API = "/get_todo_list";
		// Delete To-do item
		String DELETE_TODO_ITEM_API = "/delete_todo";
		// Get Memo list path
		String GET_MEMO_LIST_API = "/get_memo_list";
		// Get Service info path
		String GET_SERVICE_INFO_API = "/get_train_service_information";
	}
	
	long TIME_REQUEST_SERVER = 30*1000; // 30 minutes request to server to get todoList;
	
	interface ApiConst {
        int API_SAMPLE = 0;
        int API_LOGIN = 1;
        int GET_TODO_LIST_API = 2;
        int GET_CUR_WEATHER_INFO_API = 3;
        int DEL_TODO_ITEM = 4;
        int GET_MEMO_LIST_API = 5;
        int GET_SERVICE_INFO_API = 6;
        
        
        int PHONE_REGISTRAION = 7;
    }
	
	
	interface ErrorCode {
        // Success
        int ERR_SUCCESS = 0;
        // MyAQUA ID Incorrect
        int ERR_INCORRECT_ID = 10;
        // Incorrect password
        int ERR_INCORRECT_PWD = 11;
        // Incorrect token
        int ERR_INCORRECT_TOKEN = 12;
        // Incorrect digi ID
        int ERR_INCORRECT_DIGI_ID = 13;
        // Incorrect parameter
        int ERR_INCORRECT_PARAM = 14;
        // No data(There is no data which can return)
        int ERR_NO_DATA = 15;
    }

}
