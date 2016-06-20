package undo;

import java.util.ArrayList;

public class UndoManager {
	private ArrayList<String> queue = new ArrayList<String>();// ��ŵ�ǰ�����е��ַ���
	private int queuePointer = -1;
	public ArrayList<String> undoQueue = new ArrayList<String>();// ��ű�undo���ַ���
	private int undoQueuePointer = -1;
	// ���µ��ַ������������

	public void addNew(String code) {
		if (code.equals("")) {
			return;
		}
		if (queue.isEmpty()) {
			queue.add(code);
			queuePointer++;
		} else {
			if (code.equals(queue.get(queuePointer))) {
				return;
			} else {
				queue.add(code);
				queuePointer++;
			}
		}

	}

	public String undo() {
		if (queue.isEmpty()) {
			return "";
		}
		if (queuePointer <= 0) {
			return "";
		}
		String undoCode = queue.get(queuePointer - 1);
		undoQueue.add(undoCode);
		undoQueuePointer++;
		queue.remove(queuePointer);
		queuePointer--;

		return undoCode;
	}

	public String redo() {
		if (undoQueue.isEmpty()) {
			return undoQueue.get(queuePointer);
		}

		String redoCode = undoQueue.get(undoQueuePointer);
		queue.add(redoCode);
		queuePointer++;
		undoQueue.remove(undoQueuePointer);
		undoQueuePointer--;

		return redoCode;
	}
}
