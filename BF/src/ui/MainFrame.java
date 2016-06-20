package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import rmi.RemoteHelper;
import undo.PushTask;
import undo.UndoManager;

public class MainFrame extends JFrame {
	public static UndoManager undomanager = new UndoManager();;
	PushTask pt = new PushTask();
	Timer timer = new Timer();

	public static JTextArea textArea;
	private JTextArea resultArea;
	private JTextArea paramsArea;
	private final int Height = 630;
	private final int Width = 800;
	JFrame frame = new JFrame("BF Client");
	public ImageIcon icon = new ImageIcon("icon/brainicon-small.png");

	private Font font = new Font("TimesNewRoman", Font.PLAIN, 16);
	private Font menuFont = new Font("TimesNewRoman", Font.PLAIN, 14);

	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");

	JMenuItem newMenuItem = new JMenuItem("New(N)", KeyEvent.VK_N);
	JMenuItem openMenuItem = new JMenuItem("Open(O)", KeyEvent.VK_O);
	JMenuItem saveMenuItem = new JMenuItem("Save(S)", KeyEvent.VK_S);
	JMenuItem runMenuItem = new JMenuItem("Run(R)", KeyEvent.VK_R);
	JMenuItem exitMenuItem = new JMenuItem("Exit(X)", KeyEvent.VK_X);

	JMenu runMenu = new JMenu("Edit");
	JMenuItem undoMenuItem = new JMenuItem("Undo");
	JMenuItem redoMenuItem = new JMenuItem("Redo");

	static JMenu versionMenu = new JMenu("Version");

	JMenu themeMenu = new JMenu("Theme");
	JMenuItem oldman = new JMenuItem("Old Man");
	JMenuItem defaultTheme = new JMenuItem("Default(Grey)");
	JMenuItem angelababy = new JMenuItem("Angelababy");
	JMenuItem green = new JMenuItem("Default(Green)");
	JMenuItem blue = new JMenuItem("Default(Blue)");

	static JMenu account = new JMenu("Account");
	static JMenuItem login = new JMenuItem("Login");
	static JMenuItem logout = new JMenuItem("Log out");

