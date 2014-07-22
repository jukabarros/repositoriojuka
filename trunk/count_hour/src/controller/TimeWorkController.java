package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	public void doUpload(FileUploadEvent fileUploadEvent) throws IOException {
		UploadedFile uploadedFile = fileUploadEvent.getFile();

		String fileNameUploaded = uploadedFile.getFileName();
		String fileType = uploadedFile.getContentType();
		if (fileType.equals("text/plain")){
			String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded +"</b><br/>"+ "Tipo do Arquivo: <b>"+fileType+"</b>"; 
//			FacesContext facesContext = FacesContext.getCurrentInstance(); 
//			facesContext.addMessage(null, new FacesMessage("", infoAboutFile));
			readFile(uploadedFile);
		}else{
			System.out.println("ELSE");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: O arquivo precisa ser .txt", "")); //Mensagem de Erro
		}
	}
	
	/*
	 * Ler e alimentar o data table
	 */
	public void readFile(UploadedFile file) throws IOException {
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
	 * GET AND SET
	 */
	public TimeWork getTimeWork() {
		return timeWork;
	}

	public void setTimeWork(TimeWork timeWork) {
		this.timeWork = timeWork;
	}
	
	

}
