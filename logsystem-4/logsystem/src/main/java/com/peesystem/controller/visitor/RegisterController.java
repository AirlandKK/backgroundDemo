package com.peesystem.controller.visitor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peesystem.entity.pojo.ExUser;
import com.peesystem.service.UserService;


/**
 * 登录控制器
 * @author 555
 *
 */
@Controller
@RequestMapping("web/visitor")
public class RegisterController {

		
		@Autowired
		private  UserService userService;
	

	
	
	/**
	 * 保存账号
	 * @param session
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/saveAccount")
	@ResponseBody
	public Map<String,Object>   saveAccount(ExUser exUser,ModelAndView view) {
		int result= userService.saveAccount(exUser);
		Map<String,Object> map=new HashMap<String,Object>();
		if(result==-1){
			map.put("errorMsg", "账号已存在");
			map.put("flag", "false");
			return map;
		}
		map.put("flag", "true");
		return map;
	}


}
