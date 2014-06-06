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
import tcp.TCPClient;
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
		this.player = new Player();
		this.playerList = new ArrayList<Player>();
		this.rest = new LoginRest();
	}
	
	public String authenticate(){
		try{
			EncryptMD5 md5 = new EncryptMD5();
			this.password = md5.md5(getPassword());
			
			this.playerList = this.rest.authenticate(getLogin(), getPassword());
			if(!playerList.isEmpty()){
				this.player = playerList.get(0);
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("player", this.player);
				System.out.println("** Player: "+this.player.getLogin()+" autenticado!");
				this.password = "";
				return "game?faces-redirect=true";
				}else{
					FacesContext facesContext = FacesContext.getCurrentInstance();
					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha na autenticação.", "")); //Mensagem de Erro
					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login/Senha errado(s) ou", "")); //Mensagem de Erro
					player = null;
					return "index";
			}
			
		}catch(Exception e){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro fatal, Jogador já está online", "")); //Mensagem de Erro
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entre em contato o adm do jogo.", "")); //Mensagem de Erro
			return "";
		}
	}
	
	public String logout() throws IOException{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		TCPClient tcp = new TCPClient();
		this.player = (Player) session.getAttribute("player");
		String responseRest = this.rest.logout(this.player.getId().toString());
		/*
		 * ERRO AQUI: Apos logout, encerrar a conexao TCP
		 */
//		tcp.sendTCPMsg("the_end::"+player.getLogin());
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
