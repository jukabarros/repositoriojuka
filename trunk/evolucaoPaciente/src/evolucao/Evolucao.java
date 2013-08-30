package evolucao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import medicamento.Medicamento;

import org.hibernate.annotations.ForeignKey;

import cirurgia.Cirurgia;

import exame.Exame;

import paciente.Paciente;
import pressao_arterial.PressaoArterial;
import refeicao.Refeicao;
import usuario.Usuario;

@Entity
@Table(name="evolucao")
public class Evolucao implements Serializable {
	
	/*
	 * ATENCAO: COLOCAR MANUALMENTE OS CASCADE NO SCRIPT DA TABELA!!!!!!!
	 */
	private static final long serialVersionUID = -1242994344878331197L;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_evolucao",nullable=false)
	private long idEvolucao;
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_Paciente")
	@JoinColumn(name="paciente", nullable=false, referencedColumnName = "id_paciente")
	private Paciente paciente;
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_Exame")
	@JoinColumn(name="exame", nullable=true, referencedColumnName = "id_exame")
	private Exame exame;
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_Cirurgia")
	@JoinColumn(name="cirurgia", nullable=true, referencedColumnName = "id_cirurgia")
	private Cirurgia cirurgia;
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_Refeicao")
	@JoinColumn(name="refeicao", nullable=true, referencedColumnName = "id_refeicao")
	private Refeicao refeicao;
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_Medicamento")
	@JoinColumn(name="medicamento", nullable=true, referencedColumnName = "id_medicamento")
	private Medicamento medicamento;
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_PA_Sistolica")
	@JoinColumn(name="sistolica", nullable=true, referencedColumnName = "id_pressao_arterial")
	private PressaoArterial sistolica;
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_PA_Diastolica")
	@JoinColumn(name="diastolica", nullable=true, referencedColumnName = "id_pressao_arterial")
	private PressaoArterial diastolica;
	
	
	@ManyToOne
	@ForeignKey(name="FK_Evolucao_Usuario")
	@JoinColumn(name="usuario", nullable=false, referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	
	@Column(name="data_hora")
	private Date dataHora;
	
	@Column(name="observacao", nullable=true, length=1000)
	private String observacao;
	
	@Column(name="hora_registro")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date horaRegistro;
	
	public Evolucao() {
	}

	public long getIdEvolucao() {
		return idEvolucao;
	}

	public void setIdEvolucao(long idEvolucao) {
		this.idEvolucao = idEvolucao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Cirurgia getCirurgia() {
		return cirurgia;
	}

	public void setCirurgia(Cirurgia cirurgia) {
		this.cirurgia = cirurgia;
	}

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public PressaoArterial getSistolica() {
		return sistolica;
	}

	public void setSistolica(PressaoArterial sistolica) {
		this.sistolica = sistolica;
	}

	public PressaoArterial getDiastolica() {
		return diastolica;
	}

	public void setDiastolica(PressaoArterial diastolica) {
		this.diastolica = diastolica;
	}
	
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Date horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
