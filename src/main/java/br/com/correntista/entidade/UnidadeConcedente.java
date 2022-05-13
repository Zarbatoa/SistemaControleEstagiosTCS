package br.com.correntista.entidade;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* @author Lucas Z
*/
@Entity
@Table(name = "unidade_concedente")
public class UnidadeConcedente {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String razaoSocial;
    @Column(nullable = false, length = 14)
    private String cnpj;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefoneFixo;
    @Column
    private String telefoneCel;
    @Column(nullable = false)
    private String representante;
    @Column(nullable = false)
    private String cargoDoRepresentante;
    @Column(nullable = false, length = 11)
    private String cpfRepresentante;
    @Column
    private String infoRelevantes;
    @Column(nullable = false)
    private Boolean ehAgenciaIntegradora;
    
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
    
    public UnidadeConcedente(){
    }

	public UnidadeConcedente(Long id, String razaoSocial, String cnpj, String email, String telefoneFixo,
			String telefoneCel, String representante, String cargoDoRepresentante, String cpfRepresentante,
			String infoRelevantes, Boolean ehAgenciaIntegradora, String endRua, String endNumero, String endComplemento,
			String endBairro, String endCep, String endCidade, String endSiglaEstado) {
		super();
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.email = email;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCel = telefoneCel;
		this.representante = representante;
		this.cargoDoRepresentante = cargoDoRepresentante;
		this.cpfRepresentante = cpfRepresentante;
		this.infoRelevantes = infoRelevantes;
		this.ehAgenciaIntegradora = ehAgenciaIntegradora;
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

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getCargoDoRepresentante() {
		return cargoDoRepresentante;
	}

	public void setCargoDoRepresentante(String cargoDoRepresentante) {
		this.cargoDoRepresentante = cargoDoRepresentante;
	}

	public String getCpfRepresentante() {
		return cpfRepresentante;
	}

	public void setCpfRepresentante(String cpfRepresentante) {
		this.cpfRepresentante = cpfRepresentante;
	}

	public String getInfoRelevantes() {
		return infoRelevantes;
	}

	public void setInfoRelevantes(String infoRelevantes) {
		this.infoRelevantes = infoRelevantes;
	}

	public Boolean getEhAgenciaIntegradora() {
		return ehAgenciaIntegradora;
	}

	public void setEhAgenciaIntegradora(Boolean ehAgenciaIntegradora) {
		this.ehAgenciaIntegradora = ehAgenciaIntegradora;
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

	@Override
	public int hashCode() {
		return Objects.hash(cargoDoRepresentante, cnpj, cpfRepresentante, ehAgenciaIntegradora, email, endBairro,
				endCep, endCidade, endComplemento, endNumero, endRua, endSiglaEstado, id, infoRelevantes, razaoSocial,
				representante, telefoneCel, telefoneFixo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeConcedente other = (UnidadeConcedente) obj;
		return Objects.equals(cargoDoRepresentante, other.cargoDoRepresentante) && Objects.equals(cnpj, other.cnpj)
				&& Objects.equals(cpfRepresentante, other.cpfRepresentante)
				&& Objects.equals(ehAgenciaIntegradora, other.ehAgenciaIntegradora)
				&& Objects.equals(email, other.email) && Objects.equals(endBairro, other.endBairro)
				&& Objects.equals(endCep, other.endCep) && Objects.equals(endCidade, other.endCidade)
				&& Objects.equals(endComplemento, other.endComplemento) && Objects.equals(endNumero, other.endNumero)
				&& Objects.equals(endRua, other.endRua) && Objects.equals(endSiglaEstado, other.endSiglaEstado)
				&& Objects.equals(id, other.id) && Objects.equals(infoRelevantes, other.infoRelevantes)
				&& Objects.equals(razaoSocial, other.razaoSocial) && Objects.equals(representante, other.representante)
				&& Objects.equals(telefoneCel, other.telefoneCel) && Objects.equals(telefoneFixo, other.telefoneFixo);
	}

	@Override
	public String toString() {
		return "UnidadeConcedente [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", email=" + email
				+ ", telefoneFixo=" + telefoneFixo + ", telefoneCel=" + telefoneCel + ", representante=" + representante
				+ ", cargoDoRepresentante=" + cargoDoRepresentante + ", cpfRepresentante=" + cpfRepresentante
				+ ", infoRelevantes=" + infoRelevantes + ", ehAgenciaIntegradora=" + ehAgenciaIntegradora + ", endRua="
				+ endRua + ", endNumero=" + endNumero + ", endComplemento=" + endComplemento + ", endBairro="
				+ endBairro + ", endCep=" + endCep + ", endCidade=" + endCidade + ", endSiglaEstado=" + endSiglaEstado
				+ "]";
	}
    
    
	
}
