package ui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	private ImageIcon theme = new ImageIcon("background/default.jpg");

	public void paintComponent(Graphics g) {
		g.drawImage(theme.getImage(), 0, 0, null);
	}

	public void changeTheme(String pic) {
		theme = new ImageIcon(pic);
		repaint();
	}

	public void returnToDefault() {
		theme = new ImageIcon("background/default.jpg");
		repaint();
	}
}
