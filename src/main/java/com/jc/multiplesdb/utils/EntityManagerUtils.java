package com.jc.multiplesdb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class EntityManagerUtils {

    @Autowired
    @Qualifier("icpEntityManagerFactory")
    private EntityManager icpDatabase;

    @Autowired
    @Qualifier("grbEntityManagerFactory")
    private EntityManager grbDatabase;

    @Autowired
    @Qualifier("grcEntityManagerFactory")
    private EntityManager grcDatabase;


    public EntityManager getDb(String db){
        if(db.equals("ICP"))
            return icpDatabase;
        if(db.equals("GRB"))
            return grbDatabase;
        if(db.equals("GRC"))
            return grcDatabase;

        return icpDatabase;
    }

    public JpaRepositoryFactory repositoryFactory(String db){
        return new JpaRepositoryFactory(getDb(db));
    }

}
