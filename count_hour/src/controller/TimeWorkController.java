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
import java.util.Collections;
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
import org.primefaces.model.UploadedFile;


@ManagedBean(name="twController")
@ViewScoped
public class TimeWorkController implements Serializable {

	private static final long serialVersionUID = -3799636315200412151L;
	
	private TimeWork timeWork;
	
	public TimeWorkController() {
		super();
		// TODO Auto-generated constructor stub
		timeWork = new TimeWork();
	}
	
	public void doUpload(FileUploadEvent fileUploadEvent) throws IOException, BiffException {
		UploadedFile uploadedFile = fileUploadEvent.getFile();

		String fileNameUploaded = uploadedFile.getFileName();
		String fileType = uploadedFile.getContentType();
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		String infoAboutFile = null;
		
		// Verificando o tipo de arquivo
		if (fileType.equals("text/plain")){
			
			readTXTFile(uploadedFile);
			
			infoAboutFile = "<br/> Arquivo TXT recebido: <b>" +fileNameUploaded +"</b>"; 
			facesContext.addMessage(null, new FacesMessage("", infoAboutFile));
		}else if(fileType.equals("application/vnd.ms-excel")){
			
			readXLSFile(uploadedFile);
			
			infoAboutFile = "<br/> Arquivo XLS recebido: <b>" +fileNameUploaded +"</b>"; 
		    facesContext = FacesContext.getCurrentInstance(); 
			facesContext.addMessage(null, new FacesMessage("", infoAboutFile));

		}
		else{
			facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: O arquivo precisa ser .txt ou .xls", "")); //Mensagem de Erro
		}
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
			System.err.println("Erro readFile Exception: "+ex.getMessage());
		}
		
	}
	
	/*
	 * Ler o arquivo XLS
	 */
	public void readXLSFile(UploadedFile file) throws BiffException, IOException{
		Workbook wb = Workbook.getWorkbook(file.getInputstream());
		Sheet sheet = wb.getSheet(0);
		int line = sheet.getRows();
		
		ArrayList<Date> allHoursByDate = new ArrayList<Date>();
		
		int nextCell = 0;
		for(int i = 0; i < line; i++  ){

			Cell cell1 = sheet.getCell(0, i); // Siape

			Cell cell2 = sheet.getCell(1, i); // Data

			Cell cell3 = sheet.getCell(2, i); // Dia da semana

			Cell cell4 = sheet.getCell(3, i); // horas

			nextCell = i+1;
			if (nextCell < line){

				String dateCellStr = cell2.getContents(); 
				String hourStr = cell4.getContents();
				String nextDateCell = sheet.getCell(1,nextCell).getContents(); // Capturando a data da proxima cedula

				String dateHour = dateCellStr +" "+ hourStr; // Concatenando Data + Hora para o formato dd/MM/yyyy HH:mm
				
				Date hour = stringToHourDate(dateHour);
				if (dateCellStr.equals(nextDateCell)){
					allHoursByDate.add(hour);
				}else{
					System.out.println("--------- Horas do Dia: "+dateCellStr+ "------------");
					allHoursByDate.add(hour); // Adicionando a ultima hora referente a data
					
					hoursCalculatorByDay(allHoursByDate); // Calculando hora por dia
					allHoursByDate = new ArrayList<Date>(); // Iniciando um novo calculo
				}

			}

		}

		wb.close();
	}
	
	public Date stringToHourDate(String dateStr){
		try{

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date = (Date)formatter.parse(dateStr);
			return date;
			
		}catch(ParseException pe){
			System.err.println("*** Erro no parser String to Date: "+pe.getMessage());
			return null;
		}
	}
	
	/*
	 * Diferenciar Entrada e saida
	 */
	public void hoursCalculatorByDay(ArrayList<Date> listHours){
		try{
			Collections.sort(listHours); // Ordenando a lista
			// Fazer o calculo das horas, add no data table
			System.out.println("\nLista de Horas Ordenadas: "+listHours);
			
		}catch (Exception e) {
			System.err.println("*** Erro no calculo das horas: "+e.getMessage());
			// TODO: handle exception
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
	
	

}
