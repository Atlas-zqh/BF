package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
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
	private final int Height=630;
	private final int Width=800;
	
	private Font font=new Font("TimesNewRoman",Font.PLAIN,16);

	public MainFrame() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		frame.setSize(Width, Height);
		
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		panel1.setBorder(BorderFactory.createTitledBorder("Code"));
		panel2.setBorder(BorderFactory.createTitledBorder("Parameter"));
		panel3.setBorder(BorderFactory.createTitledBorder("Result"));
		
		frame.getContentPane().add(BorderLayout.NORTH,panel1);
		frame.getContentPane().add(BorderLayout.WEST,panel2);
		frame.getContentPane().add(BorderLayout.EAST,panel3);
		frame.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5,5,5,5));
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		
		JMenu runMenu=new JMenu("Run");
		menuBar.add(runMenu);
		
		JMenuItem executeMenuItem=new JMenuItem("Execute");
		runMenu.add(executeMenuItem);
		//Unfinished
		//Haven't added ActionListners
		
		JMenu versionMenu=new JMenu("Version");
		menuBar.add(versionMenu);
		//Unfinished
		//Haven't added ActionListners
		//To be solved: how to add a menu-item when a new version is created
		
		JMenu account=new JMenu("Login");
		menuBar.add(account);
		frame.setJMenuBar(menuBar);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		saveMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		

		



//		textArea = new JTextArea(10,25);
//		textArea.setMargin(new Insets(10, 10, 10, 10));//文本组件的边框和它的文本之间的空白
//		textArea.setBackground(Color.LIGHT_GRAY);
//		frame.add(textArea, BorderLayout.CENTER);
		textArea = new JTextArea(16,58);
		textArea.setMargin(new Insets(10,10,10,10));
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setFont(font);
		JScrollPane scroller=new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel1.add(scroller);

		JTextArea numbersArea=new JTextArea(6,30);
		numbersArea.setBackground(Color.WHITE);
		numbersArea.setLineWrap(true);
		numbersArea.setMargin(new Insets(10,10,10,10));
		numbersArea.setFont(font);
		panel2.add(new JScrollPane(numbersArea));
		// 显示结果
		resultArea = new JTextArea(6,24);
		resultArea.setText("Result");
		resultArea.setBackground(Color.WHITE);
		resultArea.setMargin(new Insets(10,10,10,10));
		resultArea.setFont(font);
		panel3.add(new JScrollPane(resultArea));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				textArea.setText("Open");
			} else if (cmd.equals("Save")) {
				textArea.setText("Save");
			} else if (cmd.equals("Run")) {
				resultArea.setText("Hello, result");
			}
		}
	}

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
}
