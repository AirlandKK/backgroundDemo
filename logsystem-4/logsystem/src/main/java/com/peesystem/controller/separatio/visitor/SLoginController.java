package com.peesystem.controller.separatio.visitor;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 登录控制器
 * @author 555
 *
 */
@Api(description = "登录接口")
@Controller
@RequestMapping("separatio/visitor")
public class SLoginController {

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
	@ApiOperation(value = "用户登录" ,  notes="用户登录")
	@RequestMapping(value = "/userLogin")
	public @ResponseBody Map<String,Object> userLogin(@RequestBody ExUser cspUser) {
		Map<String,Object>  map=new HashMap<String,Object>();
		try{
			 map=igLoginService.sperAtorUserLogin(cspUser);
		}catch(Exception e){
			map.put("flag", false);
			map.put("resultMsg", "系统异常");
		}
		
		return map;
	}
	
	



}
