package br.com.correntista.controle;

import br.com.correntista.dao.DonoDao;
import br.com.correntista.dao.DonoDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.entidade.Dono;
import br.com.correntista.entidade.Endereco;
import br.com.correntista.webservice.WebServiceEndereco;
import java.util.ArrayList;
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

/**
 *
 * @author Lucas Z
 */
@ManagedBean(name="donoC")
@ViewScoped
public class DonoControle {
    
    private Dono dono;
    private Endereco endereco;
    private DonoDao donoDao;
    private Session sessao;
    private List<Dono> donos;
    
    private DataModel<Dono> modelDonos;
    private int aba;
    
    public DonoControle() {
        donoDao = new DonoDaoImpl();
    }
    
    public void pesquisarPorNome() {
        sessao = HibernateUtil.abrirSessao();
        try {
            donos = donoDao.pesquisarPorNome(dono.getNome(), sessao);
            modelDonos = new ListDataModel<>(donos);
            dono.setNome(null);
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
        endereco = webService.pesquisarCep(endereco.getCep());
        if(endereco == null || endereco.getCep() == null) {
            endereco = new Endereco();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "CEP não encontrado", null));
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
            endereco.setDono(dono);
            dono.setEndereco(endereco);
            
            donoDao.salvarOuAlterar(dono, sessao);
            dono = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com sucesso", null));
            modelDonos = null;
            endereco = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar", ""));
        } finally {
            sessao.close();
        }
    }
    
    // como é composição, o excluir exclui endereço tambem
    public void excluir(){
        dono = modelDonos.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            donoDao.excluir(dono, sessao);
            dono = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluido com sucesso", null));
            modelDonos = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir", ""));
        } finally {
            sessao.close();
        }
        
    }
    
    public void prepararAlterar(){
        dono = modelDonos.getRowData();
        endereco = dono.getEndereco();
        aba = 1;
    }
    
    // getters e setters

    public Dono getDono() {
        if(dono == null) {
            dono = new Dono();
        }
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    public DataModel<Dono> getModalDonos() {
        return modelDonos;
    }

    public int getAba() {
        return aba;
    }

    public Endereco getEndereco() {
        if(endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
