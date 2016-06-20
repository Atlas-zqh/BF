package undo;

import java.util.ArrayList;

public class UndoManager {
	private ArrayList<String> queue=new ArrayList<String>();//存放当前队列中的字符串
	private int queuePointer=-1;
	public  ArrayList<String> undoQueue=new ArrayList<String>();//存放被undo的字符串
	private int undoQueuePointer=-1;
	//将新的字符串加入队列中

	public void addNew(String code){
		if(code.equals("")){
			return;
		}
		if(queue.isEmpty()){
			queue.add(code);
			queuePointer++;
////			for(int i=0;i<queue.size();i++){
////				System.out.print(queue.get(i)+";");
////			}
//			System.out.println(code);
		}else{
			//System.out.println(code);
			if(code.equals(queue.get(queuePointer))){
//				for(int i=0;i<queue.size();i++){
//					System.out.print(queue.get(i)+";");
//				}
//				System.out.println();
				
				return;
			}else{
				queue.add(code);
				System.out.println(code);
				queuePointer++;
//				for(int i=0;i<queue.size();i++){
//					System.out.print(queue.get(i)+";");
//				}
//				System.out.println();
			}
		}
//		undoQueue.clear();
		//Undo队列将会被清空，因为添加新的操作后无法回到原来被撤销的操作

	}
		
	
	public String undo(){
		if(queue.isEmpty()){
			return "";
		}
		if(queuePointer<=0){
			return "";
		}
		String undoCode=queue.get(queuePointer-1);
		undoQueue.add(undoCode);
System.out.println(undoCode);
for(int i =0;i<queue.size();i++){
	System.out.println(queue.get(i));
}
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
			return undoQueue.get(queuePointer);
		}
		
		String redoCode=undoQueue.get(undoQueuePointer);
		queue.add(redoCode);
		queuePointer++;
		undoQueue.remove(undoQueuePointer);
		undoQueuePointer--;
		
		return redoCode;
	}
}
