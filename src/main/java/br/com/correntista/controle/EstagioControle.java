package br.com.correntista.controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
import br.com.correntista.dao.InstituicaoEnsinoDao;
import br.com.correntista.dao.InstituicaoEnsinoDaoImpl;
import br.com.correntista.dao.UnidadeConcedenteDao;
import br.com.correntista.dao.UnidadeConcedenteDaoImpl;
import br.com.correntista.entidade.Estagiario;
import br.com.correntista.entidade.Estagio;
import br.com.correntista.entidade.InstituicaoEnsino;
import br.com.correntista.entidade.StatusEstagio;
import br.com.correntista.entidade.TipoInatividade;
import br.com.correntista.entidade.UnidadeConcedente;
import br.com.correntista.util.Utils;

/**
*
* @author Lucas Z
*/
@ManagedBean(name="estagioC")
@SessionScoped
public class EstagioControle {

	// Atributos para edicao de estagios novos ou nao
	private Estagio estagio;
	private EstagioDao estagioDao;
	
	private Estagiario estagiario;
    private List<SelectItem> comboEstagiarios;

	private UnidadeConcedente unidadeConcedente;
    private List<SelectItem> comboUnidadesConcedentes;

    // Atributos para as duas combobox de instituicao_ensino de estagio
    private InstituicaoEnsino instituicaoEnsino;
    private InstituicaoEnsino instituicaoEnsinoVinculada;
    private List<SelectItem> comboInstituicoesEnsino;
    
    // Atributos para as telas de pesquisa
    private Session sessao;
    private List<Estagio> estagios;
    private DataModel<Estagio> modelEstagios;
    
    private List<Estagio> estagiosAtivos;
    private DataModel<Estagio> modelEstagiosAtivos;
    
    private List<Estagio> estagiosInativos;
    private DataModel<Estagio> modelEstagiosInativos;
    
    private int aba;

	// Combo Boxes fixos
    private List<SelectItem> comboModalidade;
    private List<SelectItem> comboTurno;
    private List<SelectItem> comboTipoEstagio;
    
    
    // Infos para a inativação
    private TipoInatividade tipoInatividade;
    private List<TipoInatividade> tiposInativ;
    private int rowIndexAtivos = -1;
    
    // Atributo para a pesquisa em todas as abas
    private InstituicaoEnsino instituicaoFiltro;
    
    
    public EstagioControle() {
    	estagioDao = new EstagioDaoImpl();
    	comboModalidade = new ArrayList<>();
    	comboTurno = new ArrayList<>();
    	comboTipoEstagio = new ArrayList<>();
    	tiposInativ = new ArrayList<>();
    	
    	comboModalidade.add(new SelectItem("Ensino Médio Regular"));
    	comboModalidade.add(new SelectItem("Formação Inicial e Continuada"));
    	comboModalidade.add(new SelectItem("Educação Técnica"));
    	comboModalidade.add(new SelectItem("Educação Superior"));
    	
    	comboTurno.add(new SelectItem("Matutino"));
    	comboTurno.add(new SelectItem("Vespertino"));
    	comboTurno.add(new SelectItem("Noturno"));
    	comboTurno.add(new SelectItem("Integral"));
    	
    	comboTipoEstagio.add(new SelectItem("Obrigatório"));
    	comboTipoEstagio.add(new SelectItem("Não Obrigatório"));
    	
    	tiposInativ.add(TipoInatividade.ESTAGIARIO_NAO_EFETIVADO);
    	tiposInativ.add(TipoInatividade.ESTAGIARIO_EFETIVADO);
    	tiposInativ.add(TipoInatividade.DESAPROVACAO_EMPRESA);
    	tiposInativ.add(TipoInatividade.DESAPROVACAO_ESTAGIARIO);
    	tiposInativ.add(TipoInatividade.DESAPROVACAO_AMBOS);
    	tiposInativ.add(TipoInatividade.TRANCAMENTO_MATRICULA);
    	
    	carregarComboInstituicoesEnsino();
    }
    
