package undo;

import java.util.ArrayList;

public class UndoManager {
	private ArrayList<String> queue=new ArrayList<String>();//存放当前队列中的字符串
	private int queuePointer=-1;
	private ArrayList<String> undoQueue=new ArrayList<String>();//存放被undo的字符串
	private int undoQueuePointer=-1;
	
	//将新的字符串加入队列中

	public void addNew(String code){
		if(queue.isEmpty()){
			queue.add(code);
			queuePointer++;
//			for(int i=0;i<queue.size();i++){
//				System.out.print(queue.get(i)+";");
//			}
//			System.out.println();
		}else{
			if(code.equals(queue.get(queuePointer))){
//				for(int i=0;i<queue.size();i++){
//					System.out.print(queue.get(i)+";");
//				}
//				System.out.println();
				return;
			}else{
				queue.add(code);
				queuePointer++;
//				for(int i=0;i<queue.size();i++){
//					System.out.print(queue.get(i)+";");
//				}
//				System.out.println();
			}
//		undoQueue.clear();
		//Undo队列将会被清空，因为添加新的操作后无法回到原来被撤销的操作

	}
		}
	
	public String undo(){
		if(queue.isEmpty()){
			return "Illegal";
		}
		
		String undoCode=queue.get(queuePointer);
		undoQueue.add(undoCode);

		undoQueuePointer++;
		queue.remove(queuePointer);
//		System.out.println("****************");
//		for(int i=0;i<queue.size();i++){
//			System.out.print(queue.get(i)+";");
//		}
//		System.out.println("****************");
		queuePointer--;
		
		return undoCode;
	}
	
	public String redo(){
		if(undoQueue.isEmpty()){
			return "Illegal";
		}
		
		String redoCode=undoQueue.get(undoQueuePointer);
		queue.add(redoCode);
		queuePointer++;
		undoQueue.remove(undoQueuePointer);
		undoQueuePointer--;
		
		return redoCode;
	}
}
