package br.com.correntista.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class GeradorTabelaBanco {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbestagios_PU");
        emf.close();
    }
}
