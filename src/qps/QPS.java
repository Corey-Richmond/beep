package qps;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//Registry registry = LocateRegistry.getRegistry();
//System.out.println("Obtaining original remote object value.");
//RemoteDatabase remoteObject = (RemoteDatabase) registry.lookup("Database");

public class QPS {
	
	public static void main(){
	
		// Protects against malicious code
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
		try{
			// Get handle to registry
	        Registry registry = LocateRegistry.getRegistry();
	        
	        System.out.println("================================================================");
	        System.out.println("Binding \"Department\"...");
	        
            //Register Department object in the naming registry
            String qpsRMIName = "QPS";
            QPSInterface qpsObject = (QPSInterface)new QPS();
            QPSInterface qpsStub = (QPSInterface) UnicastRemoteObject.exportObject(qpsObject, 0);
            registry.rebind(qpsRMIName, qpsStub);
        	
        	
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
