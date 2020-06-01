package com.peesystem.controller.manage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.peesystem.common.utils.ResultUtil;
import com.peesystem.common.utils.SessionUtil;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.entity.vo.ResultVo;
import com.peesystem.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 用户管理
 * @author 555
 *
 */

@Controller
@RequestMapping("web/manage")
public class UserController extends BaseController{
	
	@Autowired
	private  UserService userService;
	
	@RequestMapping(value = "/toUser")
	public ModelAndView toUser(ModelAndView view,HttpSession session){
		view.setViewName("manage/user");
		return view;
		
	}
	
	@RequestMapping(value = "/toUserInfo")
	public ModelAndView toUserInfo(ModelAndView view,HttpSession session){
		view.setViewName("manage/userinfo");
		return view;
		
	}
	
	/**
	 * 得到用戶列表
	 * @param project
	 * @param cpsPageDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getExUserList")
	@ResponseBody
	public ResultVo getUserList(ExUser exUser,PageDto<ExUser> cpsPageDto,HttpSession session){
		  try{
			    PageDto<ExUser> pageDto =  userService.getUserList(exUser, cpsPageDto);
			    return  ResultUtil.success(pageDto);
			    }catch(Exception e){
				e.printStackTrace();
				  return  ResultUtil.exception();
			   }
	}
	
	
	/**
	 * 保存用戶
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/saveUser")
	@ResponseBody
	public ResultVo saveUser(ExUser exUser,@RequestParam(value="workFile",required=false) MultipartFile workFile,HttpServletRequest  request){
		   try{
			    String path=this.getPath(workFile,request);
			    exUser.setPhotoUrl(path);
			    exUser.setUserType("2");
		     	int result=userService.saveUser(exUser);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	
	
	
	
	/**
	 * 删除
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public ResultVo deleteUser(ExUser exUser){
		   try{
		     	int result=userService.deleteUser(exUser);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	
	/**
	 * 得到用户信息
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo",method=RequestMethod.GET)
	@ResponseBody
	public ResultVo getUserInfo(ExUser exUser,HttpSession session){
		   try{
			      ExUser eu= SessionUtil.getUserDetail(session);
			      exUser.setUserId(eu.getUserId());
			     ExUser result=userService.getUserInfo(exUser);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	/**
	 * 得到用户头像
	 * @param exUser
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getUserInfoByUserId",method=RequestMethod.GET)
	@ResponseBody
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
	 * 拉黑用户
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/blackUser")
	@ResponseBody
	public ResultVo blackUser(ExUser exUser){
		   try{
		     	int result=userService.blackUser(exUser);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	
	


}
