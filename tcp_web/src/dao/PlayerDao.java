package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Player;
import config.DBConection;

public class PlayerDao implements Serializable {
	
	private static final long serialVersionUID = 8996116873142373652L;
	private ArrayList<Player> playerList;
	private Player player;
	private String query;
	
	public PlayerDao(){
		player = new Player();
		playerList = new ArrayList<Player>();
		
	}
	
	public ArrayList<Player> listPlayerByLogin(String login){
		Connection con = new DBConection().connect();
		try {
			query = "SELECT id,name,login,email,sex FROM player WHERE login = ?";
			PreparedStatement queryExec = con.prepareStatement(query);
			queryExec.setString(1, login);
			ResultSet result = queryExec.executeQuery();
			
			while (result.next()){
				player = new Player();
				player.setId(result.getLong((1)));
				player.setName(result.getString(2));
				player.setLogin(result.getString(3));
				player.setEmail(result.getString(4));
				player.setSex(result.getString(5));
				playerList.add(player);
				
			}
			con.close();
			return playerList;
			
		} catch (SQLException e) {
			System.err.println("******* Erro ********");
			e.printStackTrace();
			return null;
		}

	}
	
	public ArrayList<Player> listPlayerOnline(){
		Connection con = new DBConection().connect();
		try {
			query = "SELECT id,name,login,email FROM player WHERE online = 1";
			PreparedStatement queryExec = con.prepareStatement(query);
			ResultSet result = queryExec.executeQuery();
			while (result.next()){
				player = new Player();
				player.setId(result.getLong((1)));
				player.setName(result.getString(2));
				player.setLogin(result.getString(3));
				player.setEmail(result.getString(4));
				playerList.add(player);
				
			}
			con.close();
			return playerList;
			
		} catch (SQLException e) {
			System.err.println("******* Erro ********");
			e.printStackTrace();
			return null;
		}

	}

	
	public boolean create(Player p){
		Connection con = new DBConection().connect();
		playerList = listPlayerByLogin(player.getLogin()); // Tratamento de Login
		if (playerList.isEmpty()){
			try {
				query = "INSERT INTO player (name,sex,login,password,email) VALUES (?,?,?,?,?)";
				PreparedStatement queryExec = con.prepareStatement(query);
				queryExec.setString(1, p.getName());
				queryExec.setString(2, p.getSex());
				queryExec.setString(3, p.getLogin());
				queryExec.setString(4, p.getPassword());
				queryExec.setString(5, p.getEmail());
				queryExec.execute();
				queryExec.close();
				con.close();
				return true;
				
			} catch (SQLException e) {
				System.err.println("****** Erro de SQL *******");
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
		
	}

	/*
	 * GET AND SET
	 */
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	
}
