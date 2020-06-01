package com.peesystem.controller.separatio.visitor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peesystem.entity.pojo.ExUser;
import com.peesystem.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 注册控制器
 * @author 555
 *
 */
@Api(description = "注册接口")
@Controller
@RequestMapping("separatio/visitor")
public class SRegisterController {

		
		@Autowired
		private  UserService userService;
	

	
	
	/**
	 * 保存账号
	 * @param session
	 * @param view
	 * @return
	 */
	@ApiOperation(value = "用户注册" ,  notes="用户注册")
	@RequestMapping(value = "/saveAccount")
	@ResponseBody
	public Map<String,Object>   saveAccount(@RequestBody ExUser exUser) {
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
