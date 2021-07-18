package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.UserController;
import models.User;
import util.ApplicationAssistant;

public class RegisterUserForm extends JFrame implements ActionListener {
	Container container = getContentPane();
	JLabel formTitle = new JLabel("User registration");
	JLabel userLabel = new JLabel("Username");
	JTextField userTextField = new JTextField();
	
	JLabel passwordLabel = new JLabel("Password");
	JPasswordField passwordField = new JPasswordField();
	
	JLabel emailLabel = new JLabel("Email");
	JTextField emailField = new JTextField();
	
	JLabel mobileLabel = new JLabel("Mobile");
	JTextField mobileField = new JTextField();
	
	JButton loginPage = new JButton("Login page");
	JButton signUpButton = new JButton("Sign up");

	public RegisterUserForm() {
		this.setTitle("Registration Form");
		this.setVisible(true);
		this.setBounds(10, 10, 370, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		setFonts();
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();

	}

	public void setFonts() {
		formTitle.setFont(new Font("verdana", Font.BOLD, 20));
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		formTitle.setBounds(30, 70, 300, 30);
		
		userLabel.setBounds(50, 150, 100, 30);
		userTextField.setBounds(150, 150, 200, 30);
		
		passwordLabel.setBounds(50, 220, 100, 30);
		passwordField.setBounds(150, 220, 200, 30);
		
		emailLabel.setBounds(50, 280, 100, 30);
		emailField.setBounds(150, 280, 200, 30);
		
		
		mobileLabel.setBounds(50, 340, 100, 30);
		mobileField.setBounds(150, 340, 200, 30);
		
		loginPage.setBounds(50, 400, 100, 30);
		signUpButton.setBounds(200, 400, 100, 30);
	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(formTitle);
		container.add(userLabel);
		container.add(userTextField);
		
		container.add(passwordLabel);
		container.add(passwordField);
		
		container.add(emailLabel);
		container.add(emailField);
		
		container.add(mobileLabel);
		container.add(mobileField);
		
		container.add(loginPage);
		container.add(signUpButton);
	}

	public void addActionEvent() {
		// adding Action listener to components
		loginPage.addActionListener(this);
		signUpButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserController userController = new UserController();
		if (e.getSource() == signUpButton) {
			if (userTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username is required!");
				return;
			}

			if (new String(passwordField.getPassword()).isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password is required!");
				return;
			}
			
			if (emailField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Email is required!");
				return;
			}
			
			if (!ApplicationAssistant.validateEmailAddress(emailField.getText())) {
				JOptionPane.showMessageDialog(this, "Email is required!");
				return;
			}
			
			if (mobileField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "mobile is required!");
				return;
			}
			
			if (!ApplicationAssistant.isPhoneNumber(mobileField.getText())) {
				JOptionPane.showMessageDialog(this, "mobile number must be 9 digit only\n"
						+ "Example 907732123");
				return;
			}

			User user = new User();
			user.setUsername(userTextField.getText());
			user.setMobile(mobileField.getText());
			user.setEmail(emailField.getText());
			if (!userController.register(user, new String(passwordField.getPassword()))) {
				JOptionPane.showMessageDialog(this, "Invalid registration!");
				return;
			}
			userTextField.setText("");
			mobileField.setText("");
			passwordField.setText("");
		}

		if (e.getSource() == loginPage) {
			this.dispose();
			new LoginForm();
		}

	}

}
