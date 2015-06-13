package com.codesys.idak.dao;

import com.codesys.idak.entity.SystemUser;

public interface SystemUserDAO {

	public SystemUser getSystemUserByUserName(String userName) throws Exception;
	
	public SystemUser getSystemUserByUserId(String userId)throws Exception;
}
