package br.com.correntista.controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import br.com.correntista.dao.EstagiarioDao;
import br.com.correntista.dao.EstagiarioDaoImpl;
import br.com.correntista.dao.EstagioDao;
import br.com.correntista.dao.EstagioDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.dao.UnidadeConcedenteDao;
import br.com.correntista.dao.UnidadeConcedenteDaoImpl;
import br.com.correntista.entidade.Estagiario;
import br.com.correntista.entidade.Estagio;
import br.com.correntista.entidade.UnidadeConcedente;
import br.com.correntista.util.Utils;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="estagioC")
@ViewScoped
public class EstagioControle {

	// Atributos para edicao de estagios novos ou nao
	private Estagio estagio;
	private EstagioDao estagioDao;
	
	private Estagiario estagiario;
    private List<SelectItem> comboEstagiarios;

	private UnidadeConcedente unidadeConcedente;
    private List<SelectItem> comboUnidadesConcedentes;

    // Criar atributos para as duas combobox de instituicao_ensino aqui!
    // TODO
    
    // Atributos para as telas de pesquisa
    private Session sessao;
    private List<Estagio> estagios;
    private DataModel<Estagio> modelEstagios;
    
    private List<Estagio> estagiosAtivos;
    private DataModel<Estagio> modelEstagiosAtivos;
    
    private List<Estagio> estagiosInativos;
    private DataModel<Estagio> modelEstagiosInativos;
    
    private int aba;
    
    
    public EstagioControle() {
    	estagioDao = new EstagioDaoImpl();
    }
    
    public void pesquisarTodos() {
		sessao = HibernateUtil.abrirSessao();
		try {
			estagios = estagioDao.pesquisarTodos(sessao);
			modelEstagios = new ListDataModel<>(estagios);
			aba = 0;
		} catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void pesquisarAtivos() {
		sessao = HibernateUtil.abrirSessao();
		try {
			estagiosAtivos = estagioDao.pesquisarAtivos(sessao);
			modelEstagiosAtivos = new ListDataModel<>(estagiosAtivos);
			aba = 0;
		} catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void pesquisarInativos() {
		sessao = HibernateUtil.abrirSessao();
		try {
			estagiosInativos = estagioDao.pesquisarInativos(sessao);
			modelEstagiosInativos = new ListDataModel<>(estagiosInativos);
			aba = 1;
		} catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void carregarComboEstagiarios() {
		sessao = HibernateUtil.abrirSessao();
        EstagiarioDao estagiarioDao = new EstagiarioDaoImpl();
        try {
            List<Estagiario> estagiarios = estagiarioDao.pesquisarTodos(sessao);
            comboEstagiarios = new ArrayList<>();
            for (Estagiario estagiario : estagiarios) {
            	String nomeCombo = estagiario.getNome() + " - " + Utils.formatarCpf(estagiario.getCpf());
            	comboEstagiarios.add(new SelectItem(estagiario.getId(), nomeCombo));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox estagiarios " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void carregarComboUnidadesConcedentes() {
		sessao = HibernateUtil.abrirSessao();
        UnidadeConcedenteDao unidadeConcedenteDao = new UnidadeConcedenteDaoImpl();
        try {
            List<UnidadeConcedente> unidadesConcedentes = unidadeConcedenteDao.pesquisarTodos(sessao);
            comboUnidadesConcedentes = new ArrayList<>();
            for (UnidadeConcedente unidadeConcedente: unidadesConcedentes) {
            	comboUnidadesConcedentes.add(new SelectItem(unidadeConcedente.getId(), unidadeConcedente.getRazaoSocial()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox unidades concedentes " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getTitle().equals("Novo")) {
        	carregarComboEstagiarios();
        	carregarComboUnidadesConcedentes();
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }
    
//    public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
//    	sessao = HibernateUtil.abrirSessao();
//        try {
//	    	EstagiarioDao estagiarioDao = new EstagiarioDaoImpl();
//	    	estagiario = estagiarioDao.pesquisarPorId(estagiario.getId(), sessao);
//	    	
//	    	curso = estagiario.getCurso();
//	    	instituicaoEnsino = curso.getInstituicaoEnsino();
//        } catch (HibernateException e) {
//	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//	                    "Erro ao carregar informações do estagiário do comboBox", ""));
//	        } finally {
//	            sessao.close();
//	    }
//    }
    
    public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
        	estagio.setUnidadeConcedente(unidadeConcedente);
        	estagio.setEstagiario(estagiario);
		    estagioDao.salvarOuAlterar(estagio, sessao);
		    estagio = null;
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		            "Salvo com sucesso", null));
		    modelEstagiosAtivos = null;
		    estagiario = null;
		    unidadeConcedente = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar", ""));
        } finally {
            sessao.close();
        }
	}
    
    public void prepararAlterarAtivo() {
		estagio = modelEstagiosAtivos.getRowData();
		carregarComboEstagiarios();
		carregarComboUnidadesConcedentes();
		estagiario = estagio.getEstagiario();
		unidadeConcedente = estagio.getUnidadeConcedente();
		aba = 2;
	}
    
    public void prepararVisualizarInativo() {
		estagio = modelEstagiosInativos.getRowData();
		carregarComboEstagiarios();
		carregarComboUnidadesConcedentes();
		estagiario = estagio.getEstagiario();
		unidadeConcedente = estagio.getUnidadeConcedente();
		aba = 2;
	}

    
    // getters e setters
    
    public Estagio getEstagio() {
    	if(estagio == null) {
    		estagio = new Estagio();
    	}
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public Estagiario getEstagiario() {
		if(estagiario == null) {
			estagiario = new Estagiario();
		}
		return estagiario;
	}

	public void setEstagiario(Estagiario estagiario) {
		this.estagiario = estagiario;
	}

	public UnidadeConcedente getUnidadeConcedente() {
		if(unidadeConcedente == null) {
			unidadeConcedente = new UnidadeConcedente();
		}
		return unidadeConcedente;
	}

	public void setUnidadeConcedente(UnidadeConcedente unidadeConcedente) {
		this.unidadeConcedente = unidadeConcedente;
	}

	public List<SelectItem> getComboEstagiarios() {
		return comboEstagiarios;
	}

	public List<SelectItem> getComboUnidadesConcedentes() {
		return comboUnidadesConcedentes;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public DataModel<Estagio> getModelEstagios() {
		return modelEstagios;
	}

	public List<Estagio> getEstagiosAtivos() {
		return estagiosAtivos;
	}

	public DataModel<Estagio> getModelEstagiosAtivos() {
		return modelEstagiosAtivos;
	}

	public List<Estagio> getEstagiosInativos() {
		return estagiosInativos;
	}

	public DataModel<Estagio> getModelEstagiosInativos() {
		return modelEstagiosInativos;
	}

	public int getAba() {
		return aba;
	}

	
}
