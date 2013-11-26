package LoadBalancer;

import java.rmi.Remote;
import java.rmi.RemoteException;

import qps.QPSInterface;

public interface Balancer extends Remote{
	
	public QPSInterface getQPS() throws RemoteException;
}
