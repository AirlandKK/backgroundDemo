package com.peesystem.controller.separatio.manage;

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
@Api(description = "用户管理")
@Controller
@RequestMapping("separatio/manage")
public class SUserController extends SBaseController{
	
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
	@ApiOperation(value = "得到用戶列表" ,  notes="得到用戶列表")
	@RequestMapping(value = "/getExUserList",method=RequestMethod.GET)
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
	@ApiOperation(value = "保存用戶" ,  notes="保存用戶")
	@RequestMapping(value = "/saveUser",method=RequestMethod.POST)
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
	@ApiOperation(value = "删除用戶" ,  notes="删除用戶")
	@RequestMapping(value = "/deleteUser",method=RequestMethod.POST)
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
	@ApiOperation(value = "得到用户信息" ,  notes="得到用户信息")
	@RequestMapping(value = "/getUserInfo",method=RequestMethod.GET,consumes= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultVo getUserInfo(ExUser exUser){
		   try{
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
	@ApiOperation(value = "得到用户头像" ,  notes="得到用户头像")
	@RequestMapping(value = "/getUserInfoByUserId",method=RequestMethod.GET)
	@ResponseBody
	public ResultVo getUserInfoByUserId( ExUser exUser){
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
	@ApiOperation(value = "拉黑用户" ,  notes="拉黑用户")
	@RequestMapping(value = "/blackUser",method=RequestMethod.POST)
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
