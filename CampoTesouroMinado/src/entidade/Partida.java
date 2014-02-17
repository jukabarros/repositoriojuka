package entidade;

public class Partida {
	
	private String nome;
	private int numJogadores;
	
	
	public Partida() {
		super();
	}
	
	public Partida(String nome, int numJogadores) {
		super();
		this.nome = nome;
		this.numJogadores = numJogadores;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumJogadores() {
		return numJogadores;
	}
	public void setNumJogadores(int numJogadores) {
		this.numJogadores = numJogadores;
	}
	
	

}
