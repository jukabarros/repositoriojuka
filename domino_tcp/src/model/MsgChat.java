package model;

import java.io.Serializable;
import java.util.Date;

public class MsgChat implements Serializable{

	private static final long serialVersionUID = 8874494149275732959L;
	
	private String msg;
	
	private String login;
	
	private Date date;

	private String dateString;
	
	public MsgChat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	

}
