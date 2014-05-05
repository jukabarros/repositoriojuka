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
	
	public ArrayList<Player> authenticate(String login, String password){
		Connection con = new DBConection().connect();
		try {
			query = "SELECT id,name,login,email FROM player WHERE login = ? AND password = ?";
			PreparedStatement queryExec = con.prepareStatement(query);
			queryExec.setString(1, login);
			queryExec.setString(2, password);
			ResultSet result = queryExec.executeQuery();
			
			while (result.next()){
				player = new Player();
				player.setId(result.getLong((1)));
				player.setName(result.getString(2));
				player.setLogin(result.getString(3));
				player.setEmail(result.getString(4));
				playerList.add(player);
				
			}
			// Setando campo Online
			String queryUpdate = "UPDATE player SET online = 1 WHERE id = ?";
			PreparedStatement queryExecUpdate = con.prepareStatement(queryUpdate);
			queryExecUpdate.setLong(1, player.getId());
			queryExecUpdate.execute();
			queryExecUpdate.close();
		
			con.close();
			return playerList;
			
		} catch (SQLException e) {
			System.err.println("******* Erro ********");
			e.printStackTrace();
			return null;
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
