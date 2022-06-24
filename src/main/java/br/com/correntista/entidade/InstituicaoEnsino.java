package br.com.correntista.entidade;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author Lucas Z
*/
@Entity
@Table(name = "instituicao_ensino")
public class InstituicaoEnsino implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String razaoSocial;
    @Column(nullable = false,length = 14)
    private String cnpj;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String representante;
    @Column(nullable = false)
    private String telefoneFixo;
    @Column
    private String telefoneCel;
    @Column
    private String infoRelevante;
    
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
    
    @OneToMany(mappedBy = "instituicaoEnsino")
    private List<Usuario> usuarios;
    @OneToMany(mappedBy = "instituicaoEnsino")
    private List<Estagio> estagiosInstituicao;
    @OneToMany(mappedBy = "instituicaoEnsinoVinculada")
    private List<Estagio> estagiosUnidadeVinculada;
    
    
    
    public InstituicaoEnsino() {
    }


	public InstituicaoEnsino(Long id, String razaoSocial, String cnpj, String email, String representante,
			String telefoneFixo, String telefoneCel, String infoRelevante, String endRua, String endNumero,
			String endComplemento, String endBairro, String endCep, String endCidade, String endSiglaEstado) {
		super();
		this.id = id;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.email = email;
		this.representante = representante;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCel = telefoneCel;
		this.infoRelevante = infoRelevante;
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


	public String getRepresentante() {
		return representante;
	}


	public void setRepresentante(String representante) {
		this.representante = representante;
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


	public String getInfoRelevante() {
		return infoRelevante;
	}


	public void setInfoRelevante(String infoRelevante) {
		this.infoRelevante = infoRelevante;
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


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Estagio> getEstagiosInstituicao() {
		return estagiosInstituicao;
	}


	public void setEstagiosInstituicao(List<Estagio> estagiosInstituicao) {
		this.estagiosInstituicao = estagiosInstituicao;
	}


	public List<Estagio> getEstagiosUnidadeVinculada() {
		return estagiosUnidadeVinculada;
	}


	public void setEstagiosUnidadeVinculada(List<Estagio> estagiosUnidadeVinculada) {
		this.estagiosUnidadeVinculada = estagiosUnidadeVinculada;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cnpj, email, endBairro, endCep, endCidade, endComplemento, endNumero, endRua,
				endSiglaEstado, id, infoRelevante, razaoSocial, representante, telefoneCel, telefoneFixo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstituicaoEnsino other = (InstituicaoEnsino) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(email, other.email)
				&& Objects.equals(endBairro, other.endBairro) && Objects.equals(endCep, other.endCep)
				&& Objects.equals(endCidade, other.endCidade) && Objects.equals(endComplemento, other.endComplemento)
				&& Objects.equals(endNumero, other.endNumero) && Objects.equals(endRua, other.endRua)
				&& Objects.equals(endSiglaEstado, other.endSiglaEstado) && Objects.equals(id, other.id)
				&& Objects.equals(infoRelevante, other.infoRelevante) && Objects.equals(razaoSocial, other.razaoSocial)
				&& Objects.equals(representante, other.representante) && Objects.equals(telefoneCel, other.telefoneCel)
				&& Objects.equals(telefoneFixo, other.telefoneFixo);
	}


	@Override
	public String toString() {
		return "InstituicaoEnsino [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", email=" + email
				+ ", representante=" + representante + ", telefoneFixo=" + telefoneFixo + ", telefoneCel=" + telefoneCel
				+ ", infoRelevante=" + infoRelevante + ", endRua=" + endRua + ", endNumero=" + endNumero
				+ ", endComplemento=" + endComplemento + ", endBairro=" + endBairro + ", endCep=" + endCep
				+ ", endCidade=" + endCidade + ", endSiglaEstado=" + endSiglaEstado + "]";
	}
    
    
    
	
}
