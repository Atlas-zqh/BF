package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JPanel {
	private static final long serialVersionUID = 1L;
//	private Image password = new ImageIcon("icon/iconfont-mima.jpg").getImage();
//	private JLabel password_icon = new JLabel(password);
//	JPanel panel = new JPanel();
	public JTextField textField;
	public JPasswordField passwordField;
	public JButton loginBt;
	public JButton signupBt;
	
	public LoginFrame() {
		JFrame loginFrame = new JFrame("Login");
		loginFrame.setSize(450, 370);
		loginFrame.setLocation(650, 400);

		textField=new JTextField("Your username here");
		passwordField = new JPasswordField("");
		loginBt=new JButton("Login in");
		signupBt=new JButton("Sign up");
		
		textField.setBounds(130, 100, 200, 35);

		passwordField.setBounds(130, 160, 200, 35);

		loginBt.setBounds(150, 210, 150, 30);
		signupBt.setBounds(150, 250, 150, 30);
		
		this.setLayout(null);
		this.repaint();
		
		this.add(textField);
		this.add(passwordField);
		this.add(loginBt);
		this.add(signupBt);
//		panel.add(password_icon);
//		password_icon.setBounds(0, 0, 50, 50);
		loginFrame.getContentPane().add(this);
		
		loginFrame.setVisible(true);
		loginFrame.setResizable(true);
	}
	
	public void paintComponents(Graphics g){
		Image password = new ImageIcon("icon/iconfont-mima.jpg").getImage();
		g.drawImage(password, 3, 4, this);
	}

}
