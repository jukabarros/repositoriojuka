package pressao_arterial;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pressao_arterial")
public class PressaoArterial implements Serializable {

	private static final long serialVersionUID = -3546413560912374818L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_pressao_arterial", unique=true, nullable=false)
	private long idPressaoArterial;
	
	@Column(name="sistolica", unique=true)	
	private int sistolica;
	
	@Column(name="diastolica", unique=true)
	private int diastolica;

	public PressaoArterial() {
	}

	public long getIdPressaoArterial() {
		return idPressaoArterial;
	}

	public void setIdPressaoArterial(long idPressaoArterial) {
		this.idPressaoArterial = idPressaoArterial;
	}

	public int getSistolica() {
		return sistolica;
	}

	public void setSistolica(int sistolica) {
		this.sistolica = sistolica;
	}

	public int getDiastolica() {
		return diastolica;
	}

	public void setDiastolica(int diastolica) {
		this.diastolica = diastolica;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idPressaoArterial ^ (idPressaoArterial >>> 32));
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
		PressaoArterial other = (PressaoArterial) obj;
		if (idPressaoArterial != other.idPressaoArterial)
			return false;
		return true;
	}
	
	
	

}
