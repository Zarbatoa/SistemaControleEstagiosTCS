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

import br.com.correntista.dao.EstagiarioDao;
import br.com.correntista.dao.EstagiarioDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.entidade.Endereco;
import br.com.correntista.entidade.Estagiario;
import br.com.correntista.util.Utils;
import br.com.correntista.webservice.WebServiceEndereco;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="estagiarioC")
@ViewScoped
public class EstagiarioControle {

	private Estagiario estagiario;
	private EstagiarioDao estagiarioDao;
	
	private Session sessao;
    private List<Estagiario> estagiarios;
    private DataModel<Estagiario> modelEstagiarios;
    private int aba;
    
    public EstagiarioControle() {
    	estagiarioDao = new EstagiarioDaoImpl();
    }
    
    public void pesquisarTodos() {
		sessao = HibernateUtil.abrirSessao();
		try {
			estagiarios = estagiarioDao.pesquisarTodos(sessao);
			modelEstagiarios = new ListDataModel<>(estagiarios);
			aba = 0;
		} catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void pesquisarPorNome() {
        sessao = HibernateUtil.abrirSessao();
        try {
            estagiarios = estagiarioDao.pesquisarPorNome(estagiario.getNome(), sessao);
            modelEstagiarios = new ListDataModel<>(estagiarios);
            estagiario.setNome(null);
            aba = 0;
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void pesquisarEstagiarios() {
    	if(estagiario == null || estagiario.getNome() == null || "".equals(estagiario.getNome().trim())) {
    		pesquisarTodos();
    	} else {
    		pesquisarPorNome();
    	}
    }
    
    public void buscarCep() {
        WebServiceEndereco webService = new WebServiceEndereco();
        //se nao encontrar cep retorna null
        Endereco endereco = webService.pesquisarCep(estagiario.getEndCep());
        if(endereco == null || endereco.getCep() == null) {
            endereco = new Endereco();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "CEP não encontrado", null));
        } else {
        	// Nao pega complemento e numero pelo cep
        	estagiario.setEndRua(endereco.getLogradouro());
        	estagiario.setEndBairro(endereco.getBairro());
        	estagiario.setEndCidade(endereco.getLocalidade());
        	estagiario.setEndSiglaEstado(endereco.getUf().substring(0, 2));
        	
        }
    }
    
    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getTitle().equals("Novo")) {
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }
    
    public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
        	estagiario.setCpf(Utils.desformatarCpf(estagiario.getCpf()));
		    estagiarioDao.salvarOuAlterar(estagiario, sessao);
		    estagiario = null;
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		            "Salvo com sucesso", null));
		    modelEstagiarios = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar", ""));
        } finally {
            sessao.close();
        }
	}
    
    public void excluir(){
        estagiario = modelEstagiarios.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            estagiarioDao.excluir(estagiario, sessao);
            estagiario = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluido com sucesso", null));
            modelEstagiarios = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir", ""));
        } finally {
            sessao.close();
        }
        
    }
    
    public void prepararAlterar() {
		estagiario = modelEstagiarios.getRowData();
		aba = 1;
	}
    
    // getters e setters
    
    public Estagiario getEstagiario() {
    	if(estagiario == null) {
    		estagiario = new Estagiario();
    	}
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public List<Estagiario> getEstagiarios() {
		return estagiarios;
	}

	public DataModel<Estagiario> getModelEstagiarios() {
		return modelEstagiarios;
	}

	public int getAba() {
		return aba;
	}
	
}
