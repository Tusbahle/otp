package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controllers.AuthController;
import controllers.UserController;

public class DashboardForm  extends JFrame implements ActionListener {
	Container container = getContentPane();
	JLabel formTitle = new JLabel("Welcome to Dashboard");
	JLabel messageLabel = new JLabel("");
	JButton logOut = new JButton("Logout");

	public DashboardForm() {
		this.setTitle("Dashboard");
		this.setVisible(true);
		this.setBounds(10, 10, 500, 500);
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
		messageLabel.setFont(new Font("verdana", Font.BOLD, 16));
	}

	public void setLayoutManager() {
		container.setLayout(null);
	}

	public void setLocationAndSize() {
		formTitle.setBounds(30, 70, 300, 30);
		messageLabel.setBounds(50, 150, 400, 150);
		logOut.setBounds(200, 300, 100, 30);

	}

	public void addComponentsToContainer() {
		this.messageLabel.setText("<html>"+  AuthController.getAuth().toString() +"</html>");
		container.add(formTitle);
		container.add(messageLabel);
		container.add(logOut);
	}

	public void addActionEvent() {
		logOut.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logOut) {
			AuthController.logOut();
			this.dispose();
			new LoginForm();
		}


	}

}
