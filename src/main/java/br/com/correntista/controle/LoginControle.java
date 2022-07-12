package br.com.correntista.controle;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.dao.UsuarioDao;
import br.com.correntista.dao.UsuarioDaoImpl;
import br.com.correntista.entidade.Usuario;
import br.com.correntista.seguranca.HashFunction;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="loginC")
@SessionScoped
public class LoginControle implements Serializable {

	public String testarRedirecionar() {
		return  "/private/principal.xhtml?faces-redirect=true";
	}
	
	
	
	//TODO Em construção...
	
    private static final long serialVersionUID = 1L;

    private static Usuario usuarioLogado;
    private UsuarioDao usuarioDao;
    private Usuario usuario;
    private Session sessao;

    public LoginControle() {
        usuarioDao = new UsuarioDaoImpl();
    }

    public String logar() {
        System.out.println("método logar");
        try {
            if (usuario.getLogin() == "" || usuario.getSenha() == "") {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "usuário e senha são obrigatórios!", "")); 
                return null;
            };

            pesquisaUsuarioPorLogin(usuario.getLogin());
            
            if(usuarioLogado != null) {
	            if (true == usuarioLogado.getAtivo() && usuario.getLogin().equals(usuarioLogado.getLogin())
	                    && (usuario.getSenha()).equals(usuarioLogado.getSenha())) { // usar HashFunction.sha256
	                return "/private/principal.xhtml?faces-redirect=true";
	
	            } else {
	                String mensagem = usuarioLogado.getAtivo() ? "Usuário e/ou Senha incorretos(s)" : "Usuário desativado!";
	                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
	            			mensagem, ""));
	                usuarioLogado = null;
	                return null;
	            }
            } else {
            	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
            			"Usuário e/ou Senha incorretos(s)", ""));
            	usuarioLogado = null;
                return null;
            }
        } catch (Exception e) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        			"Erro na autenticação de usuário - " + e.getMessage(), ""));
            return null;
        }
    }

    public String logout() {
        System.out.println("método logout");

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuario = null;
        usuarioLogado = null;
        return "/login.xhtml?faces-redirect=true";
    }
    

    public void pesquisaUsuarioPorLogin(String login) {
        try {
            sessao = HibernateUtil.abrirSessao();
            usuarioLogado = usuarioDao.pesquisarPorLogin(login, sessao);
        } catch (Exception e) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro na busca de usuário por login - " + e.getMessage(), ""));
        } finally {
            sessao.close();
        }
    }

    
    // Getters e Setters
    
    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogado() {
        if (usuarioLogado == null) {
            usuarioLogado = new Usuario();
        }
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLog) {
        usuarioLogado = usuarioLog;
    }

    
    public static Usuario usuarioSessao() {
        return usuarioLogado;
    }
    
	
}
