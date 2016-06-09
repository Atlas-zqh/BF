package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UserExistedFrame extends JPanel{
	public ImageIcon icon=new ImageIcon("icon/brainicon-small.png");
	public ImageIcon confirmImg=new ImageIcon("icon/tryagain.png");
	public JFrame notice=new JFrame("Notification");
	private Font font = new Font("TimesNewRoman", Font.PLAIN, 16);
	private Font nameFont=new Font("TimesNewRoman",Font.BOLD,16);
	
	public UserExistedFrame(){

		notice.setSize(350, 270);
		notice.setLocation(600, 470);
		notice.setIconImage(icon.getImage());
		
		JLabel sorryLabel=new JLabel("Sorry , username :",JLabel.CENTER );
		sorryLabel.setFont(font);
		sorryLabel.setBounds(70, 35, 200,30);
		JLabel nameLabel=new JLabel(LoginFrame.textField.getText(),JLabel.CENTER);
		nameLabel.setFont(nameFont);
		nameLabel.setBounds(70, 75, 200, 30);
		JLabel existedLabel=new JLabel("has existed !",JLabel.CENTER);
		existedLabel.setFont(font);
		existedLabel.setBounds(70, 115, 200, 30);
		
		JButton confirm=new JButton(confirmImg);
		confirm.setBounds(98, 160, 150, 33);
		
		this.setLayout(null);
		this.repaint();
		this.add(sorryLabel);
		this.add(nameLabel);
		this.add(existedLabel);
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
