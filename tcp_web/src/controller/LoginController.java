package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Player;
import rest.LoginRest;
import util.EncryptMD5;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -6983839857205389929L;
	
	private String login;
	private String password;
	private Player player;
	private List<Player> playerList;
	private LoginRest rest;
	
	public LoginController() throws IOException {
		player = new Player();
		playerList = new ArrayList<Player>();
		rest = new LoginRest();
	}
	
	public String authenticate(){
		try{
			EncryptMD5 md5 = new EncryptMD5();
			password = md5.md5(getPassword());
			
			playerList = rest.authenticate(getLogin(), getPassword());
			if(!playerList.isEmpty()){
				player = playerList.get(0);
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("player", player);
				System.out.println("** Player: "+player.getLogin()+" autenticado!");
				this.password = "";
				return "game?faces-redirect=true";
				}else{
					FacesContext facesContext = FacesContext.getCurrentInstance();
					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na autenticação!", "")); //Mensagem de Erro
					player = null;
					return "index";
			}
			
		}catch(Exception e){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro fatal: "+e.getMessage(), "")); //Mensagem de Erro
			return "";
		}
	}
	
	public String logout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		player = (Player) session.getAttribute("player");
		System.out.println("PLAYER ID: "+player.getId());
		String responseRest = rest.logout(player.getId().toString());
		session.invalidate();
		if (responseRest.equals("logoutOK")){
			System.out.println("** Player: "+player.getLogin()+" logout!");
			return "index.xhtml?faces-redirect=true";
		}else{
			System.err.println("Erro ao fazer o logout");
			return null;
		}
		
	}
	
	/*
	 * Get and Set
	 */
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public List<Player> getplayerList() {
		return playerList;
	}
	public void setplayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	

}
