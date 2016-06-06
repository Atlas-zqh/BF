//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

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
		String result = "";

		int posInRuntimeCodes = 0;
		int posInParams = 0;
		ArrayList<Integer> runtimeCodes = new ArrayList<Integer>();
		for (int i = 0; i < 201; i++) {
			runtimeCodes.add(0);
		}

		int numOfLeftBrackets = 0;
		// Calculate how many pairs of brackets there are in the codes.
		for (char co : codes) {
			if (co == '[') {
				numOfLeftBrackets++;
			}
		}

		int[] posOfRightBrackets = new int[numOfLeftBrackets];
		int n = 0;// index of the 'posOfRightBrackets' array
		// Used to note down the positions of right brackets in the codes.
		for (int i = 0; i < codes.length; i++) {
			if (codes[i] == ']') {
				posOfRightBrackets[n] = i;
				n++;
			}
		}

		Integer[] intArrayOfRightBrackets = new Integer[numOfLeftBrackets];
		for (int i = 0; i < numOfLeftBrackets; i++) {
			intArrayOfRightBrackets[i] = new Integer(posOfRightBrackets[i]);
		}

		int flag = 0;
		int[][] posOfBracketPairs = new int[numOfLeftBrackets][2];

		int p = 0;// index of the 'posOfLeftBrackets' array
		// Used to note down the positions of left brackets in the codes.
		while (p < numOfLeftBrackets) {
			for (int j = 0; j < codes.length; j++) {
				if (codes[j] == '[') {
					posOfBracketPairs[p][0] = j;
					p++;
				}
			}
		}

		Integer[] intArrayOfLeftBrackets = new Integer[numOfLeftBrackets];
		for (int i = 0; i < numOfLeftBrackets; i++) {
			intArrayOfLeftBrackets[i] = new Integer(posOfBracketPairs[i][0]);
		}

		int temp = 0;
		for (int q = 0; q < numOfLeftBrackets; q++) {
			for (int k = posOfBracketPairs[q][0]; k < code.length(); k++) {
				if (Arrays.asList(intArrayOfLeftBrackets).contains(k)) {
					flag++;
				}

				if (Arrays.asList(intArrayOfRightBrackets).contains(k)) {
					flag--;
					temp = k;
				}

				if (flag == 0) {
					posOfBracketPairs[q][1] = temp;
					break;
				}
			}

		}
		int indexOfBrackets = 0;
		for (int index = 0; index < codes.length; index++) {

			switch (codes[index]) {
			case '>':
				// Point to the next cell to the right.
				posInRuntimeCodes++;
				break;
			case '<':
				// Point to the next cell to the left.
				if (posInRuntimeCodes < 0) {
					System.out.println("Error! Illegal input!");
					return null;
				} else {
					posInRuntimeCodes--;
					break;
				}
			case ',':
				// Accept one byte of input, storing its value in the byte at
				// the data pointer.

				// runtimeCodes.add(params[posInParams]);
				runtimeCodes.set(posInRuntimeCodes, (int) params[posInParams]);
				posInParams++;
				break;
			case '.':
				// Output the byte at the data pointer.
				// System.out.print(runtimeCodes.get(posInRuntimeCodes));
				result += (char) (Integer.parseInt(runtimeCodes.get(posInRuntimeCodes).toString()));
				break;
			case '+':
				// Increase by one the byte at the data pointer.
				runtimeCodes.set(posInRuntimeCodes, (int) (runtimeCodes.get(posInRuntimeCodes) + 1));
				break;
			case '-':
				// Decrease by one the byte at the data pointer.
				runtimeCodes.set(posInRuntimeCodes, (int) (runtimeCodes.get(posInRuntimeCodes) - 1));
				break;
			case '[':
				// If the byte at the data pointer is zero, then instead of
				// moving the instruction pointer forward to the next command,
				// jump it forward to the command after the matching ] command.

				if (runtimeCodes.get(posInRuntimeCodes) == 0) {
					index = posOfBracketPairs[indexOfBrackets][1];
				} else {
					continue;
				}
				indexOfBrackets++;
				break;
			case ']':
				// If the byte at the data pointer is nonzero, then instead of
				// moving the instruction pointer forward to the next command,
				// jump it back to the command after the matching [ command.
				if (runtimeCodes.get(posInRuntimeCodes) != 0) {
					for (int i = 0; i < posOfBracketPairs.length; i++) {
						if (index == posOfBracketPairs[i][1]) {
							index = posOfBracketPairs[i][0];
						}
					}
				} else {
					continue;
				}
				break;
			case '\0':
				continue;
			default:
				System.out.println("Wrong input!:(");
				break;
			}

		}
		return result;
	}

}
