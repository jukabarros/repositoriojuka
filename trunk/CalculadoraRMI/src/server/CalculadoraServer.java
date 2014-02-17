package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.Calculadora;

public class CalculadoraServer {
	
	public static void main(String[] args){
		try{
			Calculadora calc = new Calculadora();
			Registry r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			String objname = "calculadoraserver";
			System.out.println("Registering "+objname+ "...");
			Naming.rebind(objname, calc);
			System.out.println("Registered");
		}catch(Exception e){
			System.err.println("Error in main()! "+e);
			e.printStackTrace();
			System.exit(2);
		}
		System.out.println("Object waiting...");
	}

}
