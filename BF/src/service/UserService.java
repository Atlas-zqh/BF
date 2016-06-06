package service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote{
	public boolean login(String username, String password) throws RemoteException,IOException;

	public boolean logout(String username) throws RemoteException;
	
	public boolean signup(String username,String password) throws RemoteException,IOException;
}
