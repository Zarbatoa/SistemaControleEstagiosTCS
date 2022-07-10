package br.com.correntista.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.correntista.entidade.Estagio;

public interface EstagioDao extends BaseDao<Estagio, Long>{
	
	List<Estagio> pesquisarTodos(Session sessao) throws HibernateException;
	
	List<Estagio> pesquisarAtivos(Session sessao) throws HibernateException;
	List<Estagio> pesquisarAtivosPorEstagiario(String nome, Session sessao) throws HibernateException;
	List<Estagio> pesquisarAtivos(Long idInstituicao, Session sessao) throws HibernateException;
	List<Estagio> pesquisarAtivosPorEstagiario(Long idInstituicao,String nome, Session sessao) throws HibernateException;
	
	
	List<Estagio> pesquisarInativos(Session sessao) throws HibernateException;
	List<Estagio> pesquisarInativosPorEstagiario(String nome, Session sessao) throws HibernateException;
	List<Estagio> pesquisarInativos(Long idInstituicao, Session sessao) throws HibernateException;
	List<Estagio> pesquisarInativosPorEstagiario(Long idInstituicao, String nome, Session sessao) throws HibernateException;
	
}
