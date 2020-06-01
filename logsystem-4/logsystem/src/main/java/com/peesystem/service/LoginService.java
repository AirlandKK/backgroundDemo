package com.peesystem.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.peesystem.entity.pojo.ExUser;


public interface LoginService {

	Map<String, Object> userLogin(ExUser igUser, HttpSession session);

	Map<String, Object> sperAtorUserLogin(ExUser cspUser);


}
