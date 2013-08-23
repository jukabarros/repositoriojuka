package exame;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exame")
public class Exame implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -147751565460592705L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_exame", unique=true, nullable=false)
	private long idExame;
	
	@Column(name="nome")	
	private String nome;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="local")
	private String local;

	public Exame() { }
	
	public long getIdExame() {
		return this.idExame;
	}

	public void setIdExame(long IdExame) {
		this.idExame = IdExame;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String pNome) {
		this.nome = pNome;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String pTipo) {
		this.tipo = pTipo;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String pLocal) {
		this.local = pLocal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idExame ^ (idExame >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		if (idExame != other.idExame)
			return false;
		return true;
	}

	
}