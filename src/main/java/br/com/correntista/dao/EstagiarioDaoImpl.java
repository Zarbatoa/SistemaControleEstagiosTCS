package br.com.correntista.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.correntista.entidade.Estagiario;

public class EstagiarioDaoImpl extends BaseDaoImpl<Estagiario, Long> implements EstagiarioDao, Serializable{

	@Override
	public Estagiario pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Estagiario) sessao.get(Estagiario.class, id);
	}

	@Override
	public List<Estagiario> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagiario");
        return consulta.list();
	}

	@Override
	public List<Estagiario> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Estagiario e where e.nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
	}

}
