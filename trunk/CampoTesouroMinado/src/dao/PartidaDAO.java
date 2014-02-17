package dao;

import java.util.ArrayList;

import entidade.Partida;

public class PartidaDAO {
	
	private ArrayList<Partida> listaPartidas = new ArrayList<Partida>();
	
	public void inserirPartida(Partida p){
		System.out.println("Criando a Partida");
		listaPartidas.add(p);
		System.out.println("Partida criada com sucesso!");
		
	}

	public ArrayList<Partida> getListaPartidas() {
		return listaPartidas;
	}

	public void setListaPartidas(ArrayList<Partida> listaPartidas) {
		this.listaPartidas = listaPartidas;
	}

}
