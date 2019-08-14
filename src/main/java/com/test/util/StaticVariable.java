package com.test.util;

public class StaticVariable {
	public static final String SECRET = "SECRET";
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final String SIGN_UP_URL = "/User/save";
	
	public static String starckTraceToString(Exception ex) {
		String result = ex.getMessage() + "\n";
		result += ex.toString() + "\n";
		StackTraceElement[] trace = ex.getStackTrace();
		for (int i = 0; i < trace.length; i++) {
			result += trace[i].toString() + "\n";
		}
		return result;
	}
}
