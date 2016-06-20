package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginErrorFrame extends JPanel{
	public ImageIcon icon=new ImageIcon("icon/brainicon-small.png");
	public ImageIcon confirmImg=new ImageIcon("icon/tryagain.png");
	public JFrame notice=new JFrame("Notification");
	private Font font = new Font("TimesNewRoman", Font.PLAIN, 16);
	
	public LoginErrorFrame(){

		notice.setSize(350, 270);
		notice.setLocation(600, 470);
		notice.setIconImage(icon.getImage());
		JLabel noUserLabel=new JLabel("The user doesn't exist !",JLabel.CENTER);
		noUserLabel.setFont(font);
		noUserLabel.setBounds(70, 55, 200, 30);
		JLabel wrongPasswordLabel=new JLabel("or you entered a WRONG password !",JLabel.CENTER);
		wrongPasswordLabel.setFont(font);
		wrongPasswordLabel.setBounds(30, 100, 300, 30);

		
		JButton confirm=new JButton(confirmImg);
		confirm.setBounds(98, 150, 150, 33);
		
		this.setLayout(null);
		this.repaint();
		this.add(noUserLabel);
		this.add(wrongPasswordLabel);
		this.add(confirm);
		
		notice.getContentPane().add(this);
		notice.setVisible(true);
		notice.setResizable(false);
		
		confirm.addActionListener(new OKActionListener());
	}
	
	class OKActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			notice.dispose();
		}
		
	}
}
