package br.com.correntista.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.correntista.entidade.Estagiario;

public interface EstagiarioDao extends BaseDao<Estagiario, Long> {

	List<Estagiario> pesquisarTodos(Session sessao) throws HibernateException;
	List<Estagiario> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
	
}
