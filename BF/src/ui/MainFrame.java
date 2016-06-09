package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import rmi.RemoteHelper;

public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JTextArea resultArea;
	private JTextArea paramsArea;
	private final int Height = 630;
	private final int Width = 800;
	JFrame frame = new JFrame("BF Client");
	public ImageIcon icon=new ImageIcon("icon/brainicon-small.png");
	
	private Font font = new Font("TimesNewRoman", Font.PLAIN, 16);
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem newMenuItem = new JMenuItem("New");
	JMenuItem openMenuItem = new JMenuItem("Open");
	JMenuItem saveMenuItem = new JMenuItem("Save");
	JMenuItem runMenuItem = new JMenuItem("Run");

	JMenu runMenu = new JMenu("Run");
	JMenuItem executeMenuItem = new JMenuItem("Execute");
	
	JMenu versionMenu = new JMenu("Version");
	
	static JMenu account = new JMenu("Account");
	JMenuItem login=new JMenuItem("Login");
	
	public MainFrame() {

		frame.setSize(Width, Height);
		frame.setIconImage(icon.getImage());
		

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel1.setBorder(BorderFactory.createTitledBorder("Code"));
		panel2.setBorder(BorderFactory.createTitledBorder("Parameter"));
		panel3.setBorder(BorderFactory.createTitledBorder("Result"));

		frame.getContentPane().add(BorderLayout.NORTH, panel1);
		frame.getContentPane().add(BorderLayout.WEST, panel2);
		frame.getContentPane().add(BorderLayout.EAST, panel3);
		frame.setResizable(false);

		menuBar.setMargin(new Insets(5, 5, 5, 5));
		menuBar.add(fileMenu);
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(runMenuItem);

		menuBar.add(runMenu);
		runMenu.add(executeMenuItem);

		menuBar.add(versionMenu);
		// Unfinished
		// Haven't added ActionListners
		// To be solved: how to add a menu-item when a new version is created

		menuBar.add(account);
		frame.setJMenuBar(menuBar);
		
		account.add(login);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
//		saveMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		executeMenuItem.addActionListener(new MenuItemActionListener());
		login.addActionListener(new LoginActionListener());
		
		// Code Area
		textArea = new JTextArea(16, 58);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setFont(font);
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel1.add(scroller);

		// Parameter Area
		paramsArea = new JTextArea(4, 30);
		paramsArea.setBackground(Color.WHITE);
		paramsArea.setLineWrap(true);
		paramsArea.setMargin(new Insets(10, 10, 10, 10));
		paramsArea.setFont(font);
		panel2.add(new JScrollPane(paramsArea));

		// Result Area
		resultArea = new JTextArea(4, 24);
		resultArea.setText("Result");
		resultArea.setBackground(Color.WHITE);
		resultArea.setMargin(new Insets(10, 10, 10, 10));
		resultArea.setFont(font);
		panel3.add(new JScrollPane(resultArea));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			// Open file
			// Unfinished
			if (cmd.equals("Open")) {
				String content;
				try {
					JFileChooser jfc=new JFileChooser();
					jfc.showOpenDialog(null);
					File f=jfc.getSelectedFile();
					
					String[] fileName=f.getName().split("_");
					
					content = RemoteHelper.getInstance().getIOService().readFile(fileName[0], fileName[1]);
					textArea.setText(content);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// Get params in the paramArea and run them along with the code.
				// Show result in the resultArea
			} else if (cmd.equals("Run") || cmd.equals("Execute")) {
				try {
					resultArea.setText(RemoteHelper.getInstance().getExecuteService().execute(textArea.getText(),
							paramsArea.getText()+"\n"));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(cmd.equals("New")){
				textArea.setText("");
			}
		}
	}

	class SaveActionListener implements ActionListener {
		// Save code into a file
		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
				resultArea.setText("Saved!");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}

	class LoginActionListener implements ActionListener {
		// Login Service
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame loginFrame=new LoginFrame();

		}
	}
}
