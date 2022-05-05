package br.com.correntista.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.correntista.entidade.Curso;

public class CursoDaoImpl extends BaseDaoImpl<Curso, Long> implements CursoDao, Serializable{

	@Override
	public Curso pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Curso) sessao.get(Curso.class, id);
	}

	@Override
	public List<Curso> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Curso");
        return consulta.list();
	}

}
