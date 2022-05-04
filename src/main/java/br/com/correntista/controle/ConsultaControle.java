package br.com.correntista.controle;

import br.com.correntista.dao.CachorroDao;
import br.com.correntista.dao.CachorroDaoImpl;
import br.com.correntista.dao.ComportamentoDao;
import br.com.correntista.dao.ComportamentoDaoImpl;
import br.com.correntista.dao.ConsultaDao;
import br.com.correntista.dao.ConsultaDaoImpl;
import br.com.correntista.dao.DonoDao;
import br.com.correntista.dao.DonoDaoImpl;
import br.com.correntista.dao.GatoDao;
import br.com.correntista.dao.GatoDaoImpl;
import br.com.correntista.dao.HibernateUtil;
import br.com.correntista.entidade.Animal;
import br.com.correntista.entidade.Cachorro;
import br.com.correntista.entidade.Comportamento;
import br.com.correntista.entidade.Consulta;
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
@ManagedBean(name="consultaC")
@ViewScoped
public class ConsultaControle {
    
    private Consulta consulta;
    private ConsultaDao consultaDao;
    
    private Animal animal;
    private Session sessao;
    private List<Consulta> consultas;
    private List<SelectItem> comboAnimais;
    private DataModel<Consulta> modelConsultas;
    private int aba;
    
    public ConsultaControle() {
        consultaDao = new ConsultaDaoImpl();
    }
    
    public void pesquisarPorData() {
        sessao = HibernateUtil.abrirSessao();
        try {
            consultas = consultaDao.pesquisarPorData(consulta.getDia(), sessao);
            modelConsultas = new ListDataModel<>(consultas);
            consulta.setDia(null);
            aba = 0;
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void carregarComboAnimais(){
        sessao = HibernateUtil.abrirSessao();
        GatoDao gatoDao = new GatoDaoImpl();
        CachorroDao cachorroDao = new CachorroDaoImpl();
        try {
            List<Gato> gatos = gatoDao.pesquisarTodo(sessao);
            List<Cachorro> cachorros = cachorroDao.pesquisarTodo(sessao);
            comboAnimais = new ArrayList<>();
            for (Gato gat : gatos) {
                comboAnimais.add(new SelectItem(gat.getId(), gat.getNome()));
            }
            for (Cachorro cachor : cachorros) {
                comboAnimais.add(new SelectItem(cachor.getId(), cachor.getNome()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox animal " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void onTabChange(TabChangeEvent event) {
        //event.getTab().getTitle()
        if(event.getTab().getTitle().equals("Novo")) {
            carregarComboAnimais();
        }
    }

    public void onTabClose(TabCloseEvent event) {
    }
    
    public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
            consulta.setAnimal(animal);
            consultaDao.salvarOuAlterar(consulta, sessao);
            consulta = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com sucesso", null));
            modelConsultas = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar", ""));
            System.out.println("br.com.correntista.controle.ConsultaControle.salvar()");
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        } finally {
            sessao.close();
        }
    }
    
    public void excluir(){
        consulta = modelConsultas.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            consultaDao.excluir(consulta, sessao);
            consulta = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluido com sucesso", null));
            modelConsultas = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao excluir", ""));
        } finally {
            sessao.close();
        }
        
    }
    
    public void prepararAlterar(){
        consulta = modelConsultas.getRowData();
        animal = consulta.getAnimal();
        aba = 1;
    }
    
    // getters e setters

    public Consulta getConsulta() {
        if(consulta == null) {
            consulta = new Consulta();
        }
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Animal getAnimal() {
        if(animal == null) {
            animal = new Animal();
        }
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    

    public DataModel<Consulta> getModelConsultas() {
        return modelConsultas;
    }

    public int getAba() {
        return aba;
    }

    public List<SelectItem> getComboAnimais() {
        return comboAnimais;
    }
    
}
