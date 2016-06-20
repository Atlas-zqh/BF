package undo;

import java.util.TimerTask;

import ui.MainFrame;

public class PushTask extends TimerTask{
	String currentCode="";
	
	@Override
	public void run() {
		if(MainFrame.textArea.getText()!=""){
			
		
		currentCode=MainFrame.textArea.getText();}
		else{
			currentCode="";
		}
		// TODO Auto-generated method stub
		MainFrame.undomanager.addNew(currentCode);
//		System.out.println(currentCode);
	}

}
