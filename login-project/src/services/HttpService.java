package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class HttpService {
	public static boolean sendOTP(String mobile, String message) {
		try {
			
			String apiKey = "apikey=" + "NmQ3MDZmMzUzNTM1NDk0NDQ1MzI1NzRhMzU0NjRlNjY=";
			  message = "&amp;message=" +  message;
			String sender = "&amp;sender=" + "Abdirahman";
			String numbers = "&amp;numbers=252" +mobile;
		
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			System.out.println(data);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
			stringBuffer.append(line);
			}
			rd.close();
			System.out.println("ok");
			 return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			return false;
			}
	}
}
