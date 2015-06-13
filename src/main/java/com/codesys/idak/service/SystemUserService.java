package com.codesys.idak.service;

import com.codesys.idak.entity.SystemUser;

public interface SystemUserService {
	
	public SystemUser getSystemUserByUserName(String userName) throws Exception;	

	public SystemUser getSystemUserByUserId(String userId)throws Exception;
}
