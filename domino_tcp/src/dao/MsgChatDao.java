package dao;

import java.io.Serializable;
import java.util.ArrayList;

import model.MsgChat;


public class MsgChatDao implements Serializable{

	private static final long serialVersionUID = 2035756765398998845L;
	
	private MsgChat chat;
	private ArrayList<MsgChat> chatList;
	
	public MsgChatDao() {
		super();
		chat = new MsgChat();
		chatList = new ArrayList<MsgChat>();
	}
	
	public ArrayList<MsgChat> addMsg (MsgChat msgChat){
		this.chatList.add(msgChat);
		return chatList;
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
	public ArrayList<MsgChat> getChatList() {
		return chatList;
	}
	public void setChatList(ArrayList<MsgChat> chatList) {
		this.chatList = chatList;
	}
	
	

}
