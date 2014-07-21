package controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.TimeWork;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="twController")
@RequestScoped
public class TimeWorkController implements Serializable {

	private static final long serialVersionUID = -3799636315200412151L;
	
	private TimeWork timeWork;

	
	
	public TimeWorkController() {
		super();
		// TODO Auto-generated constructor stub
		timeWork = new TimeWork();
	}
	
	/*
	 * Fazer validacao do Arquivo .txt
	 */
	public void doUpload(FileUploadEvent fileUploadEvent) {
		UploadedFile uploadedFile = fileUploadEvent.getFile(); 
		String fileNameUploaded = uploadedFile.getFileName();
		long fileSizeUploaded = uploadedFile.getSize();
		String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded +"</b><br/>"+ "Tamanho do Arquivo: <b>"+fileSizeUploaded+"</b>"; 
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile));
	
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
