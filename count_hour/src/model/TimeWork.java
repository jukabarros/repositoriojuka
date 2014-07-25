package model;

import java.io.Serializable;
import java.util.Date;

public class TimeWork implements Serializable{

	private static final long serialVersionUID = 344469243470440658L;
	
	private String name;
	private int hoursDay;
	
	private Date hoursWorked;
	private String workDayDateStr;
	
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

	public Date getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(Date hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getWorkDayDateStr() {
		return workDayDateStr;
	}

	public void setWorkDayDateStr(String workDayDateStr) {
		this.workDayDateStr = workDayDateStr;
	}

	public boolean isHalfTime() {
		return halfTime;
	}

	public void setHalfTime(boolean halfTime) {
		this.halfTime = halfTime;
	}

}
