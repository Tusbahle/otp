package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;

import models.User;
import services.HttpService;
import util.DatabaseConn;
import util.EmailUtil;

public class UserController {
	DatabaseConn databaseConn;

	public UserController() {
		databaseConn = DatabaseConn.getInstance();
	}

	public boolean register(User user, String password) {
		if (isUserExist(user.getUsername(), user.getMobile())) {
			JOptionPane.showMessageDialog(null, "Username or mobile is exists!.");
			return false;
		}
		if (user.getUsername().isEmpty() || password.isEmpty() || user.getMobile().isEmpty()|| user.getEmail().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill in all fields");
			return false;
		}
		password = DigestUtils.shaHex(password);

		String qu = "INSERT INTO users (username, password, mobile, email, isActive) VALUES ('" + user.getUsername()
				+ "','" + password + "','" + user.getMobile() + "','"+ user.getEmail() + "',true)";
		if (databaseConn.execAction(qu)) {
			JOptionPane.showMessageDialog(null, "Your account has been registered!.");
			return true;
		} else // Error
		{
			return false;
		}
	}

	public boolean isUserExist(String username, String password) {
		try {

			String checkstmt = "SELECT COUNT(*) FROM users WHERE username=? or mobile = ?";
			PreparedStatement stmt = DatabaseConn.getConnection().prepareStatement(checkstmt);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count > 0);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public boolean login(String username, String password) {
		try {
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Username or password is required!.");
				return false;
			}
			String checkstmt = "SELECT id, username, mobile, email FROM users WHERE username=? AND password = ?";
			PreparedStatement stmt = DatabaseConn.getConnection().prepareStatement(checkstmt);
			stmt.setString(1, username);
			stmt.setString(2, DigestUtils.shaHex(password));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				AuthController.setAuth(user);
				return (count > 0);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public boolean checkOtpCode(String otpCode) {
		try {
			if (otpCode.isEmpty()) {
				JOptionPane.showMessageDialog(null, "OTP is required!.");
				return false;
			}
			String checkstmt = "UPDATE users SET otpCode = NULL WHERE otpCode=? AND id = ?";
			PreparedStatement stmt = DatabaseConn.getConnection().prepareStatement(checkstmt);
			stmt.setString(1, otpCode);
			stmt.setString(2, AuthController.getAuth().getId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public boolean sendOtpCode() {
		try {
			if (!AuthController.isLogin()) {
				JOptionPane.showMessageDialog(null, "Please login first!.");
				return false;
			}
			User auth = AuthController.getAuth();

			String otpCode = generateOtpCode(5);
			String message = "Hello " + auth.getUsername() + "!, Your OTP code is: " + otpCode;
		       new Thread(() -> {
			EmailUtil.sendMail(auth.getEmail(), message);
		       }).start();
			String checkstmt = "UPDATE users SET otpCode = ? WHERE id = ?";

			PreparedStatement stmt = DatabaseConn.getConnection().prepareStatement(checkstmt);
			stmt.setString(1, otpCode);
			stmt.setString(2, auth.getId());
			return stmt.executeUpdate() > 0;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	public String generateOtpCode(int length) {
		String numbers = "1234567890";
		Random random = new Random();
		char[] otp = new char[length];

		for (int i = 0; i < length; i++) {
			otp[i] = numbers.charAt(random.nextInt(numbers.length()));
		}
		return String.valueOf(otp);

	}
}
