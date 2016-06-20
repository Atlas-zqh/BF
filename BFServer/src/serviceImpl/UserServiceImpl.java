package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService {
	File userInfo = new File("userInfo");

	@Override
	public boolean login(String username, String password) throws RemoteException, IOException {

		FileReader fr = new FileReader(userInfo);
		BufferedReader br = new BufferedReader(fr);

		String info = username + "_" + password;
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.equals(info)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public boolean signup(String username, String password) throws IOException, RemoteException {

		if (!userInfo.exists()) {
			userInfo.createNewFile();
		}

		File userFile = new File("F:\\Workspace\\BFServer\\" + username);
		boolean k = userFile.mkdir();
		FileWriter fw = new FileWriter(userInfo, true);
		FileReader fr = new FileReader(userInfo);
		BufferedReader br = new BufferedReader(fr);

		String info = username + "_" + password;

		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.equals(info) || line.startsWith(username)) {
				return false;
			}

		}
		fw.write(info + "\n");
		fw.flush();
		fw.close();

		return true;
	}

}
