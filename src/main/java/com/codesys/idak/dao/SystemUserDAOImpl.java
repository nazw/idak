package com.codesys.idak.dao;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codesys.idak.entity.SystemUser;
import com.codesys.idak.util.AccountStatus;

@Repository("systemUserDAO")
public class SystemUserDAOImpl implements SystemUserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override	
	public SystemUser getSystemUserByUserName(String userName) throws Exception {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SystemUser.class,"systemUser");
        criteria.add(Restrictions.eq("userName", userName));
        criteria.add(Restrictions.eq("accountStatus", AccountStatus.ACTIVE));
        SystemUser systemUser=(SystemUser) criteria.uniqueResult();
        if(systemUser != null){
        	Hibernate.initialize(systemUser.getUserRole());        	
        }
        return systemUser;
	}


	@Override
	public SystemUser getSystemUserByUserId(String userId) throws Exception {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SystemUser.class);
		criteria.add(Restrictions.eq("userId", userId));
		return (SystemUser) criteria.uniqueResult();
	}

}