	public MainFrame() {
		timer.schedule(pt, 500, 500);

		frame.setSize(Width, Height);
		frame.setIconImage(icon.getImage());
		fileMenu.setFont(menuFont);
		runMenu.setFont(menuFont);
		versionMenu.setFont(menuFont);
		account.setFont(menuFont);
		themeMenu.setFont(menuFont);

		MyJPanel panel1 = new MyJPanel();
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
		fileMenu.add(exitMenuItem);

		menuBar.add(runMenu);
		runMenu.add(undoMenuItem);
		runMenu.add(redoMenuItem);

		menuBar.add(versionMenu);

		// 添加快捷键
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newMenuItem.setDisplayedMnemonicIndex(4);
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openMenuItem.setDisplayedMnemonicIndex(5);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenuItem.setDisplayedMnemonicIndex(5);
		runMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		runMenuItem.setDisplayedMnemonicIndex(4);
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		exitMenuItem.setDisplayedMnemonicIndex(5);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new OpenActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		// saveMenuItem.addActionListener(new MenuItemActionListener());
		runMenuItem.addActionListener(new MenuItemActionListener());
		exitMenuItem.addActionListener(new ExitActionListener());
		undoMenuItem.addActionListener(new UndoActionListener());
		redoMenuItem.addActionListener(new RedoActionListener());
		login.addActionListener(new LoginActionListener());
		logout.addActionListener(new LogoutActionListener());

		// Code Area
		textArea = new JTextArea(16, 58);
		textArea.setText("");
		textArea.setOpaque(false);
		textArea.setMargin(new Insets(10, 10, 10, 10));
		// textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setFont(font);
		textArea.setBackground(null);
		textArea.setForeground(Color.BLACK);
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setOpaque(false);
		scroller.getViewport().setOpaque(false);
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

		// Theme settings
		menuBar.add(themeMenu);

		themeMenu.add(defaultTheme);
		defaultTheme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel1.returnToDefault();
				textArea.setForeground(Color.BLACK);
			}
		});

		themeMenu.add(blue);
		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel1.changeTheme("background/default-blue.jpg");
				textArea.setForeground(Color.BLACK);
			}
		});

		themeMenu.add(green);
		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel1.changeTheme("background/default-green.jpg");
				textArea.setForeground(Color.BLACK);
			}
		});

		themeMenu.add(oldman);
		oldman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel1.changeTheme("background/oldman.jpg");
				textArea.setForeground(Color.DARK_GRAY);
			}
		});

		themeMenu.add(angelababy);
		angelababy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel1.changeTheme("background/angelababy.jpg");
				textArea.setForeground(Color.WHITE);
			}

		});

		menuBar.add(account);
		frame.setJMenuBar(menuBar);

		account.add(login);
		account.add(logout);
		logout.setVisible(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(400, 200);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if (cmd.equals("Run(R)")) {
				try {
					resultArea.setText(RemoteHelper.getInstance().getExecuteService().execute(textArea.getText(),
							paramsArea.getText() + "\n"));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			} else if (cmd.equals("New(N)")) {
				textArea.setText("");
				resultArea.setText("");
				paramsArea.setText("");
			}
		}
	}

	class SaveActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea.getText();
			try {
				if (LoginFrame.logined) {
					JFileChooser jfc = new JFileChooser();
					jfc.setCurrentDirectory(new File("F:\\Workspace\\BFServer\\" + LoginFrame.textField.getText()));
					jfc.setFileFilter(new UserTxtFileFilter());
					jfc.showSaveDialog(null);
					RemoteHelper.getInstance().getIOService().writeFile(code, LoginFrame.textField.getText(),
							jfc.getSelectedFile().getName());
					resultArea.setText("Saved!");
				} else {
					NotLoginFrame nlf = new NotLoginFrame();
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}

	class LoginActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame loginFrame = new LoginFrame();
		}
	}

	class LogoutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				LoginFrame.logined = !RemoteHelper.getInstance().getUserService()
						.logout(LoginFrame.textField.getText());
			} catch (RemoteException re) {
				re.printStackTrace();
			}
			account.setText("Account");
			login.setVisible(true);
			logout.setVisible(false);
		}
	}

	class ExitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exitMenuItem) {
				System.exit(0);
			}
		}
	}

	class OpenActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String content;
			ArrayList<JMenuItem> versions = new ArrayList<JMenuItem>();

			try {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(new UserTxtFileFilter());
				jfc.setCurrentDirectory(new File("F:\\Workspace\\BFServer\\" + LoginFrame.textField.getText()));
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();

				String[] file = f.getName().split("_");
				String userId = file[0];
				String fileName = file[1].substring(0, file[1].length() - 4);// 除去.txt
				content = RemoteHelper.getInstance().getIOService().readFile(userId, fileName);
				String[] versionInfo = RemoteHelper.getInstance().getIOService().readFileList(userId, fileName);
				String[] versionContent = RemoteHelper.getInstance().getIOService().getVersionContent(userId, fileName);
				versionMenu.removeAll();
				for (int i = 0; i < versionInfo.length; i++) {
					versions.add(new JMenuItem(versionInfo[i]));
				}
				for (int j = 0; j < versionInfo.length; j++) {
					versionMenu.add(versions.get(j));
				}

				for (int k = 0; k < versionInfo.length; k++) {
					String tempContent = versionContent[k];
					versions.get(k).addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							textArea.setText(tempContent);
						}
					});
				}

				textArea.setText(content);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}

	class UndoActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			textArea.setText(undomanager.undo());
		}

	}

	class RedoActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!undomanager.undoQueue.isEmpty()) {
				textArea.setText(undomanager.redo());
			}
		}
	}
}
