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

import br.com.correntista.dao.DonoDao;
import br.com.correntista.dao.DonoDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.dao.InstituicaoEnsinoDao;
import br.com.correntista.dao.InstituicaoEnsinoDaoImpl;
import br.com.correntista.dao.UsuarioDao;
import br.com.correntista.dao.UsuarioDaoImpl;
import br.com.correntista.entidade.Dono;
import br.com.correntista.entidade.InstituicaoEnsino;
import br.com.correntista.entidade.Usuario;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="usuarioC")
@ViewScoped
public class UsuarioControle {

	private Usuario usuario;
	private UsuarioDao usuarioDao;

	// verificarei o que é necessário:
	private InstituicaoEnsino instituicaoEnsino;
	private Session sessao;
    private List<Usuario> usuarios;
    private List<SelectItem> comboInstituicoes;
    private DataModel<Usuario> modelUsuarios;
    private int aba;
	
	public UsuarioControle() {
		usuarioDao = new UsuarioDaoImpl();
	}
	
	public void pesquisarTodos() {
		sessao = HibernateUtil.abrirSessao();
		try {
			usuarios = usuarioDao.pesquisarTodos(sessao);
			modelUsuarios = new ListDataModel<>(usuarios);
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
        //event.getTab().getTitle()
        if(event.getTab().getTitle().equals("Novo")) {
        	//usuario = null;
        	carregarComboInstituicoes();
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }
	
	public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
        	usuario.setInstituicaoEnsino(instituicaoEnsino);
		    usuarioDao.salvarOuAlterar(usuario, sessao);
		    usuario = null;
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
		usuario = modelUsuarios.getRowData();
		sessao = HibernateUtil.abrirSessao();
		try {
			usuarioDao.excluir(usuario, sessao);
			usuario = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluido com sucesso", null));
            modelUsuarios = null;
		} catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir", ""));
        } finally {
            sessao.close();
        }
	}
	
	public void prepararAlterar() {
		usuario = modelUsuarios.getRowData();
		carregarComboInstituicoes();
		instituicaoEnsino = usuario.getInstituicaoEnsino();
		aba = 1;
	}

	
	// getters e setters
	
	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public List<SelectItem> getComboInstituicoes() {
		return comboInstituicoes;
	}

	public DataModel<Usuario> getModelUsuarios() {
		return modelUsuarios;
	}
	
	public int getAba() {
		return aba;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	
}
