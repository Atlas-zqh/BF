package undo;

import java.util.TimerTask;

import ui.MainFrame;

public class PushTask extends TimerTask {
	String currentCode = "";

	@Override
	public void run() {
		if (MainFrame.textArea.getText() != "") {

			currentCode = MainFrame.textArea.getText();
		} else {
			currentCode = "";
		}
		MainFrame.undomanager.addNew(currentCode);
	}

}
