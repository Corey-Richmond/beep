/* Author: Felix Rodriguez
 * University: University of Illinois at Chicago
 * Class: CS 441, Distributed Object Programming Using Middleware
 * Date: Fall 2013
 * Professor: Mark Grechanik
 * Group: 1
 */

/**
 * 
 */
package TestSuite;

import static org.junit.Assert.*;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import qps.QPSInterface;

import LoadBalancer.Balancer;

/**
 * @author Felix
 *
 */
public class LoadBalancerTest {

	static Registry registry;
	
	@BeforeClass
	public static void setup() throws RemoteException{
		registry = LocateRegistry.getRegistry(2001);
	}
	
	@Test
	public void getQPSTest() throws Exception{
		Balancer loadBalancer = (Balancer) registry.lookup("LoadBalancer");
		QPSInterface q = loadBalancer.getQPS();
		int actualLoad = q.getLoad();
		assertTrue("Load was not increased accordingly. Actual Load: " + actualLoad, q.getLoad() > 0);
	}

}
