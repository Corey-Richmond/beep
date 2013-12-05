package LoadBalancer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import qps.QPS;
import qps.QPSInterface;

public class LoadBalancer implements Balancer{

	public static int NUMBER_OF_QPS = 5;
	private Registry registry;
	private List<QPSInterface> list;

	public LoadBalancer(){
		// Protects against malicious code
		/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
		list = new ArrayList<QPSInterface>();

		try {
			registry = LocateRegistry.createRegistry(2001);
			Balancer b = this;
			Balancer bStub = (Balancer) UnicastRemoteObject.exportObject(b, 0);
			registry.rebind("LoadBalancer", bStub);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try{
			for(int i = 0; i < NUMBER_OF_QPS; i++){
				//Register QPS object in the naming registry
				String qpsRMIName = "QPS" + i;
				System.out.println("================================================================");
				System.out.println("Binding " + qpsRMIName);
				QPSInterface qpsObject = new QPS();
				QPSInterface qpsStub = (QPSInterface) UnicastRemoteObject.exportObject(qpsObject, 0);
				registry.rebind(qpsRMIName, qpsStub);
				System.out.println(qpsRMIName + " bound");
				list.add(qpsObject);
			}
		} 
		catch (Exception e) {
			System.err.println("\"QPS\" bind exception:");
			e.printStackTrace();
		}
	}
	
	@Override
	public QPSInterface getQPS() throws RemoteException {
		int leastLoaded = 0;
		int min = list.get(0).getLoad();
		for(int i = 1; i < NUMBER_OF_QPS; i++){
			int current = list.get(i).getLoad();
			if(current < min){
				min = current;
				leastLoaded = i;
			}
		}
		QPSInterface qps = null;
		try {
			qps = (QPSInterface) registry.lookup("QPS" + leastLoaded);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qps.incrementLoad();
		return  qps;
	}

	public static void main(String[] args){
		LoadBalancer lb = new LoadBalancer();
		try {
			System.out.println(lb.getQPS().getMovie("batman"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
