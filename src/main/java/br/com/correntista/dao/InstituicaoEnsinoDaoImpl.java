package br.com.correntista.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.correntista.entidade.InstituicaoEnsino;

public class InstituicaoEnsinoDaoImpl extends BaseDaoImpl<InstituicaoEnsino, Long> implements InstituicaoEnsinoDao, Serializable {

	@Override
	public InstituicaoEnsino pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (InstituicaoEnsino) sessao.get(InstituicaoEnsino.class, id);
	}

	@Override
	public List<InstituicaoEnsino> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from InstituicaoEnsino");
        return consulta.list();
	}

	@Override
	public List<InstituicaoEnsino> pesquisarPorRazaoSocial(String razaoSocial, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from InstituicaoEnsino i where i.razaoSocial like :razaoSocial");
        consulta.setParameter("razaoSocial", "%" + razaoSocial + "%");
        return consulta.list();
	}

}
