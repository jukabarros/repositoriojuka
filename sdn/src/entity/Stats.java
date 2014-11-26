package entity;

import java.io.Serializable;

public class Stats implements Serializable{

	private static final long serialVersionUID = -6975043677445944588L;
	
	private Integer id;
	
	private String sw; // switch
	
	private Integer txBytes;
	
	private Integer rxBytes;
	
	private int portNo;
	
	private int time;
	
	
	public Stats(Integer id, String sw, Integer txBytes, Integer rxBytes,
			int portNo, int time) {
		super();
		this.id = id;
		this.sw = sw;
		this.txBytes = txBytes;
		this.rxBytes = rxBytes;
		this.portNo = portNo;
		this.time = time;
	}

	public Stats() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSw() {
		return sw;
	}

	public void setSw(String sw) {
		this.sw = sw;
	}

	public Integer getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(Integer txBytes) {
		this.txBytes = txBytes;
	}

	public Integer getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(Integer rxBytes) {
		this.rxBytes = rxBytes;
	}

	public int getPortNo() {
		return portNo;
	}

	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Stats [sw=" + sw + ", portNo=" + portNo + ", time=" + time
				+ "]";
	}
	

}
