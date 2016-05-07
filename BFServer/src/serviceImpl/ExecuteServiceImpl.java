//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.ExecuteService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		char[] codes = code.toCharArray();
		char[] params = param.toCharArray();

		int posInRuntimeCodes = 0;
		int posInParams = 0;
		ArrayList<Character> runtimeCodes = new ArrayList<Character>();

		int numOfLeftBrackets=0;
		//Calculate how many pairs of brackets are there in the codes.
		for(char co:codes){
			if(co=='['){
				numOfLeftBrackets++;
			}
		}
		
		int[] posOfLeftBrackets=new int[numOfLeftBrackets];
		int m=0;//index of the 'posOfLeftBrackets' array
		//Used to note down the positions of left brackets in the codes.
		for(int j=0;j<codes.length;j++){
			if(codes[j]=='['){
				posOfLeftBrackets[m]=j;
				m++;
			}
		}
		
		int[] posOfRightBrackets=new int[numOfLeftBrackets];
		int n=0;//index of the 'posOfRightBrackets' array
		//Used to note down the positions of right brackets in the codes.
		for(int i=0;i<codes.length;i++){
			if(codes[i]=='['){
				posOfRightBrackets[n]=i;
				n++;
			}
		}
		
		for (char c : codes) {
			switch (c) {
			case '>':
				// Point to the next cell to the right.
				posInRuntimeCodes++;
				break;
			case '<':
				// Point to the next cell to the left.
				if (posInRuntimeCodes <= 0) {
					System.out.println("Error! Illegal input!");
					return null;
				} else {
					posInRuntimeCodes--;
					break;
				}
			case ',':
				// Accept one byte of input, storing its value in the byte at
				// the data pointer.
				runtimeCodes.add(params[posInParams]);
				break;
			case '.':
				// Output the byte at the data pointer.
				System.out.print(runtimeCodes.get(posInRuntimeCodes));
				break;
			case '+':
				// Increase by one the byte at the data pointer.
				runtimeCodes.set(posInRuntimeCodes, (char) (runtimeCodes.get(posInRuntimeCodes).charValue() + 1));
				break;
			case '-':
				// Decrease by one the byte at the data pointer.
				runtimeCodes.set(posInRuntimeCodes, (char) (runtimeCodes.get(posInRuntimeCodes).charValue() - 1));
				break;
			case '[':
				// If the byte at the data pointer is zero, then instead of
				// moving the instruction pointer forward to the next command,
				// jump it forward to the command after the matching ] command.
				if(runtimeCodes.get(posInRuntimeCodes).equals('0')){
					//Unsolved
				}else{
					continue;	
				}
				
				break;
			case ']':
				// If the byte at the data pointer is nonzero, then instead of
				// moving the instruction pointer forward to the next command,
				// jump it back to the command after the matching [ command.
				if(!runtimeCodes.get(posInRuntimeCodes).equals('0')){
					//Unsolved
				}else{
					continue;
				}
				break;
			}
		}

		return null;
	}

}
