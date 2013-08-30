package usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="equipe_medica")
public class EquipeMedica implements Serializable{

	private static final long serialVersionUID = 2643614850760197040L;
	
	@ManyToOne
	@ForeignKey(name="FK_Usuario_EquipeMedica")
	@JoinColumn(name="usuario", nullable=false, referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	@Column(name="cargo", nullable=false)
	private String cargo;
	
	@Column(name="num_conselho", nullable=true)
	private String numConselho;

	public EquipeMedica() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNumConselho() {
		return numConselho;
	}

	public void setNumConselho(String numConselho) {
		this.numConselho = numConselho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		EquipeMedica other = (EquipeMedica) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
	
}
