package br.com.correntista.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.correntista.entidade.InstituicaoEnsino;

public interface InstituicaoEnsinoDao extends BaseDao<InstituicaoEnsino, Long> {

	List<InstituicaoEnsino> pesquisarTodos(Session sessao) throws HibernateException;
	
}
