package com.louishoughton.irrigator.job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
public class ExecutionDaoImpl implements ExecutionDao {

    private SessionFactory sessionFactory;


    @Autowired
    public ExecutionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Execution execution) {
        execution.setDateRun(new Date());
        sessionFactory.getCurrentSession().save(execution);
    }

    @Transactional
    public Execution get(int id) {
        return (Execution) sessionFactory.getCurrentSession().get(Execution.class, id);
    }

    @Override
    @Transactional
    public List<Execution> get(List<Integer> ids) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Execution.class)
                .add(Restrictions.in("id", ids))
                .list();
    }

    @Override
    @Transactional
    public List<Execution> list() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Execution.class)
                .addOrder(Order.desc("dateRun"))
                .list();
    }


    @Override
    @Transactional
    public List<Execution> list(int from, int to) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Execution.class)
                .setFirstResult(from)
                .setMaxResults(to - from)
                .addOrder(Order.desc("dateRun"))
                .list();
    }
}
