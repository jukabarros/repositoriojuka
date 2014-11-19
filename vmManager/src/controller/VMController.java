package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
	private String outputCommand;
	
	public VMController() {
		this.refresh();
	}

	public void refresh(){
		this.vmEntity = new VMEntity();
		this.ipEntity = new IPEntity();
		this.showIpForm = false;
		this.outputCommand = null;
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
			System.out.println("QUANTIDADE DE CPU: "+this.vmEntity.getNumOfCore());
			System.out.println("MEMÓRIA: "+this.vmEntity.getMemory());
			System.out.println("QUANTIDADE DE PLACAS DE REDE: "+this.vmEntity.getNumOfNetwork());
			System.out.println("**      IP(S)");

			for (int i = 0; i < this.vmEntity.getIps().size(); i++) {
				System.out.println("("+this.getVmEntity().getIps().get(i).getId()+"): "+this.getVmEntity().getIps().get(i).getValue());
			}
			
			// Criando a Nova VM a partir da VM Template
			System.out.println("\n**** Criando Nova VM a partir da VM Template (Ubuntu1404)");
			String command1 = "VBoxManage clonevm Ubuntu1404 --name "+this.vmEntity.getName()+" --register";
			this.outputCommand = this.executeCommand(command1);
			System.out.println(this.outputCommand);
			
			// Setando as configuracoes
			System.out.println("\n**** Inserindo as configurações (1/2)");
			
			String command2 = "VBoxManage modifyvm "+this.vmEntity.getName()+" --memory "+this.vmEntity.getMemory()+""
					+ " --cpus "+this.vmEntity.getNumOfCore();
			this.executeCommand(command2);
			System.out.println("* OK");
			
			System.out.println("\n**** Inserindo as configurações (2/2)");
			for (int i = 1; i <= this.vmEntity.getNumOfNetwork(); i++) {
				String nic = "VBoxManage modifyvm "+this.vmEntity.getName()+" --nic"+i+" null";
				this.executeCommand(nic);
			}
			System.out.println("* OK");
			
			// Iniciando a VM
			System.out.println("\n**** Iniciando a nova VM");
			String command3 = "VBoxManage startvm "+this.vmEntity.getName();
	 
			String output = this.executeCommand(command3);
	 
			System.out.println(output);
			return "createVM";
			
		}else{
			System.out.println("Campo QUANTIDADE DE PLACAS DE REDE esta vazio");
			this.vmEntity.setNumOfNetwork(0);
			FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo Quantidade de Placas de Rede está vazio", "")); //Mensagem de erro 
            return null;
		}
	}
	
	private String executeCommand(String command) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
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
