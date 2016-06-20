package ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class UserTxtFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return false;
		}
		return f.getName().startsWith(LoginFrame.textField.getText()) && f.getName().endsWith(".txt");
	}

	@Override
	public String getDescription() {
		return ".txt";
	}

}
