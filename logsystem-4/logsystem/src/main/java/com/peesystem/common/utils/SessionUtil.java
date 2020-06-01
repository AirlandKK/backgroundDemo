package com.peesystem.common.utils;

import javax.servlet.http.HttpSession;

import com.peesystem.entity.pojo.ExUser;



public class SessionUtil {
	
	
	

	 public static void setUserDetail(HttpSession session,ExUser cspUser) {
		 session.setAttribute("exUser",cspUser);
	 }
	 
	 
	
    public static ExUser getUserDetail(HttpSession session) {
			return  (ExUser) session.getAttribute("exUser");
		 }

}
