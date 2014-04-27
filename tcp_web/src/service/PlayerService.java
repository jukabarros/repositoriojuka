package service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Player;
import dao.PlayerDao;

public class PlayerService implements Serializable{

	private static final long serialVersionUID = -1165364489920182176L;
	
	private PlayerDao dao;
	
	public PlayerService(){
		dao = new PlayerDao();
	}
	
	public boolean createService(Player player) throws SQLException{
		boolean create = dao.create(player);
		return create;
	}
	
	public ArrayList<Player> authenticate(String login, String password){
		ArrayList<Player> playerList = dao.authenticate(login, password);
		return playerList;
	}
	
	public ArrayList<Player> listPlayerByLoginService(String login){
		ArrayList<Player> playerList = dao.listPlayerByLogin(login);
		return playerList;
	}
	
	public ArrayList<Player> listPlayerOnline(){
		ArrayList<Player> playerList = dao.listPlayerOnline();
		return playerList;
	}
	
}
