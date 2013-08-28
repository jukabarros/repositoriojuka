package cirurgia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cirurgia")
public class Cirurgia implements Serializable {

	private static final long serialVersionUID = -7163062401171570357L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cirurgia", nullable=false)
	private long idCirurgia;
	
	@Column(name="nome", unique=true)	
	private String nome;
	
	@Column(name="complexidade")
	private String complexidade;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="local", nullable=true)
	private String local;
	
	public Cirurgia() {
		
	}

	public long getIdCirurgia() {
		return idCirurgia;
	}
	
	public void setIdCirurgia(long idCirurgia) {
		this.idCirurgia = idCirurgia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idCirurgia ^ (idCirurgia >>> 32));
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
		Cirurgia other = (Cirurgia) obj;
		if (idCirurgia != other.idCirurgia)
			return false;
		return true;
	}
}