package br.com.correntista.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.correntista.entidade.Estagio;
import br.com.correntista.entidade.StatusEstagio;

public class EstagioDaoImpl extends BaseDaoImpl<Estagio, Long> implements EstagioDao, Serializable{

	@Override
	public Estagio pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Estagio) sessao.get(Estagio.class, id);
	}

	@Override
	public List<Estagio> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio");
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarAtivos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.status = :status");
        consulta.setParameter("status", StatusEstagio.ATIVO);
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarInativos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.status in (:status1, :status2)");
        consulta.setParameter("status1", StatusEstagio.INATIVO);
        consulta.setParameter("status2", StatusEstagio.RECINDIDO);
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarAtivosPorEstagiario(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.estagiario.nome like :nome AND e.status = :status");
        consulta.setParameter("nome", nome + "%");
        consulta.setParameter("status", StatusEstagio.ATIVO);
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarInativosPorEstagiario(String nome, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.estagiario.nome like :nome AND e.status in (:status1, :status2)");
        consulta.setParameter("nome", nome + "%");
        consulta.setParameter("status1", StatusEstagio.INATIVO);
        consulta.setParameter("status2", StatusEstagio.RECINDIDO);
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarAtivos(Long idInstituicao, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.status = :status AND e.instituicaoEnsino.id = :idInstituicao");
        consulta.setParameter("status", StatusEstagio.ATIVO);
        consulta.setParameter("idInstituicao", idInstituicao);
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarAtivosPorEstagiario(Long idInstituicao, String nome, Session sessao)
			throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.estagiario.nome like :nome AND e.status = :status AND e.instituicaoEnsino.id = :idInstituicao");
        consulta.setParameter("nome", nome + "%");
        consulta.setParameter("status", StatusEstagio.ATIVO);
        consulta.setParameter("idInstituicao", idInstituicao);
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarInativos(Long idInstituicao, Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.status in (:status1, :status2) AND e.instituicaoEnsino.id = :idInstituicao");
        consulta.setParameter("status1", StatusEstagio.INATIVO);
        consulta.setParameter("status2", StatusEstagio.RECINDIDO);
        consulta.setParameter("idInstituicao", idInstituicao);
        return consulta.list();
	}

	@Override
	public List<Estagio> pesquisarInativosPorEstagiario(Long idInstituicao, String nome, Session sessao)
			throws HibernateException {
		Query consulta = sessao.createQuery("from Estagio e where e.estagiario.nome like :nome AND e.status in (:status1, :status2) AND e.instituicaoEnsino.id = :idInstituicao");
        consulta.setParameter("nome", nome + "%");
        consulta.setParameter("status1", StatusEstagio.INATIVO);
        consulta.setParameter("status2", StatusEstagio.RECINDIDO);
        consulta.setParameter("idInstituicao", idInstituicao);
        return consulta.list();
	}

}
