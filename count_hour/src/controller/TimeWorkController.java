package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import model.TimeWork;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import service.TimeWorkService;


@ManagedBean(name="twController")
@ViewScoped
public class TimeWorkController implements Serializable {

	private static final long serialVersionUID = -3799636315200412151L;
	
	private TimeWork timeWork;
	private ArrayList<TimeWork> timeWorkList;
	private String nameForm;
	private String hoursDayForm;
	private TimeWorkService twService;
	private double hoursShouldWorked; //Horas Diarias * numero de dias
	private int totalDays; // Total de dias validados
	private String allHoursWorked; // Melhor para visualizacao
	
	public TimeWorkController() {
		super();
		// TODO Auto-generated constructor stub
		timeWork = new TimeWork();
		timeWorkList = new ArrayList<TimeWork>();
		nameForm = null;
		hoursDayForm = null;
		twService = new TimeWorkService();
		hoursShouldWorked = 0.0;
		totalDays = 0;
		allHoursWorked = null;
		
	}
	
	/**
	 * Metodo responsavel por capturar o arquivo da view
	 * pode ser TXT ou XLS
	 * @param fileUploadEvent
	 * @throws IOException
	 * @throws BiffException
	 */
	public void doUpload(FileUploadEvent fileUploadEvent) throws IOException, BiffException {
		UploadedFile uploadedFile = fileUploadEvent.getFile();
		
		String fileNameUploaded = uploadedFile.getFileName();
		String fileType = uploadedFile.getContentType();
		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Verificando o tipo de arquivo
		if (fileType.equals("text/plain")){
			
			facesContext = FacesContext.getCurrentInstance(); 
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo TXT Recebido: "+fileNameUploaded, ""));
			
			readTXTFile(uploadedFile);
		}else if(fileType.equals("application/vnd.ms-excel")){
			
		    facesContext = FacesContext.getCurrentInstance(); 
		    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo XLS Recebido: "+fileNameUploaded, ""));

			readXLSFile(uploadedFile);
		}else{
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: O arquivo precisa ser .txt ou .xls", "")); //Mensagem de Erro
		}
	}
	
