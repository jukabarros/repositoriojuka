package chat;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class MsgChat implements Serializable{

	private static final long serialVersionUID = 8874494149275732959L;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private String login;
	
	@XmlElement
	private Date date;
	
	@XmlElement
	private String dateString;
	
	public MsgChat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * GET AND SET
	 */

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
