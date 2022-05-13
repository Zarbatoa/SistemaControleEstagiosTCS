package br.com.correntista.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.correntista.entidade.Estagiario;
import br.com.correntista.entidade.UnidadeConcedente;

public class UnidadeConcedenteDaoImpl extends BaseDaoImpl<UnidadeConcedente, Long> implements UnidadeConcedenteDao, Serializable{

	@Override
	public UnidadeConcedente pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (UnidadeConcedente) sessao.get(UnidadeConcedente.class, id);
	}

	@Override
	public List<UnidadeConcedente> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from UnidadeConcedente");
        return consulta.list();
	}

	@Override
	public List<UnidadeConcedente> pesquisarPorRazaoSocial(String razaoSocial, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from UnidadeConcedente u where u.razaoSocial like :razaoSocial");
        consulta.setParameter("razaoSocial", razaoSocial + "%");
        return consulta.list();
	}

}