	/**
	 * Metodos de evento do primefaces
	 * @param event
	 */
	public void onRowEdit(RowEditEvent event) {
		/*
		 * Quando editar, refazer os calculos
		 * pegar a data
		 */
		System.out.println("onRowEdit na Data: "+this.timeWork.getWorkDayDateStr());
        FacesMessage msg = new FacesMessage("Valor editado", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
    	System.out.println("onRowCancel");
        FacesMessage msg = new FacesMessage("Cancelado", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	/*
	 * Ler o arquivo TXT
	 */
	public void readTXTFile(UploadedFile file) throws IOException {
		System.out.println("** Lendo Arquivo TXT");
		InputStream is = file.getInputstream();
		BufferedReader bfReader = null;
		try{
			is = new ByteArrayInputStream(file.getContents());
			bfReader = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while((temp = bfReader.readLine()) != null){
				System.out.println(temp);
			}
			
		}catch(Exception ex){
			System.err.println("*** Erro readFile Exception: "+ex.getMessage());
		}
		
	}
	
	/**
	 * Ler o arquivo XLS
	 * @param file
	 * @throws BiffException
	 * @throws IOException
	 */
	public void readXLSFile(UploadedFile file) throws BiffException, IOException{
		Workbook wb = Workbook.getWorkbook(file.getInputstream());
		Sheet sheet = wb.getSheet(0);
		int line = sheet.getRows(); //Capturando as linhas da planilha

		ArrayList<Date> allHoursByDate = new ArrayList<Date>();
		int nextCell = 0;
		for(int i = 0; i < line; i++  ){

//			Cell cell1 = sheet.getCell(0, i); // Siape
//			Cell cell3 = sheet.getCell(2, i); // Dia da semana
			Cell cell2 = sheet.getCell(1, i); // Data
			Cell cell4 = sheet.getCell(3, i); // horas

			nextCell = i+1;
			if (nextCell < line){ // Fazendo as comparacoes das datas

				String dateCellStr = cell2.getContents(); 
				String hourStr = cell4.getContents();
				String nextDateCell = sheet.getCell(1,nextCell).getContents(); // Capturando a data da proxima cedula

				String dateHour = dateCellStr +" "+ hourStr; // Concatenando Data + Hora para o formato dd/MM/yyyy HH:mm
				
				// linha +1 pois a contagem comeca do zero
				Date hour = stringToHourDate(dateHour, cell2.getRow()+1);
				
				// Add as horas por cada dia, o ultimo registro do dia eh add no else e ja eh realizada o calculo das horas
				if (dateCellStr.equals(nextDateCell)){
					
					allHoursByDate.add(hour);
				}else{
					
					allHoursByDate.add(hour); // Adicionando a ultima hora referente a data
					twService.hoursCalculatorByDay(allHoursByDate, dateCellStr); //Calculando as horas por dia
					allHoursByDate = new ArrayList<Date>(); // Iniciando um novo calculo
				}

			}
		}
		// Listando as datas que nao foram validadas
		listAllInvalidDate();
		twService.setInvalidDateStr(new ArrayList<String>()); // Limpando a lista de Datas Invalidas
		// Fechando a planilha
		wb.close();
	}
	
	/**
	 * Validar o campo Data e Hora da planilha
	 * DateLine -> Server identificar onde ocorreu o erro na planilha
	 * 
	 * @param dateStr
	 * @param dateLine
	 * @return
	 */
	public Date stringToHourDate(String dateStr, int dateLine){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try{
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date = (Date)formatter.parse(dateStr);
			return date;
			
		}catch(ParseException pe){
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi possível converter a Data/Hora da linha: "+dateLine , ""));
			System.err.println("*** Erro no parser String to Date: "+pe.getMessage());
			return null;
		}
	}

	/**
	 * Alimentando a lista para criar o datatable
	 * retorna vazio para tirar o erro na view
	 * @return
	 */
	public String createDataTable(){
		FacesContext fContext = FacesContext.getCurrentInstance();
		this.timeWorkList = twService.getTimeWorkList();
		
		try{
			int hoursDayInt = Integer.parseInt(getHoursDayForm());
			
			if (hoursDayInt <= 24 && hoursDayInt > 0){
				for (int i = 0; i < this.timeWorkList.size(); i++) {
					this.timeWorkList.get(i).setName(getNameForm());
					this.timeWorkList.get(i).setHoursDay(hoursDayInt);
					
				}
				
				this.totalDays = this.timeWorkList.size();
				this.hoursShouldWorked = hoursDayInt*this.totalDays;
				this.allHoursWorked = twService.sumAllHoursWorked();
				
			}else{
				System.err.println("\n*** Erro: Campo horas diarias tem que ser entre 1 e 24");
				fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: O campo Horas Diárias tem que ser entre 1 e 24", ""));
				
			}
			
		}catch(Exception pe){
			fContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no campo Horas Diárias", ""));
			System.err.println("*** Erro: nao foi possivel converter o valor para inteiro "+getHoursDayForm());
		}
		return null;
	}
	
	/**
	 * Metodo responsavel por checar e listar as datas invalidas,
	 * aquelas que possuem o numero de horas impar
	 */
	public void listAllInvalidDate(){
		FacesContext fc = FacesContext.getCurrentInstance();
		ArrayList<String> listInvalidDates = twService.getInvalidDateStr(); // Capturando todas as datas invalidas da camada de servico
		if(!listInvalidDates.isEmpty()){
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data(s) Inválida(s) devido ao Número ímpar de horas: ", "")); //Mensagem de Erro
			for (int i = 0; i < listInvalidDates.size(); i++) {
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, listInvalidDates.get(i), ""));
			}
			
		}else{
			fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "\nTodas as datas foram validadas", "")); //Mensagem de Erro
			
		}
	}
	
	
	/*
	 * GET AND SET
	 */
	public TimeWork getTimeWork() {
		return timeWork;
	}

	public void setTimeWork(TimeWork timeWork) {
		this.timeWork = timeWork;
	}

	public ArrayList<TimeWork> getTimeWorkList() {
		return timeWorkList;
	}

	public void setTimeWorkList(ArrayList<TimeWork> timeWorkList) {
		this.timeWorkList = timeWorkList;
	}

	public String getNameForm() {
		return nameForm;
	}

	public void setNameForm(String nameForm) {
		this.nameForm = nameForm;
	}

	public String getHoursDayForm() {
		return hoursDayForm;
	}

	public void setHoursDayForm(String hoursDayForm) {
		this.hoursDayForm = hoursDayForm;
	}

	public double getHoursShouldWorked() {
		return hoursShouldWorked;
	}

	public void setHoursShouldWorked(double hoursShouldWorked) {
		this.hoursShouldWorked = hoursShouldWorked;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getAllHoursWorked() {
		return allHoursWorked;
	}

	public void setAllHoursWorked(String allHoursWorked) {
		this.allHoursWorked = allHoursWorked;
	}



}
