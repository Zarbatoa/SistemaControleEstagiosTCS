package br.com.correntista.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
*
* @author Lucas Z
*/
@Entity
@Table(name = "estagiario")
public class Estagiario implements Serializable {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String telefoneFixo;
    @Column
    private String telefoneCel;
    
    @Column(nullable = false)
    private String nomeDoPai;
    @Column(nullable = false)
    private String nomeDaMae;
    @Column(nullable = false)
    private Boolean matriculado;
    @Column(nullable = false)
    private String numeroMatricula;
    
    @Column
    private String representanteLegal;
    @Column
    private String rgRepLegal;
    
    @Column(nullable = false)
    private String endRua;
    @Column(nullable = false)
    private String endNumero;
    @Column
    private String endComplemento;
    @Column(nullable = false)
    private String endBairro;
    @Column(nullable = false)
    private String endCep;
    @Column(nullable = false)
    private String endCidade;
    @Column(nullable = false, length = 2)
    private String endSiglaEstado;
    
    
    @OneToMany(mappedBy = "estagiario")
    private List<Estagio> estagios;
    
    public Estagiario() {
    }


	public Estagiario(Long id, String nome, String email, Date dataNascimento, String cpf, String telefoneFixo,
			String telefoneCel, String nomeDoPai, String nomeDaMae, Boolean matriculado, String numeroMatricula,
			String representanteLegal, String rgRepLegal, String endRua, String endNumero, String endComplemento,
			String endBairro, String endCep, String endCidade, String endSiglaEstado) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCel = telefoneCel;
		this.nomeDoPai = nomeDoPai;
		this.nomeDaMae = nomeDaMae;
		this.matriculado = matriculado;
		this.numeroMatricula = numeroMatricula;
		this.representanteLegal = representanteLegal;
		this.rgRepLegal = rgRepLegal;
		this.endRua = endRua;
		this.endNumero = endNumero;
		this.endComplemento = endComplemento;
		this.endBairro = endBairro;
		this.endCep = endCep;
		this.endCidade = endCidade;
		this.endSiglaEstado = endSiglaEstado;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTelefoneFixo() {
		return telefoneFixo;
	}


	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}


	public String getTelefoneCel() {
		return telefoneCel;
	}


	public void setTelefoneCel(String telefoneCel) {
		this.telefoneCel = telefoneCel;
	}


	public String getNomeDoPai() {
		return nomeDoPai;
	}


	public void setNomeDoPai(String nomeDoPai) {
		this.nomeDoPai = nomeDoPai;
	}


	public String getNomeDaMae() {
		return nomeDaMae;
	}


	public void setNomeDaMae(String nomeDaMae) {
		this.nomeDaMae = nomeDaMae;
	}


	public Boolean getMatriculado() {
		return matriculado;
	}


	public void setMatriculado(Boolean matriculado) {
		this.matriculado = matriculado;
	}


	public String getNumeroMatricula() {
		return numeroMatricula;
	}


	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}


	public String getRepresentanteLegal() {
		return representanteLegal;
	}


	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}


	public String getRgRepLegal() {
		return rgRepLegal;
	}


	public void setRgRepLegal(String rgRepLegal) {
		this.rgRepLegal = rgRepLegal;
	}


	public String getEndRua() {
		return endRua;
	}


	public void setEndRua(String endRua) {
		this.endRua = endRua;
	}


	public String getEndNumero() {
		return endNumero;
	}


	public void setEndNumero(String endNumero) {
		this.endNumero = endNumero;
	}


	public String getEndComplemento() {
		return endComplemento;
	}


	public void setEndComplemento(String endComplemento) {
		this.endComplemento = endComplemento;
	}


	public String getEndBairro() {
		return endBairro;
	}


	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}


	public String getEndCep() {
		return endCep;
	}


	public void setEndCep(String endCep) {
		this.endCep = endCep;
	}


	public String getEndCidade() {
		return endCidade;
	}


	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}


	public String getEndSiglaEstado() {
		return endSiglaEstado;
	}


	public void setEndSiglaEstado(String endSiglaEstado) {
		this.endSiglaEstado = endSiglaEstado;
	}
	

	public List<Estagio> getEstagios() {
		return estagios;
	}


	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, email, endBairro, endCep, endCidade, endComplemento, endNumero, endRua,
				endSiglaEstado, id, matriculado, nome, nomeDaMae, nomeDoPai, numeroMatricula, representanteLegal,
				rgRepLegal, telefoneCel, telefoneFixo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estagiario other = (Estagiario) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(email, other.email) && Objects.equals(endBairro, other.endBairro)
				&& Objects.equals(endCep, other.endCep) && Objects.equals(endCidade, other.endCidade)
				&& Objects.equals(endComplemento, other.endComplemento) && Objects.equals(endNumero, other.endNumero)
				&& Objects.equals(endRua, other.endRua) && Objects.equals(endSiglaEstado, other.endSiglaEstado)
				&& Objects.equals(id, other.id) && Objects.equals(matriculado, other.matriculado)
				&& Objects.equals(nome, other.nome) && Objects.equals(nomeDaMae, other.nomeDaMae)
				&& Objects.equals(nomeDoPai, other.nomeDoPai) && Objects.equals(numeroMatricula, other.numeroMatricula)
				&& Objects.equals(representanteLegal, other.representanteLegal)
				&& Objects.equals(rgRepLegal, other.rgRepLegal) && Objects.equals(telefoneCel, other.telefoneCel)
				&& Objects.equals(telefoneFixo, other.telefoneFixo);
	}


	@Override
	public String toString() {
		return "Estagiario [id=" + id + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento
				+ ", cpf=" + cpf + ", telefoneFixo=" + telefoneFixo + ", telefoneCel=" + telefoneCel + ", nomeDoPai="
				+ nomeDoPai + ", nomeDaMae=" + nomeDaMae + ", matriculado=" + matriculado + ", numeroMatricula="
				+ numeroMatricula + ", representanteLegal=" + representanteLegal + ", rgRepLegal=" + rgRepLegal
				+ ", endRua=" + endRua + ", endNumero=" + endNumero + ", endComplemento=" + endComplemento
				+ ", endBairro=" + endBairro + ", endCep=" + endCep + ", endCidade=" + endCidade + ", endSiglaEstado="
				+ endSiglaEstado + "]";
	}
    
    
    

}
