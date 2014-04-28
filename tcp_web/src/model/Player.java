package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Long id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String login;
	
	@XmlElement
	private String email;
	
	@XmlElement
	private String password;
	
	@XmlElement
	private String sex;
	
	@XmlElement
	private int num_true;
	
	@XmlElement
	private int num_false;
	//private int reputation;
	
	@XmlElement
	private int num_victory_4;
	
	@XmlElement
	private int num_lost;
	
	@XmlElement
	private int score;
	
	@XmlElement
	private int online;
	
	@XmlElement
	private int num_victory_2;
	
	@XmlElement
	private int num_second;
	
	@XmlElement
	private int num_third;
	
	
	public Player() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getNum_true() {
		return num_true;
	}
	public void setNum_true(int num_true) {
		this.num_true = num_true;
	}
	public int getNum_false() {
		return num_false;
	}
	public void setNum_false(int num_false) {
		this.num_false = num_false;
	}
	public int getNum_victory_4() {
		return num_victory_4;
	}
	public void setNum_victory_4(int num_victory_4) {
		this.num_victory_4 = num_victory_4;
	}
	public int getNum_lost() {
		return num_lost;
	}
	public void setNum_lost(int num_lost) {
		this.num_lost = num_lost;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getNum_victory_2() {
		return num_victory_2;
	}
	public void setNum_victory_2(int num_victory_2) {
		this.num_victory_2 = num_victory_2;
	}
	public int getNum_second() {
		return num_second;
	}
	public void setNum_second(int num_second) {
		this.num_second = num_second;
	}
	public int getNum_third() {
		return num_third;
	}
	public void setNum_third(int num_third) {
		this.num_third = num_third;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
