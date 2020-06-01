package com.peesystem.controller.visitor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peesystem.common.utils.SessionUtil;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.service.LoginService;


/**
 * 登录控制器
 * @author 555
 *
 */
@Controller
@RequestMapping("web/visitor")
public class LoginController {

	@Autowired
	private LoginService igLoginService;

	
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin(ModelAndView view,HttpSession session){
		view.setViewName("visitor/login");
		return view;
		
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/userLogin")
	public @ResponseBody Map<String,Object> userLogin(@RequestBody ExUser cspUser, HttpSession session) {
		Map<String,Object>  map=new HashMap<String,Object>();
		ExUser igUser1=SessionUtil.getUserDetail(session);
		if(igUser1!=null){
			map.put("flag", true);
			return map;
		}
		try{
			 map=igLoginService.userLogin(cspUser,session);
		}catch(Exception e){
			map.put("flag", false);
			map.put("resultMsg", "系统异常");
		}
		
		return map;
	}
	
	



}
