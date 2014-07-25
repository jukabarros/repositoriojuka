package model;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeWork implements Serializable{

	private static final long serialVersionUID = 344469243470440658L;
	
	private String name;
	private int hoursDay;
	
	private GregorianCalendar hoursWorked;
	private Date workDay;
	
	private boolean halfTime;
	
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

	public GregorianCalendar getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(GregorianCalendar hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public Date getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Date workDay) {
		this.workDay = workDay;
	}

	public boolean isHalfTime() {
		return halfTime;
	}

	public void setHalfTime(boolean halfTime) {
		this.halfTime = halfTime;
	}

}
