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
		this.player = new Player();
		this.playerList = new ArrayList<Player>();
		
	}
	
	public ArrayList<Player> listPlayerByLogin(String login){
		Connection con = new DBConection().connect();
		try {
			this.query = "SELECT id,name,login,email,sex FROM player WHERE login = ?";
			PreparedStatement queryExec = con.prepareStatement(query);
			queryExec.setString(1, login);
			ResultSet result = queryExec.executeQuery();
			
			while (result.next()){
				this.player = new Player();
				this.player.setId(result.getLong((1)));
				this.player.setName(result.getString(2));
				this.player.setLogin(result.getString(3));
				this.player.setEmail(result.getString(4));
				this.player.setSex(result.getString(5));
				this.playerList.add(player);
				
			}
			con.close();
			
			return this.playerList;
			
		} catch (SQLException e) {
			System.err.println("******* Erro no SQL ********");
			e.printStackTrace();
			return null;
		}

	}
	
	public ArrayList<Player> listPlayerOnline(){
		Connection con = new DBConection().connect();

		try {
			this.playerList = new ArrayList<Player>();
			
			this.query = "SELECT id,name,login,email, sex, score FROM player WHERE online = 1";
			PreparedStatement queryExec = con.prepareStatement(query);
			ResultSet result = queryExec.executeQuery();
			while (result.next()){
				this.player = new Player();
				
				this.player.setId(result.getLong((1)));
				this.player.setName(result.getString(2));
				this.player.setLogin(result.getString(3));
				this.player.setEmail(result.getString(4));
				this.player.setSex(result.getString(5));
				this.player.setScore(result.getInt(6));
				
				if (!this.playerList.contains(player)){ //Nao inserir valor repetido
					this.playerList.add(player);
				}
				
			}
			con.close();
			return playerList;
			
		} catch (SQLException e) {
			System.err.println("******* Erro no SQL ********");
			e.printStackTrace();
			return null;
		}

	}

	
	public boolean create(Player p){
		Connection con = new DBConection().connect();
		this.playerList = listPlayerByLogin(this.player.getLogin()); // Tratamento de Login
		if (this.playerList.isEmpty()){
			try {
				this.query = "INSERT INTO player (name,sex,login,password,email) VALUES (?,?,?,?,?)";
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
				System.err.println("****** Erro no SQL *******");
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
