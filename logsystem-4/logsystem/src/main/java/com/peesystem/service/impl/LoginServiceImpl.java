package com.peesystem.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peesystem.common.utils.MD5Util;
import com.peesystem.common.utils.SessionUtil;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.mapper.ExUserMapper;
import com.peesystem.service.LoginService;




@Service
public class LoginServiceImpl implements LoginService{

	
	@Autowired
	private ExUserMapper userMapper;

	
	/**
	 * 用户登录
	 */
	public Map<String, Object> userLogin(ExUser cspUser,HttpSession session) {
		Map<String, Object>  map=new HashMap<String,Object>();
		    cspUser.setUserPassword(MD5Util.encode(cspUser.getUserPassword()));
		    ExUser cspUser1=userMapper.selecUser(cspUser);
			if(cspUser1!=null){
				if(StringUtils.equals(cspUser1.getIsEnable(), "1")){
					SessionUtil.setUserDetail(session, cspUser1);
					map.put("flag", true);
					map.put("user", cspUser1);
				}else{
					map.put("flag", false);
					map.put("resultMsg", "已封号，请联系管理员");
					
				}
				
			}else{
				map.put("flag", false);
				map.put("resultMsg", "账号或密码输入错误");
			}

		return map;
	}

	
	public static void main(String[] args) {
		System.out.println(MD5Util.encode("admin"));
		
	}


	/**
	 * 用户登录
	 */
	public Map<String, Object> sperAtorUserLogin(ExUser cspUser) {
		Map<String, Object>  map=new HashMap<String,Object>();
	    cspUser.setUserPassword(MD5Util.encode(cspUser.getUserPassword()));
	    ExUser cspUser1=userMapper.selecUser(cspUser);
		if(cspUser1!=null){
			if(StringUtils.equals(cspUser1.getIsEnable(), "1")){
				map.put("flag", true);
				map.put("user", cspUser1);
			}else{
				map.put("flag", false);
				map.put("resultMsg", "已封号，请联系管理员");
				
			}
			
		}else{
			map.put("flag", false);
			map.put("resultMsg", "账号或密码输入错误");
		}

	return map;
	}
	
	
	
    
	
}
