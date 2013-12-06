package LoadBalancer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoadBalanced extends Remote{

	/**
	 * 
	 * @return the number of requests being processed
	 */
	public int getLoad() throws RemoteException;
	
	public void incrementLoad() throws RemoteException;
	
}
