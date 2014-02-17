package client;

import java.rmi.Naming;
import java.rmi.RemoteException;


public class ClienteCalculadora {
	
	public static void main(String[] args) {
		ICalculadora rcalculadora = null;
		try{
			String objname = "rmi://localhost/calculadoraserver";
			System.out.println("Looking for object "+objname);
			rcalculadora = (ICalculadora) Naming.lookup(objname);
			
		}catch(Exception e){
			System.err.println("Lookup problems! "+ e);
			System.exit(2);
		}
		try{
			float num1 = 10;
			float num2 = 8;
			float resp;
			System.out.println("NUM1: "+num1);
			System.out.println("NUM2: "+num2);
			resp = rcalculadora.somar(num1, num2);
			System.out.println("** VALOR DA SOMA: "+resp);
			
			resp = rcalculadora.subtrair(num1, num2);
			System.out.println("** VALOR DA SUBTRACAO: "+resp);
			
			resp = rcalculadora.multiplicar(num1, num2);
			System.out.println("** VALOR DA MULTIPLICACAO: "+resp);
			
			resp = rcalculadora.dividir(num1, num2);
			System.out.println("** VALOR DA DIVISAO: "+resp);
		}catch (RemoteException re){
			System.err.println("Remote call problems! "+re);
			re.printStackTrace();
			System.exit(3);
		}
	}


}
