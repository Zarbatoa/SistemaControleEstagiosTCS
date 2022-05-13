package br.com.correntista.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.correntista.entidade.UnidadeConcedente;

public interface UnidadeConcedenteDao extends BaseDao<UnidadeConcedente, Long>{

	List<UnidadeConcedente> pesquisarTodos(Session sessao) throws HibernateException;
	List<UnidadeConcedente> pesquisarPorRazaoSocial(String nome, Session sessao) throws HibernateException;
	
	
}
