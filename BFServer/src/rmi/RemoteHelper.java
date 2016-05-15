package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RemoteHelper {
	public RemoteHelper() {
		initServer();
	}

	public void initServer() {
		DataRemoteObject dataRemoteObject;
		try {
			dataRemoteObject = new DataRemoteObject();
			LocateRegistry.createRegistry(8888);// 用于创建一个接受对特定端口调用的远程对象注册表
			Naming.bind("rmi://localhost:8888/DataRemoteObject", dataRemoteObject);
			// 向RMI远程对象注册表注册至少一个远程对象。一个远程对象拥有的方法可生成指向其他远程对象的句柄。
			// 这样一来，客户只需到注册表里访问一次，得到第一个远程对象即可
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}

	}
}
