package br.com.correntista.controle;

import br.com.correntista.dao.ComportamentoDao;
import br.com.correntista.dao.ComportamentoDaoImpl;
import br.com.correntista.dao.DonoDao;
import br.com.correntista.dao.DonoDaoImpl;
import br.com.correntista.dao.GatoDao;
import br.com.correntista.dao.GatoDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.entidade.Comportamento;
import br.com.correntista.entidade.Dono;
import br.com.correntista.entidade.Gato;
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

/**
 *
 * @author Lucas Z
 */
@ManagedBean(name="gatoC")
@ViewScoped
public class GatoControle {
    
    private Gato gato;
    private GatoDao gatoDao;
    
    private Comportamento comportamento;
    private Dono dono;
    private Session sessao;
    private List<Gato> gatos;
    private List<SelectItem> comboComportamentos;
    private List<SelectItem> comboDonos;
    private DataModel<Gato> modelGatos;
    private int aba;
    
    public GatoControle() {
        gatoDao = new GatoDaoImpl();
    }
    
    public void pesquisarPorNome() {
        sessao = HibernateUtil.abrirSessao();
        try {
            gatos = gatoDao.pesquisarPorNome(gato.getNome(), sessao);
            modelGatos = new ListDataModel<>(gatos);
            gato.setNome(null);
            aba = 0;
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
     public void carregarComboComportamento(){
        sessao = HibernateUtil.abrirSessao();
        ComportamentoDao comportamentoDao = new ComportamentoDaoImpl();
        try {
            List<Comportamento> comportamentos = comportamentoDao.pesquisarTodo(sessao);
            comboComportamentos = new ArrayList<>();
            for (Comportamento comp : comportamentos) {
                comboComportamentos.add(new SelectItem(comp.getId(), comp.getTipo()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox comportamento " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
     
     public void carregarComboDonos(){
        sessao = HibernateUtil.abrirSessao();
        DonoDao donoDao = new DonoDaoImpl();
        try {
            List<Dono> donos = donoDao.pesquisarTodo(sessao);
            comboDonos = new ArrayList<>();
            for (Dono don : donos) {
                comboDonos.add(new SelectItem(don.getId(), don.getNome()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox dono " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void onTabChange(TabChangeEvent event) {
        //event.getTab().getTitle()
        if(event.getTab().getTitle().equals("Novo")) {
            carregarComboComportamento();
            carregarComboDonos();
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }
    
    public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
            gato.setComportamento(comportamento);
            gato.setDono(dono);
            //gato.getDono().getAnimais().add(gato); //TODO verificar esta linha
            gatoDao.salvarOuAlterar(gato, sessao);
            gato = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com sucesso", null));
            modelGatos = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar", ""));
        } finally {
            sessao.close();
        }
    }
    
    public void excluir(){
        gato = modelGatos.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            gatoDao.excluir(gato, sessao);
            gato = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluido com sucesso", null));
            modelGatos = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir", ""));
        } finally {
            sessao.close();
        }
        
    }
    
    public void prepararAlterar(){
        gato = modelGatos.getRowData();
        comportamento = gato.getComportamento();
        dono = gato.getDono();
        aba = 1;
    }
    
    // getters e setters

    public Gato getGato() {
        if(gato == null) {
            gato = new Gato();
        }
        return gato;
    }

    public void setGato(Gato gato) {
        this.gato = gato;
    }

    public Comportamento getComportamento() {
        if(comportamento == null) {
            comportamento = new Comportamento();
        }
        return comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public Dono getDono() {
        if(dono == null) {
            dono = new Dono();
        }
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }
    
    

    public DataModel<Gato> getModelGatos() {
        return modelGatos;
    }

    public int getAba() {
        return aba;
    }

    public List<SelectItem> getComboComportamentos() {
        return comboComportamentos;
    }

    public List<SelectItem> getComboDonos() {
        return comboDonos;
    }
    
}
