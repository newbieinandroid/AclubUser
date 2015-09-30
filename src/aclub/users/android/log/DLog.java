package aclub.users.android.log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import aclub.users.android.utils.CommonValues;
import aclub.users.android.utils.DateUtils;

public class DLog {

	private static final int V = 0;
	private static final int D = 1;
	private static final int I = 2;
	private static final int W = 3;
	private static final int E = 4;

	public static final int ENVIRONTMENT = 1000;
	public static final int OTHERS = 40;

	private static final String TAG = "AClub";

	static class LogInfo {
		public String filename = null;
		public String className = null;
		public String methodName = null;
		public String description = null;
		public int lineNumber = 0;

		public String toString() {
			String shotClassName = className.substring(
					className.lastIndexOf(".") + 1, className.length());
			return shotClassName + ":" + methodName + "(" + lineNumber + ")";
		}
	}

	private static LogInfo getLogInfo(int index) {
		LogInfo info = new LogInfo();
		Throwable stack = new Throwable().fillInStackTrace();
		StackTraceElement[] trace = stack.getStackTrace();
		if (index + 1 < trace.length) {
			if (trace[index].getFileName() != null) {
				info.filename = trace[index].getFileName().replaceAll(".java",
						"");
			} else {
				info.filename = "-";
			}

			if (info.filename.compareTo("ActivityFont") == 0
					|| info.filename.compareTo("ActivityThread") == 0) {

				if (trace[index + 1].getFileName() != null) {
					info.filename = trace[index].getFileName().replaceAll(
							".java", "");
				} else {
					info.filename = "-";
				}

				info.className = trace[index + 1].getClassName();
				info.methodName = trace[index + 1].getMethodName();
				info.lineNumber = trace[index + 1].getLineNumber();
			} else {
				info.className = trace[index].getClassName();
				info.methodName = trace[index].getMethodName();
				info.lineNumber = trace[index].getLineNumber();
			}

		}

		for (int i = trace.length - 1; i >= 3; i--) {

			String className = trace[i].getClassName();
			String methodName = trace[i].getMethodName();
			int lineNumber = trace[i].getLineNumber();

			if (className.contains("cloudprint")) {
				String shotClassName = className.substring(
						className.lastIndexOf(".") + 1, className.length());
				info.description = new StringBuilder().append(info.description)
						.append(shotClassName).append(':').append(methodName)
						.append('(').append(lineNumber).append(')').toString();
			}
		}

		return info;
	}

	public static void v(Object... values) {
		int kind = OTHERS;
		try {
			kind = (Integer) values[0];
		} catch (Exception e) {
			// TODO: handle exception
		}
		print(kind, V, values);
	}

	public static void d(Object... values) {
		int kind = OTHERS;
		try {
			kind = (Integer) values[0];
		} catch (Exception e) {
			// TODO: handle exception
		}
		print(kind, D, values);
	}

	public static void i(Object... values) {
		int kind = OTHERS;
		try {
			kind = (Integer) values[0];
		} catch (Exception e) {
			// TODO: handle exception
		}
		print(kind, I, values);
	}

	public static void w(Object... values) {
		int kind = OTHERS;
		try {
			kind = (Integer) values[0];
		} catch (Exception e) {
			// TODO: handle exception
		}
		print(kind, W, values);
	}

	public static void e(Exception e) {
		int kind = OTHERS;
		print(kind, E, e);
	}

	private static void print(int kind, int type, Object... values) {

		LogInfo info = null;
		String strValus = "";

		if (values != null && values.length > 0) {
			if (values.length > 0 && values[0] != null
					&& values[0].toString().compareTo("+") == 0) {
				info = getLogInfo(4);
				LogInfo preInfo = getLogInfo(3);
				values[0] = preInfo.methodName;
			} else {
				info = getLogInfo(3);
			}

			if (kind == ENVIRONTMENT) {
				if (values.length == 1) {
					strValus = "";
				} else {
					ArrayList<Object> n = new ArrayList<Object>();
					for (int i = 0; i < values.length - 1; i++) {
						n.add(values[i + 1]);
					}
					int size = n.size();
					Object[] m = new Object[size];
					for (int i = 0; i < size; i++) {
						m[i] = n.get(i);
					}
					strValus += " " + Arrays.toString(m);
				}

			} else {
				strValus += " " + Arrays.toString(values);
			}

		} else {
			info = getLogInfo(3);
		}

		if (CommonValues.isShowLog && kind != ENVIRONTMENT) {
			String strPrintString = info.methodName + "(" + info.lineNumber
					+ ") " + strValus;

			switch (type) {
			case V:
				android.util.Log.v(TAG, info.filename + " : " + strPrintString);
				break;
			case D:
				android.util.Log.d(TAG, info.filename + " : " + strPrintString);
				break;
			case I:
				android.util.Log.i(TAG, info.filename + " : " + strPrintString);
				break;
			case W:
				android.util.Log.w(TAG, info.filename + " : " + strPrintString);
				break;
			case E:
				android.util.Log.e(TAG, info.filename + " : " + strPrintString);

				if (values[0] instanceof Exception) {
					Exception e = (Exception) values[0];
					e.printStackTrace();
				}
				break;
			}
		}

		if (CommonValues.isShowLog && kind == ENVIRONTMENT) {

			String strType = "";

			switch (type) {
			case V:
				strType = "(V)";
				break;
			case D:
				strType = "(D)";
				break;
			case I:
				strType = "(I)";
				break;
			case W:
				strType = "(W)";
				break;
			case E:
				strType = "(E)";
				break;
			}

			String strPrintString = DateUtils.getCurrentTime() + "\t" + strType
					+ "\t" + info.filename + "\t" + info.methodName + "("
					+ info.lineNumber + ")" + "\t" + strValus;

			FileWriter logFile = null;
			BufferedWriter out = null;

			try {
				out = new BufferedWriter(logFile);

				out.write(strPrintString);
				out.newLine();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null)
						out.close();
					if (logFile != null)
						logFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
