package rest;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import model.MsgChat;

import org.springframework.web.client.RestClientException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import config.ReadProperties;

public class MsgChatRest implements Serializable{

	private static final long serialVersionUID = -4191396071138121389L;
	private MsgChat chat;
	private List<MsgChat> chatList;
	private String uri;
	private ReadProperties readProperties = new ReadProperties();
	private Properties properties;
	
	private String restServer;
	private String restPort;
	
	
	public MsgChatRest() throws IOException {
		super();
		
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
		properties = readProperties.getProp();
		restServer =  properties.getProperty("ip.rest.server");
		restPort = properties.getProperty("port.rest.server");
		uri = "http://"+restServer+":"+restPort+"/domino_rest/rest";
	}
	
	public MsgChat sendMsg(MsgChat chat){
		try{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println("REST REQUEST: SEND CHAT MSG - "+dateFormat.format(date));
			GenericType<MsgChat> generic = new GenericType<MsgChat>() {};
			WebResource resource = Client.create().resource(uri);
			MsgChat msgChat = resource
					.path("/sendChatMsg")      
					.accept(MediaType.APPLICATION_JSON)
					.entity(chat) 
					.post(generic);

			return msgChat;
		}catch(RestClientException e){
			System.err.println("******* ERRO NO SEND CHAT MSG VIA REST: RestClient Exception *******"+e.getMessage());
			return null;
		}catch (Exception e) {
			System.err.println("******* ERRO NO SEND CHAT MSG VIA REST: Exception *******"+e.getMessage());
			return null;
		}
	}
	
	public List<MsgChat> getMsg(){
		try{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			System.out.println("REST REQUEST: GET MSG - "+dateFormat.format(date));
			GenericType<List<MsgChat>> generic = new GenericType<List<MsgChat>>() {};
			WebResource resource = Client.create().resource(uri);
			List<MsgChat> result = resource
					.path("/getMsg")      
					.accept(MediaType.APPLICATION_JSON)
					.get(generic);
			
			
			return result;
			
		}catch(Exception e){
			System.err.println("******* ERRO GET MSG VIA REST *******"+e.getMessage());
			return null;
		}
	}
	
	/*
	 * GET AND SET
	 */
	public MsgChat getChat() {
		return chat;
	}
	public void setChat(MsgChat chat) {
		this.chat = chat;
	}
	public List<MsgChat> getChatList() {
		return chatList;
	}
	public void setChatList(List<MsgChat> chatList) {
		this.chatList = chatList;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	

}
