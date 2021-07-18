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

public class LoginForm extends JFrame implements ActionListener {
	Container container = getContentPane();
	JLabel formTitle = new JLabel("Welcome to login page");
	JLabel userLabel = new JLabel("Username");
	JLabel passwordLabel = new JLabel("Password");
	JTextField userTextField = new JTextField("");
	JPasswordField passwordField = new JPasswordField("");
	JButton loginButton = new JButton("Sign in");
	JButton registerUser = new JButton("New user");

	public LoginForm() {
		this.setTitle("Login Form");
		this.setVisible(true);
		this.setBounds(10, 10, 370, 420);
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
		passwordLabel.setBounds(50, 220, 100, 30);
		userTextField.setBounds(150, 150, 200, 30);
		passwordField.setBounds(150, 220, 200, 30);
		loginButton.setBounds(50, 300, 100, 30);
		registerUser.setBounds(200, 300, 100, 30);

	}

	public void addComponentsToContainer() {
		// Adding each components to the Container
		container.add(formTitle);
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(userTextField);
		container.add(passwordField);
		container.add(loginButton);
		container.add(registerUser);
	}

	public void addActionEvent() {
		// adding Action listener to components
		loginButton.addActionListener(this);
		registerUser.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserController userController = new UserController();
		if (e.getSource() == loginButton) {
			if (userTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username is required!");
				return;
			}

			if (passwordField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password is required!");
				return;
			}
			 
			if (!userController.login(userTextField.getText(), passwordField.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid Username or password!.");
				return;
			}
			
			if(!userController.sendOtpCode()){
				JOptionPane.showMessageDialog(this, "Error while sending OTP code.");
				return;
			}
			this.dispose();
			new OtpForm();
		}

		if (e.getSource() == registerUser) {
			this.dispose();
			new RegisterUserForm();
		}

	}

}
