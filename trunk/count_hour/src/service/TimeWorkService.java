package service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
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
	private Date allHoursWorked;
	
	public TimeWorkService() {
		super();
		timeWork = new TimeWork();
		invalidDateStr = new ArrayList<String>();
		timeWorkList = new ArrayList<TimeWork>();
		allHoursWorked = new Date();
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
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // Usado para concatenar as var long de diff horas e minutos
			
			if (listHours.size()%2 != 0){
				this.invalidDateStr.add(dateStr);
				System.err.println("\n**** Data com Numero Horas Impar: "+dateStr);
			}else{
				int j = 0; // Capturar os indexs de entrada e saida
				long diffTotal = 0;
				long diffTotalHours = 0;
				long diffTotalMinutes = 0;
				for (int i = 0; i < listHours.size(); i++) {
					
					if(j <= i){ // Evitar o erro indexOfBounds
					
						Date in = listHours.get(j);
						Date out = listHours.get(j+1);
						j = j+2; // Pegar a proxima entrada e saida
						long diff = out.getTime() - in.getTime();

						diffTotal += diff; // Incrementando as horas trabalhadas no dia
						
					}
				}
				diffTotalHours = diffTotal/ (60 * 60 * 1000) % 24;
				diffTotalMinutes = diffTotal/ (60 * 1000) % 60;
				
				String hoursWorkedStr = dateStr+" "+diffTotalHours+":"+diffTotalMinutes;
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
	
	/**
	 * Realizando o calculo total das horas
	 * eh calculado separadamente as horas e os minutos
	 * retorna uma string para abstrair na view
	 * @return
	 * @throws ParseException
	 */
	public String sumAllHoursWorked() throws ParseException{
		DateFormat formatterHour = new SimpleDateFormat("HH");
		DateFormat formatterMinute = new SimpleDateFormat("mm");
		int onlyHours = 0;
		int onlyMinutes = 0;
		for (int i = 0; i < this.timeWorkList.size(); i++) {
			onlyHours += Integer.parseInt(formatterHour.format(this.timeWorkList.get(i).getHoursWorked()));
			onlyMinutes += Integer.parseInt(formatterMinute.format(this.timeWorkList.get(i).getHoursWorked()));
			
			if (onlyMinutes >= 60){ //Convertendo Minutos para Horas
				onlyHours++;
				onlyMinutes -=60;
			}
			
		}
		
		String allHoursWorkedStr = onlyHours+"h"+onlyMinutes+"min";
		
		return allHoursWorkedStr;
	}
	
	/**
	 * Metodo responsavel por calcular as horas
	 * que deveria trabalhar de acordo com a carga horaria
	 * diaria. Eh chamado, somente, toda vez que um valor no dataTable
	 * eh editado. Verifica se tem meio expediente e realiza os calculos.
	 * 
	 * @param timeWorkList
	 * @return
	 */
	public double sumAllHourShouldWorked(ArrayList<TimeWork> twList){
		double newHoursDay = 0.0;
		for (int i = 0; i < twList.size(); i++) {
			if (twList.get(i).isHalfTime()){
				newHoursDay += twList.get(i).getHoursDay()/2;
			}else{
				newHoursDay += twList.get(i).getHoursDay();
			}
			
		}
		
		return newHoursDay;
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


	public Date getAllHoursWorked() {
		return allHoursWorked;
	}


	public void setAllHoursWorked(Date allHoursWorked) {
		this.allHoursWorked = allHoursWorked;
	}

}
