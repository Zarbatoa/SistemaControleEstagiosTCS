package br.com.correntista.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.correntista.entidade.Estagio;

public interface EstagioDao extends BaseDao<Estagio, Long>{
	
	List<Estagio> pesquisarTodos(Session sessao) throws HibernateException;
	
	List<Estagio> pesquisarAtivos(Session sessao) throws HibernateException;
	List<Estagio> pesquisarInativos(Session sessao) throws HibernateException;
}
