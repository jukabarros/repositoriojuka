package exercicio01;

public class Desafio {

	private String objeto;
	private static int numObjeto = 0;
	
	public Desafio(String objeto){
		this.objeto = objeto;
		numObjeto++;
		
	}
	public String getObjeto() {
		return objeto;
	}


	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}


	public static void numObjeto(){
		System.out.println(numObjeto);
	}


	public static void main(String[] args) {
		
		
		// Criando objetos
		Desafio obj1 = new Desafio("Objeto 1");
		Desafio obj2 = new Desafio("Objeto 2");
		Desafio obj3 = new Desafio("Objeto 3");
		Desafio obj4 = new Desafio("Objeto 4");
		Desafio obj5 = new Desafio("Objeto 5");
		
		// Imprimindo quantidade de objetos Criado
		System.out.println("NUMERO DE OBJETOS CRIADOS: ");
		numObjeto();
		
	}

}
