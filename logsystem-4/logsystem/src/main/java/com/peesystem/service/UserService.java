package com.peesystem.service;

import java.util.Map;

import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;

public interface UserService {
	
	


	int deleteUser(ExUser igUser);


	int saveUser(ExUser exUser);


	PageDto<ExUser> getUserList(ExUser exUser, PageDto<ExUser> cpsPageDto);


	int blackUser(ExUser exUser);


	ExUser getUserInfo(ExUser exUser);


	int saveAccount(ExUser exUser);


	Map<String, Object> comparePassWord(ExUser exUser);






}
