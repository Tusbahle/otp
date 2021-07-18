package controllers;

import models.User;

public class AuthController {
	private static User auth;

	/**
	 * @return the auth
	 */
	public static User getAuth() {
		return auth;
	}

	public static  void setAuth(User user) {
		  auth = user;
	}
	
	
	public static boolean isLogin() {
		return auth != null;
	}
	
	
	public static void logOut() {
		 auth = null;
	}
}
