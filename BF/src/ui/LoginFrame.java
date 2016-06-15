package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class LoginFrame extends JPanel {

	public static JTextField textField;
	public JPasswordField passwordField;
	public JButton loginBt;
	public JButton signupBt;

	public ImageIcon password = new ImageIcon("icon/iconfont-mima-small.png");
	public ImageIcon user = new ImageIcon("icon/iconfont-yonghu-small.png");
	public ImageIcon brainfuck = new ImageIcon("icon/brainfuck.png");
	public ImageIcon login = new ImageIcon("icon/login-small.png");
	public ImageIcon signup = new ImageIcon("icon/signup-small.png");
	public ImageIcon icon = new ImageIcon("icon/brainicon-small.png");
//	public static JMenuItem logout = new JMenuItem("Log out");

	public JLabel password_icon = new JLabel(password);
	public JLabel user_icon = new JLabel(user);
	public JLabel logo = new JLabel(brainfuck);

	public JFrame loginFrame = new JFrame("Login");
	public static boolean logined = false;
	public static boolean signed = false;

	public LoginFrame() {

		loginFrame.setSize(450, 370);
		loginFrame.setLocation(550, 400);
		loginFrame.setIconImage(icon.getImage());

		textField = new JTextField("Your username here");
		passwordField = new JPasswordField("");
		loginBt = new JButton(login);
		loginBt.setBounds(125, 220, 200, 33);
		signupBt = new JButton(signup);
		signupBt.setBounds(125, 265, 200, 33);

		textField.setBounds(110, 100, 250, 35);
		passwordField.setBounds(110, 160, 250, 35);
		user_icon.setBounds(55, 90, 52, 52);
		password_icon.setBounds(49, 150, 60, 60);
		logo.setBounds(120, 20, 200, 80);

		this.setLayout(null);
		this.repaint();

		this.add(textField);
		this.add(passwordField);
		this.add(loginBt);
		this.add(signupBt);
		this.add(user_icon);
		this.add(password_icon);
		this.add(logo);

		loginFrame.getContentPane().add(this);
		loginFrame.setVisible(true);
		loginFrame.setResizable(false);

		loginBt.addActionListener(new LogActionListener());
		signupBt.addActionListener(new SignUpActionListener());
	}

	// public static void changeNameInAccountMenu(boolean logined){
	// if(logined){
	// MainFrame.account.remove(0);
	// MainFrame.account.setText("User : "+textField.getText());
	// MainFrame.account.add(logout);
	// logout.addActionListener(new LogoutActionListener());
	// }
	// }

	// public static void changeBack(boolean logined){
	// if(!logined){
	// MainFrame.account.remove(0);
	// MainFrame.account.setText("User : "+textField.getText());
	// }
	// }

	public class LogActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(logined){
				MainFrame.account.setText("User : "+LoginFrame.textField.getText());
			}
			// TODO Auto-generated method stub
			try {
				logined = RemoteHelper.getInstance().getUserService().login(textField.getText(),
						new String(passwordField.getPassword()));
				if (logined) {
					loginFrame.dispose();
					if(logined){
//						account.removeAll();
						MainFrame.login.setVisible(false);
						MainFrame.logout.setVisible(true);
						MainFrame.account.setText("User : "+LoginFrame.textField.getText());}
				} else {
					LoginErrorFrame lef = new LoginErrorFrame();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// MainFrame.account.setText("User : "+textField.getText());
		}
	}

	public class SignUpActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				signed = RemoteHelper.getInstance().getUserService().signup(textField.getText(),
						new String(passwordField.getPassword()));
				if (signed) {
					loginFrame.dispose();
					SignedFrame sf = new SignedFrame();
					logined = true;
					// LoginFrame.changeNameInAccountMenu(logined);
				} else {
					// 已存在用户，signed为false
					UserExistedFrame uef = new UserExistedFrame();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
