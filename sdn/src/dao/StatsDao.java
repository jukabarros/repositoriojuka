package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import config.ConnectDB;
import entity.Stats;


public class StatsDao implements Serializable{

	private static final long serialVersionUID = 8122799074816795699L;
	
	private Stats stats;
	
	private List<Stats> listStats;
	
	private String query;
	
	public StatsDao() {
		this.stats = new Stats();
		this.listStats = new ArrayList<Stats>();
	}
	
	public List<Stats> findall() throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT * FROM stats";
		System.out.println("query: "+this.query);
		PreparedStatement queryExec = conectar.prepareStatement(query);
		ResultSet result = queryExec.executeQuery();
		while (result.next()){
			this.stats = new Stats();
			this.stats.setId(result.getInt(1));
			this.stats.setSw(result.getString(2));
			this.stats.setTxBytes(result.getInt(3));
			this.stats.setRxBytes(result.getInt(4));
			this.stats.setPortNo(result.getInt(5));
			this.stats.setTime(result.getInt(6));
			
			this.listStats.add(this.stats);
			
		}
		// Limpando o objeto
		this.stats = new Stats();
		conectar.close();
		return this.listStats;
		
	}
	
	public List<Stats> findBySwitch(String sw) throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT * FROM stats WHERE switch = ?";
		System.out.println("query: "+this.query);
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, sw);
		ResultSet result = queryExec.executeQuery();
		this.listStats = new ArrayList<Stats>();
		while (result.next()){
			this.stats = new Stats();
			this.stats.setId(result.getInt(1));
			this.stats.setSw(result.getString(2));
			this.stats.setTxBytes(result.getInt(3));
			this.stats.setRxBytes(result.getInt(4));
			this.stats.setPortNo(result.getInt(5));
			this.stats.setTime(result.getInt(6));
			
			this.listStats.add(this.stats);
			
		}
		// Limpando o objeto
		this.stats = new Stats();
		conectar.close();
		return this.listStats;
		
	}
	
	public List<Stats> findByPortNo(int portNo, String sw) throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT * FROM stats WHERE port_no = ? AND switch = ?";
		System.out.println("query: "+this.query);
		
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setInt(1, portNo);
		queryExec.setString(2, sw);
		ResultSet result = queryExec.executeQuery();
		this.listStats = new ArrayList<Stats>();
		while (result.next()){
			this.stats = new Stats();
			this.stats.setId(result.getInt(1));
			this.stats.setSw(result.getString(2));
			this.stats.setTxBytes(result.getInt(3));
			this.stats.setRxBytes(result.getInt(4));
			this.stats.setPortNo(result.getInt(5));
			this.stats.setTime(result.getInt(6));
			
			this.listStats.add(this.stats);
			
		}
		// Limpando o objeto
		this.stats = new Stats();
		conectar.close();
		return this.listStats;
		
	}
	public Long sumAllTXBytes(String sw) throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT SUM(tx_bytes) FROM stats WHERE switch = ? GROUP BY port_no ORDER BY port_no DESC";
		System.out.println("query: "+this.query);
		
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, sw);
		ResultSet result = queryExec.executeQuery();
		Long sumAll = 0L;
		while (result.next()){
			sumAll += result.getLong(1);
			
		}
		conectar.close();
		return sumAll;
		
	}
	
	public Long sumAllTXBytesByPort(String sw, int port_no) throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT SUM(tx_bytes) FROM stats WHERE switch = ? AND port_no = ?";
		System.out.println("query: "+this.query);
		
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, sw);
		queryExec.setInt(2, port_no);
		ResultSet result = queryExec.executeQuery();
		Long sumAll = 0L;
		while (result.next()){
			sumAll += result.getLong(1);
			
		}
		conectar.close();
		return sumAll;
		
	}
	
	/*
	 * Para o gráfico de barra
	 */
	public List<Long> findSumAllBytes(String sw) throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT SUM(tx_bytes), SUM(rx_bytes) FROM stats WHERE switch = ?";
		
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, sw);
		ResultSet result = queryExec.executeQuery();
		List<Long> sumAllBySwitch = new ArrayList<Long>();
		while (result.next()){
			sumAllBySwitch.add(result.getLong(1));
			sumAllBySwitch.add(result.getLong(2));
			
		}
		conectar.close();
		
		return sumAllBySwitch;
	}
	
	/*
	 * Para o gráfico de pizza
	 */
	public Map<String, Long> findSumByPort(String sw) throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT port_no, SUM(tx_bytes) FROM stats WHERE switch = ? GROUP BY port_no ORDER BY port_no DESC";
		System.out.println("query: "+this.query);
		
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, sw);
		ResultSet result = queryExec.executeQuery();
		Map<String, Long> sumPorts = new HashMap<>(); 
		while (result.next()){
			sumPorts.put(result.getString(1), result.getLong(2));
			
		}
		Map<String, Long> treeMap = new TreeMap<String, Long>(sumPorts);
		conectar.close();
		return treeMap;
		
	}
	
	public List<String> getAllPorts(String sw) throws SQLException{
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT port_no FROM stats WHERE switch = ? GROUP BY port_no";
		System.out.println("query: "+this.query);
		PreparedStatement queryExec = conectar.prepareStatement(query);
		queryExec.setString(1, sw);
		ResultSet result = queryExec.executeQuery();
		
		List<String> allPort = new ArrayList<String>();
		while (result.next()){
			String port = result.getString(1);
			
			allPort.add(port);
			
		}
		// Limpando o objeto
		conectar.close();
		return allPort;
		
	}
		
	public List<String> allSwitches() throws SQLException {
		Connection conectar = new ConnectDB().connect();
		this.query = "SELECT switch FROM stats GROUP BY switch";
		System.out.println("query: "+this.query);
		PreparedStatement queryExec = conectar.prepareStatement(query);
		ResultSet result = queryExec.executeQuery();
		
		List<String> allSw = new ArrayList<String>();
		while (result.next()){
			String sw = result.getString(1);
			
			allSw.add(sw);
			
		}
		// Limpando o objeto
		conectar.close();
		return allSw;
		
	}
	
	/*
	 * Get and Set
	 */
	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public List<Stats> getListStats() {
		return listStats;
	}

	public void setListStats(List<Stats> listStats) {
		this.listStats = listStats;
	}
	
}
