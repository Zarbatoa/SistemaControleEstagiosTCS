package br.com.correntista.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.correntista.entidade.Usuario;

public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Long> implements UsuarioDao, Serializable{

	@Override
	public Usuario pesquisarPorId(Long id, Session sessao) throws HibernateException {
		return (Usuario) sessao.get(Usuario.class, id);
	}

	@Override
	public List<Usuario> pesquisarTodos(Session sessao) throws HibernateException {
		Query consulta = sessao.createQuery("from Usuario");
        return consulta.list();
	}

}
