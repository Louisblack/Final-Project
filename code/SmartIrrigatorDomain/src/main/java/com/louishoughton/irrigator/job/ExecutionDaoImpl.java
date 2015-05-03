package com.louishoughton.irrigator.job;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class ExecutionDaoImpl {

    private SessionFactory sessionFactory;

    @Autowired
    public ExecutionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Execution execution) {
        sessionFactory.getCurrentSession().save(execution);
    }

    @Transactional
    public Execution get(int id) {
        return (Execution) sessionFactory.getCurrentSession().get(Execution.class, id);
    }
}