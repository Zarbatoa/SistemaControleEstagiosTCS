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
import br.com.correntista.dao.InstituicaoEnsinoDao;
import br.com.correntista.dao.InstituicaoEnsinoDaoImpl;
import br.com.correntista.entidade.Endereco;
import br.com.correntista.entidade.InstituicaoEnsino;
import br.com.correntista.util.Utils;
import br.com.correntista.webservice.WebServiceEndereco;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="instituciaoC")
@ViewScoped
public class InstituicaoEnsinoControle {

	private InstituicaoEnsino instituicaoEnsino;
	private InstituicaoEnsinoDao instituicaoEnsionDao;
	private Session sessao;
	private List<InstituicaoEnsino> instituicoes;
	
	private DataModel<InstituicaoEnsino> modelInstituicoes;
	private int aba;
	
	public InstituicaoEnsinoControle() {
		instituicaoEnsionDao = new InstituicaoEnsinoDaoImpl();
	}
	
	public void pesquisarTodos() {
		sessao = HibernateUtil.abrirSessao();
		try {
			instituicoes = instituicaoEnsionDao.pesquisarTodos(sessao);
			modelInstituicoes = new ListDataModel<>(instituicoes);
			aba = 0;
		} catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
	
	public void buscarCep() {
        WebServiceEndereco webService = new WebServiceEndereco();
        //se nao encontrar cep retorna null
        Endereco endereco = webService.pesquisarCep(instituicaoEnsino.getEndCep());
        if(endereco == null || endereco.getCep() == null) {
            endereco = new Endereco();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "CEP não encontrado", null));
        } else {
        	// Nao pega complemento e numero pelo cep
        	instituicaoEnsino.setEndRua(endereco.getLogradouro());
        	instituicaoEnsino.setEndBairro(endereco.getBairro());
        	instituicaoEnsino.setEndCidade(endereco.getLocalidade());
        	instituicaoEnsino.setEndSiglaEstado(endereco.getUf().substring(0, 2));
        	
        }
    }
	
	public void onTabChange(TabChangeEvent event) {
        //event.getTab().getTitle()
//        if(event.getTab().getTitle().equals("Novo")) {
//        }
    }

    public void onTabClose(TabCloseEvent event) {
    }
    
    public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
        	instituicaoEnsino.setCnpj(Utils.desformatarCnpj(instituicaoEnsino.getCnpj()));
            instituicaoEnsionDao.salvarOuAlterar(instituicaoEnsino, sessao);
            instituicaoEnsino = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com sucesso", null));
            modelInstituicoes = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar", ""));
            System.out.println("========");
            System.out.println(e.getMessage());
            System.out.println("========");
            System.out.println(instituicaoEnsino.toString());
            System.out.println("========");
        } finally {
            sessao.close();
        }
    }
    
    public void excluir(){
        instituicaoEnsino = modelInstituicoes.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            instituicaoEnsionDao.excluir(instituicaoEnsino, sessao);
            instituicaoEnsino = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluido com sucesso", null));
            modelInstituicoes = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir", ""));
        } finally {
            sessao.close();
        }
        
    }
    
    public void prepararAlterar(){
        instituicaoEnsino = modelInstituicoes.getRowData();
        aba = 1;
    }
    
    // getters e setters

	public InstituicaoEnsino getInstituicaoEnsino() {
		if(instituicaoEnsino == null) {
			instituicaoEnsino = new InstituicaoEnsino();
		}
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(InstituicaoEnsino instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public DataModel<InstituicaoEnsino> getModelInstituicoes() {
		return modelInstituicoes;
	}

	public int getAba() {
		return aba;
	}
	
	
	
	
	
	
}
