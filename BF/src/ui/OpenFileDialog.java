package ui;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class OpenFileDialog extends JPanel{

	private static final long serialVersionUID = -6246615709600348866L;
	public ImageIcon icon=new ImageIcon("icon/brainicon-small.png");

	public OpenFileDialog(){
		JFileChooser jf=new JFileChooser();
		jf.setDialogTitle("Chose");

		 jf.setVisible(true);
//		JFrame openFrame=new JFrame("Open");
//		openFrame.setSize(550, 470);
//		openFrame.setLocation(450, 400);
//		openFrame.setIconImage(icon.getImage());
//		
//		openFrame.getContentPane().add(this);
//		openFrame.setVisible(true);
//		openFrame.setResizable(false);
	}
}
