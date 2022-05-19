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

import br.com.correntista.dao.CursoDao;
import br.com.correntista.dao.CursoDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.dao.InstituicaoEnsinoDao;
import br.com.correntista.dao.InstituicaoEnsinoDaoImpl;
import br.com.correntista.entidade.Curso;
import br.com.correntista.entidade.InstituicaoEnsino;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="cursoC")
@ViewScoped
public class CursoControle {

	private Curso curso;
	private CursoDao cursoDao;
	
	private InstituicaoEnsino instituicaoEnsino;
	private Session sessao;
    private List<Curso> cursos;
    private List<SelectItem> comboInstituicoes;
    private DataModel<Curso> modelCursos;
    private int aba;
	
    public CursoControle() {
    	cursoDao = new CursoDaoImpl();
    }
    
    public void pesquisarTodos() {
		sessao = HibernateUtil.abrirSessao();
		try {
			cursos = cursoDao.pesquisarTodos(sessao);
			modelCursos = new ListDataModel<>(cursos);
			aba = 0;
		} catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void carregarComboInstituicoes() {
		sessao = HibernateUtil.abrirSessao();
        InstituicaoEnsinoDao instituicaoDao = new InstituicaoEnsinoDaoImpl();
        try {
            List<InstituicaoEnsino> instituicoes = instituicaoDao.pesquisarTodos(sessao);
            comboInstituicoes = new ArrayList<>();
            for (InstituicaoEnsino instituicao : instituicoes) {
            	comboInstituicoes.add(new SelectItem(instituicao.getId(), instituicao.getRazaoSocial()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox instituicoes " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getTitle().equals("Novo")) {
        	carregarComboInstituicoes();
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }
    
    public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
        	curso.setInstituicaoEnsino(instituicaoEnsino);
		    cursoDao.salvarOuAlterar(curso, sessao);
		    curso = null;
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		            "Salvo com sucesso", null));
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar", ""));
        } finally {
            sessao.close();
        }
	}
    
    public void excluir() {
		curso = modelCursos.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			cursoDao.excluir(curso, sessao);
			curso = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluido com sucesso", null));
            modelCursos = null;
		} catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir", ""));
        } finally {
            sessao.close();
        }
	}
    
    public void prepararAlterar() {
		curso = modelCursos.getRowData();
		carregarComboInstituicoes();
		instituicaoEnsino = curso.getInstituicaoEnsino();
		aba = 1;
	}

    
    // getters e setters
    
    
    public Curso getCurso() {
    	if(curso == null) {
    		curso = new Curso();
    	}
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public InstituicaoEnsino getInstituicaoEnsino() {
		if(instituicaoEnsino == null) {
			instituicaoEnsino = new InstituicaoEnsino();
		}
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(InstituicaoEnsino instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public List<SelectItem> getComboInstituicoes() {
		return comboInstituicoes;
	}

	public DataModel<Curso> getModelCursos() {
		return modelCursos;
	}

	public int getAba() {
		return aba;
	}
    
    
    
    
    
    
    
    
    
    
}
