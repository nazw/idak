package com.codesys.idak.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codesys.idak.dao.SystemUserDAO;
import com.codesys.idak.entity.CommonDomainProperty;
import com.codesys.idak.entity.SystemUser;


@Service("systemUserService")
@Transactional(propagation = Propagation.SUPPORTS , readOnly = true)
public class SystemUserServiceImpl implements SystemUserService {
	
	@Autowired
	private SystemUserDAO systemUserDAO;
	
	@Autowired
	private CommonDomainProperty cdp;

	@Override
	@Transactional(propagation = Propagation.REQUIRED , readOnly = true)
	public SystemUser getSystemUserByUserName(String userName) throws Exception {
		
		return systemUserDAO.getSystemUserByUserName(userName);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED , readOnly = true)
	public SystemUser getSystemUserByUserId(String userId) throws Exception {
		return systemUserDAO.getSystemUserByUserId(userId);
	}

}

