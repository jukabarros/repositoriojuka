package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote{
	
	float somar(float num1, float num2) throws RemoteException;
	float subtrair(float num1, float num2) throws RemoteException;
	float multiplicar(float num1, float num2) throws RemoteException;
	float dividir(float num1, float num2) throws RemoteException;

}
