package br.com.correntista.controle;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.dao.UnidadeConcedenteDao;
import br.com.correntista.dao.UnidadeConcedenteDaoImpl;
import br.com.correntista.entidade.Endereco;
import br.com.correntista.entidade.UnidadeConcedente;
import br.com.correntista.util.Utils;
import br.com.correntista.webservice.WebServiceEndereco;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="empresaC")
@ViewScoped
public class UnidadeConcedenteControle {

	private UnidadeConcedente unidadeConcedente;
	private UnidadeConcedenteDao unidadeConcedenteDao;
	
	private Session sessao;
	private List<UnidadeConcedente> unidadesConcedentes;
	private DataModel<UnidadeConcedente> modelUnidades;
	
	private int aba;
	
	public UnidadeConcedenteControle() {
		unidadeConcedenteDao = new UnidadeConcedenteDaoImpl();
	}
	
	 public void pesquisarTodos() {
			sessao = HibernateUtil.abrirSessao();
			try {
				unidadesConcedentes = unidadeConcedenteDao.pesquisarTodos(sessao);
				modelUnidades = new ListDataModel<>(unidadesConcedentes);
				aba = 0;
			} catch (HibernateException e) {
	            System.out.println("Erro ao pesquisar " + e.getMessage());
	        } finally {
	            sessao.close();
	        }
		}
	    
	    public void pesquisarPorRazaoSocial() {
	        sessao = HibernateUtil.abrirSessao();
	        try {
	        	unidadesConcedentes = unidadeConcedenteDao.pesquisarPorRazaoSocial(unidadeConcedente.getRazaoSocial(), sessao);
	            modelUnidades = new ListDataModel<>(unidadesConcedentes);
	            unidadeConcedente.setRazaoSocial(null);
	            aba = 0;
	        } catch (HibernateException e) {
	            System.out.println("Erro ao pesquisar " + e.getMessage());
	        } finally {
	            sessao.close();
	        }
	    }
	    
	    public void pesquisarUnidadesConcedentes() {
	    	if(unidadeConcedente == null || unidadeConcedente.getRazaoSocial() == null || "".equals(unidadeConcedente.getRazaoSocial().trim())) {
	    		pesquisarTodos();
	    	} else {
	    		pesquisarPorRazaoSocial();
	    	}
	    }
	    
	    public void buscarCep() {
	        WebServiceEndereco webService = new WebServiceEndereco();
	        //se nao encontrar cep retorna null
	        Endereco endereco = webService.pesquisarCep(unidadeConcedente.getEndCep());
	        if(endereco == null || endereco.getCep() == null) {
	            endereco = new Endereco();
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
	                    "CEP não encontrado", null));
	        } else {
	        	// Nao pega complemento e numero pelo cep
	        	unidadeConcedente.setEndRua(endereco.getLogradouro());
	        	unidadeConcedente.setEndBairro(endereco.getBairro());
	        	unidadeConcedente.setEndCidade(endereco.getLocalidade());
	        	unidadeConcedente.setEndSiglaEstado(endereco.getUf().substring(0, 2));
	        	
	        }
	    }
	    
	    public void onTabChange(TabChangeEvent event) {
	    }

	    public void onTabClose(TabCloseEvent event) {
	    }
	
	    public void salvar(){
	        sessao = HibernateUtil.abrirSessao();
	        try {
	        	unidadeConcedente.setCnpj(Utils.desformatarCnpj(unidadeConcedente.getCnpj()));
	        	unidadeConcedente.setCpfRepresentante(Utils.desformatarCpf(unidadeConcedente.getCpfRepresentante()));
	        	unidadeConcedenteDao.salvarOuAlterar(unidadeConcedente, sessao);
	        	unidadeConcedente = null;
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
			            "Salvo com sucesso", null));
			    modelUnidades = null;
	        } catch (HibernateException e) {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Erro ao salvar", ""));
	        } finally {
	            sessao.close();
	        }
		}
	    
	    public void excluir(){
	    	unidadeConcedente = modelUnidades.getRowData();
	        sessao = HibernateUtil.abrirSessao();
	        try {
	        	unidadeConcedenteDao.excluir(unidadeConcedente, sessao);
	        	unidadeConcedente = null;
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Excluido com sucesso", null));
	            modelUnidades = null;
	        } catch (HibernateException e) {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Erro ao excluir", ""));
	        } finally {
	            sessao.close();
	        }
	        
	    }
	    
	    public void prepararAlterar() {
	    	unidadeConcedente = modelUnidades.getRowData();
			aba = 1;
		}
	    
	    
	    // getters e setters
	    
	    public UnidadeConcedente getUnidadeConcedente() {
	    	if(unidadeConcedente == null) {
	    		unidadeConcedente = new UnidadeConcedente();
	    	}
			return unidadeConcedente;
		}

		public void setUnidadeConcedente(UnidadeConcedente unidadeConcedente) {
			this.unidadeConcedente = unidadeConcedente;
		}

		public List<UnidadeConcedente> getUnidadesConcedentes() {
			return unidadesConcedentes;
		}

		public DataModel<UnidadeConcedente> getModelUnidades() {
			return modelUnidades;
		}

		public int getAba() {
			return aba;
		}
	    
	
}
