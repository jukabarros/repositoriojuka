package entity;

import java.io.Serializable;

public class IPEntity implements Serializable {
	
	private static final long serialVersionUID = -1641997095602683802L;

	private int id;
	
	private String value;

	public IPEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IPEntity [id=" + id + ", value=" + value + "]";
	}
	
	
}
