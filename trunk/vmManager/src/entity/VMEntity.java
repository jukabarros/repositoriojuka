package entity;

import java.io.Serializable;
import java.util.List;

public class VMEntity implements Serializable {
	
	private static final long serialVersionUID = -3935866977829536416L;

	private String name;
	
	private Integer numOfCore;
	
	private Integer memory;
	
	private Integer numOfNetwork;
	
	private List<IPEntity> ips;

	
	public VMEntity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumOfCore() {
		return numOfCore;
	}

	public void setNumOfCore(Integer numOfCore) {
		this.numOfCore = numOfCore;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Integer getNumOfNetwork() {
		return numOfNetwork;
	}

	public void setNumOfNetwork(Integer numOfNetwork) {
		this.numOfNetwork = numOfNetwork;
	}

	public List<IPEntity> getIps() {
		return ips;
	}

	public void setIps(List<IPEntity> ips) {
		this.ips = ips;
	}

	@Override
	public String toString() {
		return "VMEntity [name=" + name + ", numOfCore=" + numOfCore
				+ ", memory=" + memory + ", numOfNetwork=" + numOfNetwork
				+ ", ips=" + ips + "]";
	}
	
	
	

}
