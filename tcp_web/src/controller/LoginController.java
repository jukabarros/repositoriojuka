package controller;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import service.PlayerService;
import util.EncryptMD5;

import model.Player;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -6983839857205389929L;
	
	private String login;
	private String password;
	private Player player;
	private ArrayList<Player> playerList;
	private PlayerService playerService;
	
	public LoginController() {
		super();
		player = new Player();
		playerList = new ArrayList<Player>();
		playerService = new PlayerService();
		
	}
	
	public String authenticate(){
		try{
			EncryptMD5 md5 = new EncryptMD5();
			password = md5.md5(getPassword());
			
			playerList = playerService.authenticate(getLogin(), getPassword());
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
		session.invalidate();
		System.out.println("** Player: "+player.getLogin()+" logout!");
		return "../index.xhtml?faces-redirect=true";
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
	public ArrayList<Player> getplayerList() {
		return playerList;
	}
	public void setplayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	public PlayerService getPlayerService() {
		return playerService;
	}
	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	
	

}
