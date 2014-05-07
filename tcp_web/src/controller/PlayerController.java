package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import service.PlayerService;
import util.EncryptMD5;

import model.Player;

@ManagedBean(name="playerController")
@ViewScoped
public class PlayerController implements Serializable {

	private static final long serialVersionUID = 67164389915317270L;
	
	private Player player;
	private ArrayList<Player> playerList;
	private PlayerService playerService;
	
	
	public PlayerController() {
		refresh();
		playerList = playerService.listPlayerOnline();
		 
	}
	
	public void refresh(){
		player = new Player();
		playerList = new ArrayList<Player>();
		playerService = new PlayerService();
		
	}
	
	public String listPlayerByLogin(){
		playerList = playerService.listPlayerByLoginService(getPlayer().getLogin());
		
		return null;
	}
	
	public String create(){
		System.out.println("Creating player:"+getPlayer().getName());
		EncryptMD5 md5 = new EncryptMD5();
		String passwordEncrypted = md5.md5(player.getPassword());
		player.setPassword(passwordEncrypted);
		try{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			boolean create = playerService.createService(getPlayer());
			if (create){
				facesContext.addMessage(null, new FacesMessage("Jogador Criado com Sucesso!!")); //Mensagem de validacao 
			}else{
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: Login já existe", ""));
			}
			refresh();
			
		}catch(Exception e){
			refresh();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao criar o jogador: "+e.getMessage(), "")); //Mensagem de erro 
		}
		return null;
	}

	/*
	 * GET AND SET
	 */
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	
	

}
