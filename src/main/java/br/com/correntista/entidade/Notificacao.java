package br.com.correntista.entidade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Notificacao {

	Estagio estagio;
	TipoNotificacao tipoNotificacao;
	

	
	public Notificacao() {
		
	}
	
	public Notificacao(Estagio estagio, TipoNotificacao tipoNotificacao) {
		super();
		this.estagio = estagio;
		this.tipoNotificacao = tipoNotificacao;
	}

	
	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public TipoNotificacao getTipoNotificacao() {
		return tipoNotificacao;
	}

	public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}

	public static List<TipoNotificacao> extrairTiposNotificacoes(Estagio estagio) {
		List<TipoNotificacao> tipos = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		long millis = System.currentTimeMillis();
		Date dataAtual = new Date(millis);
		cal.setTime(dataAtual);
		int mesAtual = cal.get(Calendar.MONTH);
		int anoAtual = cal.get(Calendar.YEAR);
		cal.add(Calendar.MONTH, 1);
		int proximoMes = cal.get(Calendar.MONTH);
		int anoProximo = cal.get(Calendar.YEAR);
		
		cal.setTime(estagio.getDataTermino());
		int mesTermino = cal.get(Calendar.MONTH);
		int anoTermino = cal.get(Calendar.YEAR);

		cal.setTime(estagio.getDataInicio());
		cal.add(Calendar.MONTH, 3);
		int mesRQ1 = cal.get(Calendar.MONTH);
		int anoRQ1 = cal.get(Calendar.YEAR);
		cal.setTime(estagio.getDataInicio());
		cal.add(Calendar.MONTH, 3+4);
		int mesRQ2 = cal.get(Calendar.MONTH);
		int anoRQ2 = cal.get(Calendar.YEAR);
		cal.setTime(estagio.getDataInicio());
		cal.add(Calendar.MONTH, 3+8);
		int mesRQ3 = cal.get(Calendar.MONTH);
		int anoRQ3 = cal.get(Calendar.YEAR);
		cal.setTime(estagio.getDataInicio());
		cal.add(Calendar.MONTH, 3+12);
		int mesRQ4 = cal.get(Calendar.MONTH);
		int anoRQ4 = cal.get(Calendar.YEAR);
		cal.setTime(estagio.getDataInicio());
		cal.add(Calendar.MONTH, 3+16);
		int mesRQ5 = cal.get(Calendar.MONTH);
		int anoRQ5 = cal.get(Calendar.YEAR);
		cal.setTime(estagio.getDataInicio());
		cal.add(Calendar.MONTH, 3+20);
		int mesRQ6 = cal.get(Calendar.MONTH);
		int anoRQ6 = cal.get(Calendar.YEAR);
		
		// Ver se o mês seguinte ou o mês atual é o mês de término
		if((mesAtual == mesTermino && anoAtual == anoTermino) || (proximoMes == mesTermino && anoProximo == anoTermino)) {
			tipos.add(TipoNotificacao.DESLIGAMENTO);
		}
		
		// Ver se tem algum relatório quadrimestral neste mês
		//   e se tiver, ver qual a numeração do relatório
		if(mesAtual == mesRQ1 && anoAtual == anoRQ1) {
			tipos.add(TipoNotificacao.RQUADRIMESTAL1);
		} else if(mesAtual == mesRQ2 && anoAtual == anoRQ2) {
			tipos.add(TipoNotificacao.RQUADRIMESTAL2);
		} else if(mesAtual == mesRQ3 && anoAtual == anoRQ3) {
			tipos.add(TipoNotificacao.RQUADRIMESTAL3);
		} else if(mesAtual == mesRQ4 && anoAtual == anoRQ4) {
			tipos.add(TipoNotificacao.RQUADRIMESTAL4);
		} else if(mesAtual == mesRQ5 && anoAtual == anoRQ5) {
			tipos.add(TipoNotificacao.RQUADRIMESTAL5);
		} else if(mesAtual == mesRQ6 && anoAtual == anoRQ6) {
			tipos.add(TipoNotificacao.RQUADRIMESTAL6);
		}
		
		
		return tipos;
	}
	
	public static List<Notificacao> gerarNotificacoes(List<Estagio> estagios) {
		if(estagios == null) {
			return null;
		}
		
		List<Notificacao> notificacoes = new ArrayList<>();
		
		for(Estagio estagio: estagios) {
			List<TipoNotificacao> notificacoesEstagio = extrairTiposNotificacoes(estagio);
			for(TipoNotificacao tipo : notificacoesEstagio) {
				Notificacao novaNotificacao = new Notificacao();
				novaNotificacao.setEstagio(estagio);
				novaNotificacao.setTipoNotificacao(tipo);
				notificacoes.add(novaNotificacao);
			}
		}
		return notificacoes;
	}
	
}
