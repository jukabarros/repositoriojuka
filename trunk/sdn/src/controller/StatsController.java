package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.StatsDao;
import entity.Stats;

@ManagedBean(name="statsController")
@ViewScoped
public class StatsController implements Serializable{

	private static final long serialVersionUID = -6703944335657971629L;
	
	private Stats stats;
	private List<Stats> listStats;
	
	private String sw; // Switch para consulta
	private List<String> allSwitches; // Agrupar todos os switches
	private List<String> allPorts; // Pega todas as portas de 1 switch
	private StatsDao dao;
	
	private boolean disableChart;
	
	private CartesianChartModel lineChart;
	
	public StatsController() throws SQLException {
		super();
		this.stats = new Stats();
		this.listStats = new ArrayList<Stats>();
		this.dao = new StatsDao();
		this.allSwitches = new ArrayList<String>();
		this.disableChart = true;
		this.allPorts = new ArrayList<String>();
		this.refresh();
	}
	
	@PostConstruct
	public void init(){
	   this.createDefaultChartLine();
	}
	
	private void refresh() throws SQLException{
		this.listStats = this.dao.findall();
		this.allSwitches = this.dao.allSwitches();
	}
	
	public void findBySwitch() throws SQLException{
		this.listStats = this.dao.findBySwitch(getSw());
		this.allPorts = this.dao.getAllPorts(getSw());
		this.disableChart = false;
		this.createChartLine();
	}

	private void createChartLine() throws SQLException{
		
		this.lineChart = new CartesianChartModel();
		for (int i = 1; i <= this.allPorts.size(); i++) {
			ChartSeries port = new ChartSeries(); 
			port.setLabel("Port "+i);
			List<Stats> listStatsByPort = this.dao.findByPortNo(i, getSw());
			for (int j = 0; j < listStatsByPort.size(); j++) {
				Stats st = new Stats();
				st = listStatsByPort.get(j);
				port.set(st.getTime(), st.getRxBytes());
				
			}
			this.lineChart.addSeries(port);
			
			// Limpando os objetos
			listStatsByPort = new ArrayList<Stats>();
			port = new ChartSeries();
			
		}
	
	}
	
	private void createDefaultChartLine(){
		this.lineChart = new CartesianChartModel();
        ChartSeries port1 = new ChartSeries();  
        port1.setLabel("Port 1");  
        port1.set("5", 120);  
        port1.set("10", 100);  
        port1.set("15", 44);  
        port1.set("20", 150);  
        port1.set("25", 25);  
  
        ChartSeries port2 = new ChartSeries();  
        port2.setLabel("Port 2");  
  
        port2.set("5", 52);  
        port2.set("10", 60);  
        port2.set("15", 110);
        port2.set("20", 135);  
        port2.set("25", 120);  
  
        this.lineChart.addSeries(port1);  
        this.lineChart.addSeries(port2);
		
	}
	
	/*
	 * Get And Set
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
	public String getSw() {
		return sw;
	}
	public void setSw(String sw) {
		this.sw = sw;
	}

	public List<String> getAllSwitches() {
		return allSwitches;
	}

	public void setAllSwitches(List<String> allSwitches) {
		this.allSwitches = allSwitches;
	}

	public boolean isDisableChart() {
		return disableChart;
	}

	public void setDisableChart(boolean disableChart) {
		this.disableChart = disableChart;
	}

	public CartesianChartModel getLineChart() {
		return lineChart;
	}

	public void setLineChart(CartesianChartModel lineChart) {
		this.lineChart = lineChart;
	}

}
