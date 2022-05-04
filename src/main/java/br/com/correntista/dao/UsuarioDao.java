package br.com.correntista.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.correntista.entidade.Usuario;

public interface UsuarioDao extends BaseDao<Usuario, Long> {

	List<Usuario> pesquisarTodos(Session sessao) throws HibernateException;
	
}
