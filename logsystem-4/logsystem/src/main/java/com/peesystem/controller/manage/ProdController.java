package com.peesystem.controller.manage;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.peesystem.common.utils.ResultUtil;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.entity.vo.ResultVo;
import com.peesystem.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 *
 */
@Api(description = "补充接口")
@RequestMapping("/prod")
@RestController
public class ProdController {
	
	@Autowired
	private  UserService userService;
	/**
	 * 得到用户头像
	 * @param exUser
	 * @param session
	 * @return
	 */
	@ApiOperation(value = " 得到用户头像" ,  notes=" 得到用户头像")
	@RequestMapping(value = "/getUserInfoByUserId",method=RequestMethod.GET)
	public ResultVo getUserInfoByUserId(@RequestBody ExUser exUser){
		   try{
			     ExUser result=userService.getUserInfo(exUser);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	
	
	/**
	 * 前端密码加密比对数据库
	 * @param exUser
	 * @return
	 */
	@ApiOperation(value = "前端密码加密比对数据库" ,  notes="前端密码加密比对数据库")
	@RequestMapping(value = "/comparePassWord",method=RequestMethod.POST)
	public ResultVo comparePassWord(@RequestBody ExUser exUser){
		   try{
			     Map<String,Object> map=userService.comparePassWord(exUser);
		     	 return  ResultUtil.success(map);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
 
}