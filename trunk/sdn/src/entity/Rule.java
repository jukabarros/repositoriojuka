package entity;

import java.io.Serializable;
import java.util.List;

public class Rule implements Serializable{

	private static final long serialVersionUID = -3872700245781392565L;
	
	private Integer id;
	
	private String action;
	
	private Long parameter; // Parametro calculado
	
	private List<String> typeOfSwitch; // Tipos de switch que se aplica a regra

	
	public Rule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getParameter() {
		return parameter;
	}

	public void setParameter(Long parameter) {
		this.parameter = parameter;
	}

	public List<String> getTypeOfSwitch() {
		return typeOfSwitch;
	}

	public void setTypeOfSwitch(List<String> typeOfSwitch) {
		this.typeOfSwitch = typeOfSwitch;
	}

	@Override
	public String toString() {
		return "Rule [action=" + action + ", parameter=" + parameter
				+ ", typeOfSwitch=" + typeOfSwitch + "]";
	}
	
	
	

}
