package ui;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SignedFrame extends JPanel{

	public ImageIcon icon=new ImageIcon("icon/brainicon-small.png");
	public ImageIcon confirmImg=new ImageIcon("icon/confirm.png");
	public JFrame notice=new JFrame("Notification");
	private Font font = new Font("TimesNewRoman", Font.PLAIN, 16);
	
	public SignedFrame(){

		notice.setSize(350, 270);
		notice.setLocation(650, 400);
		notice.setIconImage(icon.getImage());
		
		JLabel helloAndName=new JLabel("Hello , "+LoginFrame.textField.getText()+" !",JLabel.CENTER);
		helloAndName.setFont(font);
		helloAndName.setBounds(70, 50, 200,30);
		JLabel rememberPassword=new JLabel("Please remember your password !");
		rememberPassword.setFont(font);
		rememberPassword.setBounds(50,90,300,30);
		
		JButton confirm=new JButton(confirmImg);
		confirm.setBounds(98, 150, 150, 33);
		
		this.setLayout(null);
		this.repaint();
		this.add(helloAndName);
		this.add(rememberPassword);
		this.add(confirm);
		
		notice.getContentPane().add(this);
		notice.setVisible(true);
		notice.setResizable(false);
	}
}
