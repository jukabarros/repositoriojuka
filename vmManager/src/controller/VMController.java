package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import entity.IPEntity;
import entity.VMEntity;

@ManagedBean(name="vmController")
@ViewScoped
public class VMController implements Serializable {
	
	private static final long serialVersionUID = -313339546613128566L;

	private VMEntity vmEntity;
	
	private IPEntity ipEntity;
	
	private boolean showIpForm;
	
	
	public VMController() {
		this.refresh();
	}

	public void refresh(){
		this.vmEntity = new VMEntity();
		this.ipEntity = new IPEntity();
		this.showIpForm = false;
		
	}
	
	public void enableIpForm(){
		List<IPEntity> ips = new ArrayList<>();
		if(!this.vmEntity.getNumOfNetwork().equals(0)){
			
			// Adicionando os IPS na lista
			for (int i = 1; i < this.vmEntity.getNumOfNetwork()+1; i++) {
				this.ipEntity = new IPEntity();
				this.ipEntity.setId(i);
				ips.add(this.ipEntity);

				// Limpando o objeto
				this.ipEntity = null;

			}
			this.vmEntity.setIps(ips);

			// Limpando o objeto
			ips = null;
			this.setShowIpForm(true);
		}else{
			System.out.println("Campo QUANTIDADE DE PLACAS DE REDE esta vazio");
			this.vmEntity.setNumOfNetwork(0);
			FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Quantidade de Placas de Rede está vazio", "")); //Mensagem de erro 
		}
	}
	
	public String create(){
		if(!this.vmEntity.getNumOfNetwork().equals(0)){

			System.out.println("\n****             VM SETTINGS");
			System.out.println("NOME: "+this.vmEntity.getName());
			System.out.println("QUANTIDADE DE CORES: "+this.vmEntity.getNumOfCore());
			System.out.println("MEMÓRIA: "+this.vmEntity.getMemory());
			System.out.println("QUANTIDADE DE PLACAS DE REDE: "+this.vmEntity.getNumOfNetwork());
			System.out.println("**      IP(S)");

			for (int i = 0; i < this.vmEntity.getIps().size(); i++) {
				System.out.println("("+this.getVmEntity().getIps().get(i).getId()+"): "+this.getVmEntity().getIps().get(i).getValue());
			}
			return "createVM";
			
		}else{
			System.out.println("Campo QUANTIDADE DE PLACAS DE REDE esta vazio");
			this.vmEntity.setNumOfNetwork(0);
			FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Quantidade de Placas de Rede está vazio", "")); //Mensagem de erro 
            return null;
		}
	}
	
	/*
	 * Get and Set
	 */
	public VMEntity getVmEntity() {
		return vmEntity;
	}

	public void setVmEntity(VMEntity vmEntity) {
		this.vmEntity = vmEntity;
	}

	public IPEntity getIpEntity() {
		return ipEntity;
	}

	public void setIpEntity(IPEntity ipEntity) {
		this.ipEntity = ipEntity;
	}

	public boolean isShowIpForm() {
		return showIpForm;
	}

	public void setShowIpForm(boolean showIpForm) {
		this.showIpForm = showIpForm;
	}

}
