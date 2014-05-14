package chat;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Chat implements Serializable{

	private static final long serialVersionUID = 8874494149275732959L;
	
	@XmlElement
	private Long id;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private String login;
	
	@XmlElement
	private Date date;

	
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * GET AND SET
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	

}
