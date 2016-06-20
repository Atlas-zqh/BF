package undo;

import java.util.ArrayList;

public class UndoManager {
	private ArrayList<String> queue=new ArrayList<String>();//��ŵ�ǰ�����е��ַ���
	private int queuePointer=-1;
	private ArrayList<String> undoQueue=new ArrayList<String>();//��ű�undo���ַ���
	private int undoQueuePointer=-1;
	
	//���µ��ַ������������

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
		//Undo���н��ᱻ��գ���Ϊ����µĲ������޷��ص�ԭ���������Ĳ���

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
