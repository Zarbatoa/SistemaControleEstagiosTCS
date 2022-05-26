package br.com.correntista.dao;

import br.com.correntista.entidade.*;

import java.util.Properties;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Silvio
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration cfg = new Configuration();
            // legado, usado pela API
            cfg.addAnnotatedClass(Endereco.class);
            
            // estagios:
            cfg.addAnnotatedClass(Usuario.class);
            cfg.addAnnotatedClass(InstituicaoEnsino.class);
            cfg.addAnnotatedClass(Curso.class);
            cfg.addAnnotatedClass(Estagiario.class);
            cfg.addAnnotatedClass(UnidadeConcedente.class);
            cfg.addAnnotatedClass(Estagio.class);

            cfg.configure("/META-INF/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abrirSessao() {
        return sessionFactory.openSession();
    }
}
