package br.com.correntista.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

/**
*
* @author Lucas Z
*/
@Entity
@Table(name = "estagio")
@DynamicInsert(value = true)
public class Estagio implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String supervisor;
    @Column(nullable = false)
    private String cargoSupervisor;
    @Column(nullable = false)
    private String formacaoAcademica;
    @Column(nullable = false)
    private String tempoExperiencia;
    
    @Column(nullable = false)
    private String professor;
    
    @Column(nullable = false)
    private String curso;
    @Column(nullable = false)
    private String anoFase;
    @Column(nullable = false)
    private String turno;
    @Column(nullable = false)
    private String modalidade;
    @Column(nullable = false)
    private String horarioEstagio;
    //TODO Testar valores Double da Entity no xhtml depois
    @Column(nullable = false)
    private Double jornadaSemanal;
    @Column(nullable = false)
    private Double cargaHorariaTotal;
    @Column(nullable = false)
    private Double remuneracao;
    @Column(nullable = false)
    private Double valorValeTransporte;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTermino;
    @Column(nullable = false)
    private String setorOuArea;
    @Column(nullable = false)
    private String municipio;
    @Column
    private String numApoliceSeguro;
    @Column(nullable = false)
    private String tipoEstagio;
    
    @Column(nullable = false)//, columnDefinition = "BIT(1) DEFAULT 0")
    @ColumnDefault(value = "0")
    private Boolean temAgenteIntegracao = false;
    @Column
    private String nomeAgenteIntegracao;
    @Column(nullable = false)
    @ColumnDefault(value = "'ATIVO'")
    @Enumerated(EnumType.STRING)
    private StatusEstagio status = StatusEstagio.ATIVO;
    
    
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Boolean rQuadrimestral1 = false;
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Boolean rQuadrimestral2 = false;
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Boolean rQuadrimestral3 = false;
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Boolean rQuadrimestral4 = false;
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Boolean rQuadrimestral5 = false;
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Boolean rQuadrimestral6 = false;
    
    @ManyToOne
    @JoinColumn(name = "id_estagiario")
    private Estagiario estagiario;
    @ManyToOne
    @JoinColumn(name = "id_unidade_concedente")
    private UnidadeConcedente unidadeConcedente;
    @ManyToOne
    @JoinColumn(name = "id_instituicao_ensino")
    private InstituicaoEnsino instituicaoEnsino;
    @ManyToOne
    @JoinColumn(name = "id_instituicao_ensino_vinculada")
    private InstituicaoEnsino instituicaoEnsinoVinculada;
    
    public Estagio() {
    }

	public Estagio(Long id, String supervisor, String cargoSupervisor, String formacaoAcademica,
			String tempoExperiencia, String professor, String curso, String anoFase, String turno, String modalidade,
			String horarioEstagio, Double jornadaSemanal, Double cargaHorariaTotal, Double remuneracao,
			Double valorValeTransporte, Date dataInicio, Date dataTermino, String setorOuArea, String municipio,
			String numApoliceSeguro, String tipoEstagio, Boolean temAgenteIntegracao, String nomeAgenteIntegracao,
			StatusEstagio status, Boolean rQuadrimestral1, Boolean rQuadrimestral2, Boolean rQuadrimestral3,
			Boolean rQuadrimestral4, Boolean rQuadrimestral5, Boolean rQuadrimestral6) {
		super();
		this.id = id;
		this.supervisor = supervisor;
		this.cargoSupervisor = cargoSupervisor;
		this.formacaoAcademica = formacaoAcademica;
		this.tempoExperiencia = tempoExperiencia;
		this.professor = professor;
		this.curso = curso;
		this.anoFase = anoFase;
		this.turno = turno;
		this.modalidade = modalidade;
		this.horarioEstagio = horarioEstagio;
		this.jornadaSemanal = jornadaSemanal;
		this.cargaHorariaTotal = cargaHorariaTotal;
		this.remuneracao = remuneracao;
		this.valorValeTransporte = valorValeTransporte;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.setorOuArea = setorOuArea;
		this.municipio = municipio;
		this.numApoliceSeguro = numApoliceSeguro;
		this.tipoEstagio = tipoEstagio;
		this.temAgenteIntegracao = temAgenteIntegracao;
		this.nomeAgenteIntegracao = nomeAgenteIntegracao;
		this.status = status;
		this.rQuadrimestral1 = rQuadrimestral1;
		this.rQuadrimestral2 = rQuadrimestral2;
		this.rQuadrimestral3 = rQuadrimestral3;
		this.rQuadrimestral4 = rQuadrimestral4;
		this.rQuadrimestral5 = rQuadrimestral5;
		this.rQuadrimestral6 = rQuadrimestral6;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getCargoSupervisor() {
		return cargoSupervisor;
	}

	public void setCargoSupervisor(String cargoSupervisor) {
		this.cargoSupervisor = cargoSupervisor;
	}

	public String getFormacaoAcademica() {
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(String formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}

	public String getTempoExperiencia() {
		return tempoExperiencia;
	}

	public void setTempoExperiencia(String tempoExperiencia) {
		this.tempoExperiencia = tempoExperiencia;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getAnoFase() {
		return anoFase;
	}

	public void setAnoFase(String anoFase) {
		this.anoFase = anoFase;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getHorarioEstagio() {
		return horarioEstagio;
	}

	public void setHorarioEstagio(String horarioEstagio) {
		this.horarioEstagio = horarioEstagio;
	}

	public Double getJornadaSemanal() {
		return jornadaSemanal;
	}

	public void setJornadaSemanal(Double jornadaSemanal) {
		this.jornadaSemanal = jornadaSemanal;
	}

	public Double getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(Double cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public Double getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(Double remuneracao) {
		this.remuneracao = remuneracao;
	}

	public Double getValorValeTransporte() {
		return valorValeTransporte;
	}

	public void setValorValeTransporte(Double valorValeTransporte) {
		this.valorValeTransporte = valorValeTransporte;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getSetorOuArea() {
		return setorOuArea;
	}

	public void setSetorOuArea(String setorOuArea) {
		this.setorOuArea = setorOuArea;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNumApoliceSeguro() {
		return numApoliceSeguro;
	}

	public void setNumApoliceSeguro(String numApoliceSeguro) {
		this.numApoliceSeguro = numApoliceSeguro;
	}

	public String getTipoEstagio() {
		return tipoEstagio;
	}

	public void setTipoEstagio(String tipoEstagio) {
		this.tipoEstagio = tipoEstagio;
	}

	public Boolean getTemAgenteIntegracao() {
		return temAgenteIntegracao;
	}

	public void setTemAgenteIntegracao(Boolean temAgenteIntegracao) {
		this.temAgenteIntegracao = temAgenteIntegracao;
	}

	public String getNomeAgenteIntegracao() {
		return nomeAgenteIntegracao;
	}

	public void setNomeAgenteIntegracao(String nomeAgenteIntegracao) {
		this.nomeAgenteIntegracao = nomeAgenteIntegracao;
	}

	public StatusEstagio getStatus() {
		return status;
	}

	public void setStatus(StatusEstagio status) {
		this.status = status;
	}

	public Boolean getrQuadrimestral1() {
		return rQuadrimestral1;
	}

	public void setrQuadrimestral1(Boolean rQuadrimestral1) {
		this.rQuadrimestral1 = rQuadrimestral1;
	}

	public Boolean getrQuadrimestral2() {
		return rQuadrimestral2;
	}

	public void setrQuadrimestral2(Boolean rQuadrimestral2) {
		this.rQuadrimestral2 = rQuadrimestral2;
	}

	public Boolean getrQuadrimestral3() {
		return rQuadrimestral3;
	}

	public void setrQuadrimestral3(Boolean rQuadrimestral3) {
		this.rQuadrimestral3 = rQuadrimestral3;
	}

	public Boolean getrQuadrimestral4() {
		return rQuadrimestral4;
	}

	public void setrQuadrimestral4(Boolean rQuadrimestral4) {
		this.rQuadrimestral4 = rQuadrimestral4;
	}

	public Boolean getrQuadrimestral5() {
		return rQuadrimestral5;
	}

	public void setrQuadrimestral5(Boolean rQuadrimestral5) {
		this.rQuadrimestral5 = rQuadrimestral5;
	}

	public Boolean getrQuadrimestral6() {
		return rQuadrimestral6;
	}

	public void setrQuadrimestral6(Boolean rQuadrimestral6) {
		this.rQuadrimestral6 = rQuadrimestral6;
	}

	public Estagiario getEstagiario() {
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public UnidadeConcedente getUnidadeConcedente() {
		return unidadeConcedente;
	}

	public void setUnidadeConcedente(UnidadeConcedente unidadeConcedente) {
		this.unidadeConcedente = unidadeConcedente;
	}
	
	public InstituicaoEnsino getInstituicaoEnsino() {
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(InstituicaoEnsino instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public InstituicaoEnsino getInstituicaoEnsinoVinculada() {
		return instituicaoEnsinoVinculada;
	}

	public void setInstituicaoEnsinoVinculada(InstituicaoEnsino instituicaoEnsinoVinculada) {
		this.instituicaoEnsinoVinculada = instituicaoEnsinoVinculada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoFase, cargaHorariaTotal, cargoSupervisor, curso, dataInicio, dataTermino, estagiario,
				formacaoAcademica, horarioEstagio, id, instituicaoEnsino, instituicaoEnsinoVinculada, jornadaSemanal,
				modalidade, municipio, nomeAgenteIntegracao, numApoliceSeguro, professor, rQuadrimestral1,
				rQuadrimestral2, rQuadrimestral3, rQuadrimestral4, rQuadrimestral5, rQuadrimestral6, remuneracao,
				setorOuArea, status, supervisor, temAgenteIntegracao, tempoExperiencia, tipoEstagio, turno,
				unidadeConcedente, valorValeTransporte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estagio other = (Estagio) obj;
		return Objects.equals(anoFase, other.anoFase) && Objects.equals(cargaHorariaTotal, other.cargaHorariaTotal)
				&& Objects.equals(cargoSupervisor, other.cargoSupervisor) && Objects.equals(curso, other.curso)
				&& Objects.equals(dataInicio, other.dataInicio) && Objects.equals(dataTermino, other.dataTermino)
				&& Objects.equals(estagiario, other.estagiario)
				&& Objects.equals(formacaoAcademica, other.formacaoAcademica)
				&& Objects.equals(horarioEstagio, other.horarioEstagio) && Objects.equals(id, other.id)
				&& Objects.equals(instituicaoEnsino, other.instituicaoEnsino)
				&& Objects.equals(instituicaoEnsinoVinculada, other.instituicaoEnsinoVinculada)
				&& Objects.equals(jornadaSemanal, other.jornadaSemanal) && Objects.equals(modalidade, other.modalidade)
				&& Objects.equals(municipio, other.municipio)
				&& Objects.equals(nomeAgenteIntegracao, other.nomeAgenteIntegracao)
				&& Objects.equals(numApoliceSeguro, other.numApoliceSeguro)
				&& Objects.equals(professor, other.professor) && Objects.equals(rQuadrimestral1, other.rQuadrimestral1)
				&& Objects.equals(rQuadrimestral2, other.rQuadrimestral2)
				&& Objects.equals(rQuadrimestral3, other.rQuadrimestral3)
				&& Objects.equals(rQuadrimestral4, other.rQuadrimestral4)
				&& Objects.equals(rQuadrimestral5, other.rQuadrimestral5)
				&& Objects.equals(rQuadrimestral6, other.rQuadrimestral6)
				&& Objects.equals(remuneracao, other.remuneracao) && Objects.equals(setorOuArea, other.setorOuArea)
				&& status == other.status && Objects.equals(supervisor, other.supervisor)
				&& Objects.equals(temAgenteIntegracao, other.temAgenteIntegracao)
				&& Objects.equals(tempoExperiencia, other.tempoExperiencia)
				&& Objects.equals(tipoEstagio, other.tipoEstagio) && Objects.equals(turno, other.turno)
				&& Objects.equals(unidadeConcedente, other.unidadeConcedente)
				&& Objects.equals(valorValeTransporte, other.valorValeTransporte);
	}

	@Override
	public String toString() {
		return "Estagio [id=" + id + ", supervisor=" + supervisor + ", cargoSupervisor=" + cargoSupervisor
				+ ", formacaoAcademica=" + formacaoAcademica + ", tempoExperiencia=" + tempoExperiencia + ", professor="
				+ professor + ", curso=" + curso + ", anoFase=" + anoFase + ", turno=" + turno + ", modalidade="
				+ modalidade + ", horarioEstagio=" + horarioEstagio + ", jornadaSemanal=" + jornadaSemanal
				+ ", cargaHorariaTotal=" + cargaHorariaTotal + ", remuneracao=" + remuneracao + ", valorValeTransporte="
				+ valorValeTransporte + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", setorOuArea="
				+ setorOuArea + ", municipio=" + municipio + ", numApoliceSeguro=" + numApoliceSeguro + ", tipoEstagio="
				+ tipoEstagio + ", temAgenteIntegracao=" + temAgenteIntegracao + ", nomeAgenteIntegracao="
				+ nomeAgenteIntegracao + ", status=" + status + ", rQuadrimestral1=" + rQuadrimestral1
				+ ", rQuadrimestral2=" + rQuadrimestral2 + ", rQuadrimestral3=" + rQuadrimestral3 + ", rQuadrimestral4="
				+ rQuadrimestral4 + ", rQuadrimestral5=" + rQuadrimestral5 + ", rQuadrimestral6=" + rQuadrimestral6
				+ ", estagiario=" + estagiario + ", unidadeConcedente=" + unidadeConcedente + ", instituicaoEnsino="
				+ instituicaoEnsino + ", instituicaoEnsinoVinculada=" + instituicaoEnsinoVinculada + "]";
	}
	
	
}
