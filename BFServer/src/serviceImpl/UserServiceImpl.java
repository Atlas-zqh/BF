package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl  implements UserService{
	File userInfo=new File("userInfo.txt");
	
	@Override
	public boolean login(String username, String password) throws RemoteException,IOException {
		FileReader fr=new FileReader(userInfo);
		BufferedReader br=new BufferedReader(fr);
		
		String info=username+"_"+password;
		String line=null;
		while((line=br.readLine())!=null){
			if(line.equals(info)){
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
	public boolean signup(String username,String password) throws IOException,RemoteException{
		FileWriter fw=new FileWriter(userInfo);
		FileReader fr=new FileReader(userInfo);
		BufferedReader br=new BufferedReader(fr);
		
		String info=username+"_"+password;
		fw.write(info+"\n");
		fw.flush();
		fw.close();

		String line=null;
		while((line=br.readLine())!=null){
			if(line.equals(info)){
				return true;
			}
		}
		return false;
	}
	
}
