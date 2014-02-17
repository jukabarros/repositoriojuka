package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora extends UnicastRemoteObject implements ICalculadora {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float result;
	
	public Calculadora() throws RemoteException{
		
	}
	
	public float somar(float num1, float num2){
		result = num1 + num2;
		System.out.println("Valor da soma: "+result);
		return result;
	}
	
	public float subtrair(float num1, float num2){
		result = num1 - num2;
		System.out.println("Valor da subtracao: "+result);
		return result;
	}
	
	public float multiplicar(float num1, float num2){
		result = num1 * num2;
		System.out.println("Valor da multiplicacao: "+result);
		return result;
	}
	
	public float dividir(float num1, float num2){
		result = num1 / num2;
		System.out.println("Valor da divisao: "+result);
		return result;
	}
	
}
