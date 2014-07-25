package service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import model.TimeWork;

public class TimeWorkService implements Serializable{
	
	
	private static final long serialVersionUID = -6177612749660790189L;
	private TimeWork timeWork;
	private ArrayList<TimeWork> timeWorkList;
	private ArrayList<String> invalidDateStr;
	
	public TimeWorkService() {
		super();
		timeWork = new TimeWork();
		invalidDateStr = new ArrayList<String>();
		timeWorkList = new ArrayList<TimeWork>();
	}


	/**
	 * Diferenciar Entrada e saida
	 * Considerando que a primeira hora do dia seja a hora de entrada
	 * e a segunda de saida e assim por diante.
	 * Caso o numero de horas do dias seja impar, ficara impossivel de fazer
	 * o calulo, o metodo retornara nulo.
	 * 
	 * 
	 * @param listHours
	 * @param dateStr
	 */
	public void hoursCalculatorByDay(ArrayList<Date> listHours, String dateStr){
		try{
			Collections.sort(listHours); // Ordenando a lista
			DateFormat formatter = new SimpleDateFormat("HH:mm"); // Usado para concatenar as var long de diff horas e minutos
			
			if (listHours.size()%2 != 0){
				this.invalidDateStr.add(dateStr);
				System.err.println("\n**** Data com Numero Horas Impar: "+dateStr);
			}else{
				int j = 0; // Capturar os indexs de entrada e saida
				System.out.println("\n----- Horas Trabalhadas da Data: "+dateStr);
				long diffTotal = 0;
				long diffTotalHours = 0;
				long diffTotalMinutes = 0;
				for (int i = 0; i < listHours.size(); i++) {
					
					if(j <= i){ // Evitar o erro indexOfBounds
					
						Date in = listHours.get(j);
						Date out = listHours.get(j+1);
						j = j+2; // Pegar a proxima entrada e saida
						long diff = out.getTime() - in.getTime();
						
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffMinute = diff / (60 * 1000) % 60;

						diffTotal += diff; // Incrementando as horas trabalhadas no dia
						System.out.println("\nDiferenca em Horas: "+diffHours+ " em minutos: "+diffMinute);
						
					}
				}
				diffTotalHours = diffTotal/ (60 * 60 * 1000) % 24;
				diffTotalMinutes = diffTotal/ (60 * 1000) % 60;
				System.out.println("Total: "+diffTotalHours+":"+diffTotalMinutes);
				
				String hoursWorkedStr = diffTotalHours+":"+diffTotalMinutes;
				Date hoursWorked = (Date)formatter.parse(hoursWorkedStr);
				
				this.timeWork.setWorkDayDateStr(dateStr);
				this.timeWork.setHoursWorked(hoursWorked);
				this.timeWork.setHalfTime(false); // So sera setado pelo usuario no proprio datatable
				this.timeWorkList.add(this.timeWork);
				
				this.timeWork = new TimeWork();
				
			}
			
		}catch (Exception e) {
			System.err.println("*** Erro no calculo das horas: "+e.getMessage());
			// TODO: handle exception
		}
	}

	
	/*
	 * GET AND SET
	 */
	public TimeWork getTimeWork() {
		return timeWork;
	}


	public void setTimeWork(TimeWork timeWork) {
		this.timeWork = timeWork;
	}


	public ArrayList<TimeWork> getTimeWorkList() {
		return timeWorkList;
	}


	public void setTimeWorkList(ArrayList<TimeWork> timeWorkList) {
		this.timeWorkList = timeWorkList;
	}


	public ArrayList<String> getInvalidDateStr() {
		return invalidDateStr;
	}


	public void setInvalidDateStr(ArrayList<String> invalidDateStr) {
		this.invalidDateStr = invalidDateStr;
	}

}
