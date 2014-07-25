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

import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean(name="twController")
@ViewScoped
public class TimeWorkController implements Serializable {

	private static final long serialVersionUID = -3799636315200412151L;
	
	private TimeWork timeWork;
	private ArrayList<String> invalidDateStr;
	private ArrayList<String> allValidDateStr;
	
	public TimeWorkController() {
		super();
		// TODO Auto-generated constructor stub
		timeWork = new TimeWork();
		invalidDateStr = new ArrayList<String>();
		allValidDateStr = new ArrayList<String>();
		
	}
	
	public void doUpload(FileUploadEvent fileUploadEvent) throws IOException, BiffException {
		UploadedFile uploadedFile = fileUploadEvent.getFile();

		String fileNameUploaded = uploadedFile.getFileName();
		String fileType = uploadedFile.getContentType();
		
		
		String infoAboutFile = null;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// Verificando o tipo de arquivo
		if (fileType.equals("text/plain")){
			
			readTXTFile(uploadedFile);
			
			infoAboutFile = "<br/> Arquivo TXT recebido: <b>" +fileNameUploaded +"</b>"; 
			facesContext.addMessage(null, new FacesMessage("", infoAboutFile));
		}else if(fileType.equals("application/vnd.ms-excel")){
			
			
			infoAboutFile = "<br/> Arquivo XLS recebido: <b>" +fileNameUploaded +"</b>"; 
		    facesContext = FacesContext.getCurrentInstance(); 
			facesContext.addMessage(null, new FacesMessage("", infoAboutFile));

			readXLSFile(uploadedFile);
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
		int line = sheet.getRows(); //Capturando as linhas da planilha
		
		ArrayList<Date> allHoursByDate = new ArrayList<Date>();
		int nextCell = 0;
		for(int i = 0; i < line; i++  ){

			Cell cell1 = sheet.getCell(0, i); // Siape

			Cell cell2 = sheet.getCell(1, i); // Data

			Cell cell3 = sheet.getCell(2, i); // Dia da semana

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
					
					// Add na lista de dias somente, para fazer o calculo total.
					hoursCalculatorByDay(allHoursByDate, dateCellStr); // Calculando hora por dia

					allHoursByDate = new ArrayList<Date>(); // Iniciando um novo calculo
				}
				

			}

		}
		// Listando as datas que nao foram validadas
		checkInvalidDate();
		invalidDateStr = new ArrayList<String>();
		
		// Fechando a planilha
		wb.close();
	}
	
	/*
	 * Validar o campo Data e Hora da planilha
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
	
	
	/*
	 * Diferenciar Entrada e saida
	 * Considerando que a primeira hora do dia seja a hora de entrada
	 * e a segunda de saida e assim por diante.
	 * Caso o numero de horas do dias seja impar, ficara impossivel de fazer
	 * o calulo, o metodo retornara nulo.
	 */
	public void hoursCalculatorByDay(ArrayList<Date> listHours, String dateStr){
		try{
			Collections.sort(listHours); // Ordenando a lista
			
			// Fazer o calculo das horas, add no data table
			
			if (listHours.size()%2 != 0){
				this.invalidDateStr.add(dateStr);
				System.err.println("\n**** Data com Numero Horas Impar: "+dateStr);
			}else{
				
				// Add na lista de Datas Validas
				this.allValidDateStr.add(dateStr);
				
				int j = 0; // Capturar os indexs de entrada e saida
				System.out.println("\n----- Horas Trabalhadas da Data: "+dateStr);
				long diffTotal = 0;
				long diffTotalHours = 0;
				long diffTotalMinutes = 0;
				for (int i = 0; i < listHours.size(); i++) {
					
					if(j <= i){ // Evitar o erro indexOfBounds
					
						Date in = listHours.get(j);
						Date out = listHours.get(j+1);
						j = j+2; // Pegar a proxima entrada
						long diff = out.getTime() - in.getTime();
						long diffHours = diff / (60 * 60 * 1000) % 24;
						long diffMinute = diff / (60 * 1000) % 60;

						diffTotal += diff; // Incrementando as horas trabalhadas no dia
						System.out.println("\nDiferenca em Horas: "+diffHours+ " em minutos: "+diffMinute);
						
						// Fazer o calcula da diferenca das horas
					}
				}
				diffTotalHours = diffTotal/ (60 * 60 * 1000) % 24;
				diffTotalMinutes = diffTotal/ (60 * 1000) % 60;
				// Converter pra Date ou Gregoriam ou deixar em Long????
				System.out.println("Total: "+diffTotalHours+":"+diffTotalMinutes);
				
				
			}
			
		}catch (Exception e) {
			System.err.println("*** Erro no calculo das horas: "+e.getMessage());
			// TODO: handle exception
		}
	}
	
	/*
	 * Metodo responsavel por checar e listar as datas invalidas,
	 * Pode ser por formato invalido, numero de horas impar
	 */
	public void checkInvalidDate(){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(!this.invalidDateStr.isEmpty()){
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data(s) Inválida(s) devido ao Número ímpar de horas: ", "")); //Mensagem de Erro
			for (int i = 0; i < this.invalidDateStr.size(); i++) {
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, this.invalidDateStr.get(i), ""));
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
	
	

}
