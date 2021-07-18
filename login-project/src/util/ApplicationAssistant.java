package util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ApplicationAssistant {

	public static final String ICON_IMAGE_LOC = "/resources/icon.png";
	public static final String MAIL_CONTENT_LOC = "/resources/mail_content.html";
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	public static Object loadWindow(URL loc, String title) {

		return null;
	}

	public static String formatDateTimeString(Date date) {
		return DATE_TIME_FORMAT.format(date);
	}

	public static String formatDateTimeString(Long time) {
		return DATE_TIME_FORMAT.format(new Date(time));
	}

	public static String getDateString(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static boolean validateEmailAddress(String emailID) {
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(emailID).matches();
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean isPhoneNumber(String str) {
		if (!isNumeric(str)) {
			return false;
		}
		return str.length() == 9;
	}
}
