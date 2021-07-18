package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controllers.AuthController;
import controllers.UserController;

public class OtpForm extends JFrame implements ActionListener {
	Container container = getContentPane();
	JLabel formTitle = new JLabel("Welcome to OTP page");
	JLabel messageLabel = new JLabel("");
	JLabel userLabel = new JLabel("Enter OTP code");
	JTextField otpTextField = new JTextField();
	JButton checkOtpCode = new JButton("Conteniue");
	JButton backToLogin = new JButton("Back");

	public OtpForm() {
		this.setTitle("OTP Form");
		this.setVisible(true);
		this.setBounds(10, 10, 370, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.messageLabel.setText("Your OTP code sent to " + AuthController.getAuth().getEmail());
		setFonts();
		setLayoutManager();
		setLocationAndSize();
		addComponentsToContainer();
		addActionEvent();

	}

	public void setFonts() {
		formTitle.setFont(new Font("verdana", Font.BOLD, 20));
		messageLabel.setFont(new Font("verdana", Font.ITALIC, 12));
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		formTitle.setBounds(30, 70, 300, 30);
		messageLabel.setBounds(0, 100, 350, 30);
		userLabel.setBounds(20, 150, 100, 30);
		otpTextField.setBounds(150, 150, 200, 30);
		backToLogin.setBounds(50, 220, 100, 30);
		checkOtpCode.setBounds(200, 220, 100, 30);

	}

	public void addComponentsToContainer() {
		container.add(formTitle);
		container.add(messageLabel);
		container.add(userLabel);
		container.add(otpTextField);
		container.add(checkOtpCode);
		container.add(backToLogin);
	}

	public void addActionEvent() {
		// adding Action listener to components
		checkOtpCode.addActionListener(this);
		backToLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UserController userController = new UserController();
		if (e.getSource() == checkOtpCode) {

			if (otpTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "OTP code is required!");
				return;
			}

			if (!userController.checkOtpCode(otpTextField.getText())) {
				JOptionPane.showMessageDialog(this, "Invalid OTP code.");
				return;
			}

			this.dispose();
			new DashboardForm();
		}

		if (e.getSource() == backToLogin) {
			this.dispose();
			new LoginForm();
		}

	}

}
