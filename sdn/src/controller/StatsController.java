package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import dao.StatsDao;
import entity.Rule;
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
	private PieChartModel pieChart;
	private CartesianChartModel barChart;
	
	private Rule rule;
	private List<Rule> allRules;
	
	private List<String> showUpgrades;
	
	public StatsController() throws SQLException {
		super();
		this.stats = new Stats();
		this.listStats = new ArrayList<Stats>();
		this.dao = new StatsDao();
		this.allSwitches = new ArrayList<String>();
		this.disableChart = true;
		this.allPorts = new ArrayList<String>();
		this.refresh();
		this.createChartBar();
		
		
	}
	
	@PostConstruct
	public void init(){
	   this.createDefaultChartLine();
	   this.createDefaultChartPie();
	}
	
	private void refresh() throws SQLException{
		
		this.listStats = this.dao.findall();
		this.allSwitches = this.dao.allSwitches();
		
		this.rule = new Rule();
		this.allRules = new ArrayList<Rule>();
		this.showUpgrades = new ArrayList<String>();
		
		this.createRules();
		this.applyRules();
	}
	
	public void findBySwitch() throws SQLException{
		this.listStats = this.dao.findBySwitch(getSw());
		this.allPorts = this.dao.getAllPorts(getSw());
		this.disableChart = false;
		this.createChartLine();
		this.createChartPie();
	}
	
	
	private void createRules(){
		this.rule = new Rule();
		/*
		 * Se o consumo na porta for menor que 5%
		 * desliga o link, para os switch de core e aggregation
		 */
		this.rule.setId(1);
		this.rule.setAction("Desligar link");
		this.rule.setParameter(5L);
		List<String> typeOfSwitch = new ArrayList<String>();
		typeOfSwitch.add("aggregation");
		typeOfSwitch.add("core");
		this.rule.setTypeOfSwitch(typeOfSwitch);
		
		this.allRules.add(this.rule);
	}
	
	public void applyRules() throws SQLException{
		Long allTxBytes = 0L;
		Long allTxBytesPorts = 0L;
		for (int i = 0; i < this.allSwitches.size(); i++) {
			allTxBytes = this.dao.sumAllTXBytes(this.allSwitches.get(i));
			double allTxBytesDouble =new Long(allTxBytes).doubleValue();
			for (int j = 1; j < 5; j++) {
				allTxBytesPorts = this.dao.sumAllTXBytesByPort(this.allSwitches.get(i), j);
				double allTxBytesPortsDouble =new Long(allTxBytesPorts).doubleValue();
				
		        double percentage=(allTxBytesPortsDouble*100.00)/allTxBytesDouble;
		        
				if (percentage < this.rule.getParameter().doubleValue()){
					String upgrade = "Desligar a porta: "+j+ " do Switch: "+this.allSwitches.get(i)+ " %"+percentage;
					this.showUpgrades.add(upgrade);
				}
			}
			
		}
	}
	
	/*
	 * Create Charts
	 */
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
	
	@SuppressWarnings("rawtypes")
	private void createChartPie() throws SQLException{
		this.pieChart = new PieChartModel();
		Map<String, Long> sumPorts = this.dao.findSumByPort(getSw());
		Iterator iter = sumPorts.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			Long sum = (Long) mEntry.getValue();
			this.pieChart.set("Port "+mEntry.getKey(), sum);
		}
	}
	
	private void createChartBar() throws SQLException{
		this.barChart = new CartesianChartModel();
		
		ChartSeries txBytes = new ChartSeries(); 
		txBytes.setLabel("TX Bytes");
		
		ChartSeries rxBytes = new ChartSeries(); 
		rxBytes.setLabel("RX Bytes");
		for (int i = 0; i < this.allSwitches.size(); i++) {
			List<Long> sumAllBytes = this.dao.findSumAllBytes(this.allSwitches.get(i));
			int swNum = i +1;
			txBytes.set("SW "+swNum, sumAllBytes.get(0));
			rxBytes.set("SW "+swNum, sumAllBytes.get(1));
			
			sumAllBytes = new ArrayList<>();
			
		}
		this.barChart.addSeries(txBytes);
		this.barChart.addSeries(rxBytes);
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
	
	private void createDefaultChartPie(){
		this.pieChart = new PieChartModel();
		this.pieChart.set("Porta 1", 540);
		this.pieChart.set("Porta 2", 325);
		this.pieChart.set("Porta 3", 702);
		this.pieChart.set("Porta 4", 421);
		
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

	public PieChartModel getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChartModel pieChart) {
		this.pieChart = pieChart;
	}

	public CartesianChartModel getBarChart() {
		return barChart;
	}

	public void setBarChart(CartesianChartModel barChart) {
		this.barChart = barChart;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public List<Rule> getAllRules() {
		return allRules;
	}

	public void setAllRules(List<Rule> allRules) {
		this.allRules = allRules;
	}

	public List<String> getShowUpgrades() {
		return showUpgrades;
	}

	public void setShowUpgrades(List<String> showUpgrades) {
		this.showUpgrades = showUpgrades;
	}

}
