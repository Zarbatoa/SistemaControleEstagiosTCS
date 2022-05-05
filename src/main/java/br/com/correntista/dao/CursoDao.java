package br.com.correntista.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.correntista.entidade.Curso;

public interface CursoDao extends BaseDao<Curso, Long> {

	List<Curso> pesquisarTodos(Session sessao) throws HibernateException;
	
}
