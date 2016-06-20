package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotLoginFrame extends JPanel {
	public ImageIcon icon = new ImageIcon("icon/brainicon-small.png");
	public ImageIcon confirmImg = new ImageIcon("icon/tryagain.png");
	public JFrame notice = new JFrame("Notification");
	private Font font = new Font("TimesNewRoman", Font.PLAIN, 16);

	public void paintComponent(Graphics g) {
		ImageIcon a = new ImageIcon("background/rainbow.jpg");
		g.drawImage(a.getImage(), 0, 0, null);
	}

	public NotLoginFrame() {

		notice.setSize(350, 270);
		notice.setLocation(600, 470);
		notice.setIconImage(icon.getImage());
		JLabel noLoginLabel = new JLabel("You haven't logined yet !", JLabel.CENTER);
		noLoginLabel.setFont(font);
		noLoginLabel.setBounds(70, 55, 200, 60);

		JButton confirm = new JButton(confirmImg);
		confirm.setBounds(98, 150, 150, 33);

		this.setLayout(null);
		this.repaint();
		this.add(noLoginLabel);
		this.add(confirm);

		notice.getContentPane().add(this);
		notice.setVisible(true);
		notice.setResizable(false);

		confirm.addActionListener(new OKActionListener());
	}

	class OKActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			notice.dispose();
		}

	}
}