    public String destruirBean() {
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("estagioC");
    	return "estagio.xhtml";
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
    
    public void pesquisarAtivosTodos() {
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
    
    public void pesquisarAtivosPorEstagiario() {
        sessao = HibernateUtil.abrirSessao();
        try {
        	estagiosAtivos = estagioDao.pesquisarAtivosPorEstagiario(estagiario.getNome(), sessao);
            modelEstagiosAtivos = new ListDataModel<>(estagiosAtivos);
            estagiario.setNome(null);
            aba = 0;
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void pesquisarAtivos() {
    	if(estagiario == null || estagiario.getNome() == null || "".equals(estagiario.getNome().trim())) {
    		pesquisarAtivosTodos();
    	} else {
    		pesquisarAtivosPorEstagiario();
    	}
	}    
    
    
    public void pesquisarInativosTodos() {
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
    
    public void pesquisarInativosPorEstagiario() {
    	sessao = HibernateUtil.abrirSessao();
        try {
        	estagiosInativos = estagioDao.pesquisarInativosPorEstagiario(estagiario.getNome(), sessao);
            modelEstagiosInativos = new ListDataModel<>(estagiosInativos);
            estagiario.setNome(null);
            aba = 0;
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void pesquisarInativos() {
    	if(estagiario == null || estagiario.getNome() == null || "".equals(estagiario.getNome().trim())) {
    		pesquisarInativosTodos();
    	} else {
    		pesquisarInativosPorEstagiario();
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
    
    public void carregarComboInstituicoesEnsino() {
		sessao = HibernateUtil.abrirSessao();
        InstituicaoEnsinoDao instituicaoEnsinoDao = new InstituicaoEnsinoDaoImpl();
        try {
            List<InstituicaoEnsino> instituicoesEnsino = instituicaoEnsinoDao.pesquisarTodos(sessao);
            comboInstituicoesEnsino = new ArrayList<>();
            for (InstituicaoEnsino instituicao: instituicoesEnsino) {
            	comboInstituicoesEnsino.add(new SelectItem(instituicao.getId(), instituicao.getRazaoSocial()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar combobox instituições ensino " + e.getMessage());
        } finally {
            sessao.close();
        }
	}
    
    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getTitle() != null && event.getTab().getTitle().equals("Novo")) {
        	carregarComboEstagiarios();
        	carregarComboUnidadesConcedentes();
        	carregarComboInstituicoesEnsino();
        }// else {
        	estagio = null;
        	estagiario = null;
        	unidadeConcedente = null;
        	instituicaoEnsino = null;
        	instituicaoEnsinoVinculada = null;
        //}
    }

    public void onTabClose(TabCloseEvent event) {
    }
    
    public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
    	if(instituicaoFiltro.getId() == null) {
    		System.out.println("subjectSelectionChanged -> null...");
    	} else {
    		System.out.println("subjectSelectionChanged -> " + instituicaoFiltro.getId());
    	}
    }
    
    public void salvar(){
        sessao = HibernateUtil.abrirSessao();
        try {
        	estagio.setUnidadeConcedente(unidadeConcedente);
        	estagio.setEstagiario(estagiario);
        	estagio.setInstituicaoEnsino(instituicaoEnsino);
        	estagio.setInstituicaoEnsinoVinculada(instituicaoEnsinoVinculada);
		    estagioDao.salvarOuAlterar(estagio, sessao);
		    estagio = null;
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		            "Salvo com sucesso", null));
		    modelEstagiosAtivos = null;
		    estagiario = null;
		    unidadeConcedente = null;
		    instituicaoEnsino = null;
		    instituicaoEnsinoVinculada = null;
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
		carregarComboInstituicoesEnsino();
		estagiario = estagio.getEstagiario();
		unidadeConcedente = estagio.getUnidadeConcedente();
		instituicaoEnsino = estagio.getInstituicaoEnsino();
		instituicaoEnsinoVinculada = estagio.getInstituicaoEnsinoVinculada();
		aba = 2;
	}
    
    public void prepararVisualizarInativo() {
		estagio = modelEstagiosInativos.getRowData();
		carregarComboEstagiarios();
		carregarComboUnidadesConcedentes();
		carregarComboInstituicoesEnsino();
		estagiario = estagio.getEstagiario();
		unidadeConcedente = estagio.getUnidadeConcedente();
		instituicaoEnsino = estagio.getInstituicaoEnsino();
		instituicaoEnsinoVinculada = estagio.getInstituicaoEnsinoVinculada();
		aba = 2;
	}
    
    public void prepararAbaDocumentos() {
    	estagio = modelEstagiosAtivos.getRowData();
    	aba = 3;
    }
    
    
    public String direcinarRelatorioDesligamento() {
    	return "relatorioDesligamento.htm";
    }
    
    public String traduzirEnumTipoInatividade(TipoInatividade tipo) {
    	return Utils.mapearTipoInatividade(tipo);
    }
    
    public String formatarCpf(String cpf) {
    	return Utils.formatarCpf(cpf);
    }
    
    public String formatarCnpj(String cnpj) {
    	return Utils.formatarCnpj(cnpj);
    }
    
    public String formatarData(Date data) {
    	if(data == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		String dataFormatted = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);
		return dataFormatted;
    }
    
    public String extrairDiaDoMesExtenso(Date data) {
    	Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return Utils.mapearDiaDoMesExtenso(cal.get(Calendar.DAY_OF_MONTH));
    }
    
    public String extrairMesExtenso(Date data) {
    	Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return  Utils.mapearMesExtenso(cal.get(Calendar.MONTH)) ;
    }
    
    public String extrairAno(Date data) {
    	Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.YEAR) + "";
    }

    public void inativarEstagio() {
    	sessao = HibernateUtil.abrirSessao();
        try {
        	estagio = estagiosAtivos.get(rowIndexAtivos);
        	estagio.setTipoInativacao(tipoInatividade);
        	estagio.setStatus(StatusEstagio.INATIVO);
        	estagioDao.salvarOuAlterar(estagio, sessao);
		    estagio = null;
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		            "Inativado com sucesso", null));
		    modelEstagiosAtivos = null;
		    estagiario = null;
		    unidadeConcedente = null;
		    instituicaoEnsino = null;
		    instituicaoEnsinoVinculada = null;
        } catch (HibernateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao inativar", ""));
        } finally {
            sessao.close();
        }
    }
    
    public void definirRowAtivos() {
    	rowIndexAtivos = modelEstagiosAtivos.getRowIndex();
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

	
	public InstituicaoEnsino getInstituicaoEnsino() {
		if(instituicaoEnsino == null) {
			instituicaoEnsino = new InstituicaoEnsino();
		}
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(InstituicaoEnsino instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public InstituicaoEnsino getInstituicaoEnsinoVinculada() {
		if(instituicaoEnsinoVinculada == null) {
			instituicaoEnsinoVinculada = new InstituicaoEnsino();
		}
		return instituicaoEnsinoVinculada;
	}

	public void setInstituicaoEnsinoVinculada(InstituicaoEnsino instituicaoEnsinoVinculada) {
		this.instituicaoEnsinoVinculada = instituicaoEnsinoVinculada;
	}

	public List<SelectItem> getComboInstituicoesEnsino() {
		return comboInstituicoesEnsino;
	}

	public List<SelectItem> getComboModalidade() {
		return comboModalidade;
	}

	public List<SelectItem> getComboTurno() {
		return comboTurno;
	}

	public List<SelectItem> getComboTipoEstagio() {
		return comboTipoEstagio;
	}

	public TipoInatividade getTipoInatividade() {
		return tipoInatividade;
	}

	public void setTipoInatividade(TipoInatividade tipoInatividade) {
		this.tipoInatividade = tipoInatividade;
	}

	public List<TipoInatividade> getTiposInativ() {
		return tiposInativ;
	}

	public int getRowIndexAtivos() {
		return rowIndexAtivos;
	}

	public void setRowIndexAtivos(int rowIndexAtivos) {
		this.rowIndexAtivos = rowIndexAtivos;
	}

	public InstituicaoEnsino getInstituicaoFiltro() {
		if(instituicaoFiltro == null) {
			instituicaoFiltro = new InstituicaoEnsino();
		}
		return instituicaoFiltro;
	}

	public void setInstituicaoFiltro(InstituicaoEnsino instituicaoFiltro) {
		this.instituicaoFiltro = instituicaoFiltro;
	}
	
}
