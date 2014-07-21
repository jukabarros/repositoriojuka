package model;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class TimeWork implements Serializable{

	private static final long serialVersionUID = 344469243470440658L;
	
	private String name;
	private int hoursDay;
	private ArrayList<Date> weekDays;
	private ArrayList<Date> halfTime;
	private Date initDay;
	private Date endDay;
	private String columSeparator;
	private String rowSeparator;
	
	private BufferedReader file;
	
	public TimeWork() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHoursDay() {
		return hoursDay;
	}
	public void setHoursDay(int hoursDay) {
		this.hoursDay = hoursDay;
	}
	public ArrayList<Date> getWeekDays() {
		return weekDays;
	}
	public void setWeekDays(ArrayList<Date> weekDays) {
		this.weekDays = weekDays;
	}
	public ArrayList<Date> getHalfTime() {
		return halfTime;
	}
	public void setHalfTime(ArrayList<Date> halfTime) {
		this.halfTime = halfTime;
	}
	public Date getInitDay() {
		return initDay;
	}
	public void setInitDay(Date initDay) {
		this.initDay = initDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public String getColumSeparator() {
		return columSeparator;
	}
	public void setColumSeparator(String columSeparator) {
		this.columSeparator = columSeparator;
	}
	public String getRowSeparator() {
		return rowSeparator;
	}
	public void setRowSeparator(String rowSeparator) {
		this.rowSeparator = rowSeparator;
	}

	public BufferedReader getFile() {
		return file;
	}

	public void setFile(BufferedReader file) {
		this.file = file;
	}
	

}
