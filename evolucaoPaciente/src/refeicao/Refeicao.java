package refeicao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="refeicao")
public class Refeicao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_refeicao", nullable=false)
	private long idRefeicao;
	
	@Column(name="prato", unique=true)	
	private String prato;
	
	@Column(name="tipo")	
	private String tipo;
	
	@Column(name="descricao", nullable=true)	
	private String descricao;
	
	public Refeicao() {
	}

	public long getIdRefeicao() {
		return idRefeicao;
	}


	public void setIdRefeicao(long idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	public String getPrato() {
		return prato;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idRefeicao ^ (idRefeicao >>> 32));
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
		Refeicao other = (Refeicao) obj;
		if (idRefeicao != other.idRefeicao)
			return false;
		return true;
	}
}
