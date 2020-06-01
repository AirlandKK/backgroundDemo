package com.peesystem.controller.manage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.peesystem.common.utils.ResultUtil;
import com.peesystem.common.utils.SessionUtil;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.entity.pojo.ExUserLog;
import com.peesystem.entity.vo.ResultVo;
import com.peesystem.service.LogService;


/**
 * 日志管理
 * @author 555
 *
 */
@Controller
@RequestMapping("web/manage")
public class LogController extends BaseController{
	
	@Autowired
	private  LogService logService;
	
	
	/**
	 * 到日志页面
	 * @return
	 */
	@RequestMapping(value = "/toLog")
	public ModelAndView toLog(HttpSession session,ModelAndView view) {
		view.setViewName("manage/log");
		return view;
	}
	
	/**
	 * 到管理员的日志列表
	 * @param session
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/toAdminLog")
	public ModelAndView toAdminLog(HttpSession session,ModelAndView view) {
		view.setViewName("manage/adminLog");
		return view;
	}
	
	/**
	 * 查看所有的日志
	 * @param session
	 * @param view
	 * @return
	 */
	@RequestMapping(value = "/toAllLog")
	public ModelAndView toAllLog(HttpSession session,ModelAndView view) {
		view.setViewName("manage/allLog");
		return view;
	}
	
	
	/**
	 * 得到日志列表
	 * @param exUser
	 * @param cpsPageDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getExUserLogList")
	@ResponseBody
	public ResultVo getExUserLogList(ExUserLog exUserLog,PageDto<ExUserLog> cpsPageDto,HttpSession session){
		  try{
			     ExUser exu=SessionUtil.getUserDetail(session);
			     //如果不是管理员才通过用户id去查我的日志
			     if(!StringUtils.equals("3",exu.getUserType())){
			    	 exUserLog.setUserId(exu.getUserId());
			     }
			    PageDto<ExUserLog> pageDto =  logService.getExUserLogList(exUserLog, cpsPageDto);
			    return  ResultUtil.success(pageDto);
			    }catch(Exception e){
				e.printStackTrace();
				  return  ResultUtil.exception();
			   }
	}
	
	
	/**
	 * 查看所有的日志
	 * @param exUserLog
	 * @param cpsPageDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllExUserLogList")
	@ResponseBody
	public ResultVo getAllExUserLogList(ExUserLog exUserLog,PageDto<ExUserLog> cpsPageDto,HttpSession session){
		  try{
			    
			    PageDto<ExUserLog> pageDto =  logService.getExUserLogList(exUserLog, cpsPageDto);
			    return  ResultUtil.success(pageDto);
			    }catch(Exception e){
				e.printStackTrace();
				  return  ResultUtil.exception();
			   }
	}
	
	
	
	/**
	 * 查看日志内容
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/findLogById")
	@ResponseBody
	public ResultVo findLogById(ExUserLog exUserLog){
		   try{
			     ExUserLog eul=logService.findLogById(exUserLog);
			   
		     	 return  ResultUtil.success(eul.getContentString());
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	
	
	/**
	 * 删除日志
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/deleteLog")
	@ResponseBody
	public ResultVo deleteLog(ExUserLog exUserLog){
		   try{
		     	int result=logService.deleteLog(exUserLog);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}

	
	/**
	 *上传文件
	 * @param exUserLog
	 * @param workFile
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public ResultVo uploadFile(ExUserLog exUserLog,@RequestParam(value="workFile",required=false) MultipartFile workFile,HttpServletRequest  request){
		   try{
			   
			     String path=this.getPath(workFile,request);
			     String projectpath = request.getContextPath();
			 	  String basePath = request.getScheme() + "://" + request.getServerName() 
			 	                   + ":" + request.getServerPort() + projectpath + "/";
		     	 return  ResultUtil.success(basePath+path);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	
	/**
	 * 保存日志
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/saveLog")
	@ResponseBody
	public ResultVo saveLog(ExUserLog exUserLog,@RequestParam(value="workFile",required=false) MultipartFile workFile,HttpServletRequest  request,HttpSession session){
		   try{
			    ExUser exUse= SessionUtil.getUserDetail(session);
			    exUserLog.setUserId(exUse.getUserId());
			    if(StringUtils.isEmpty(exUserLog.getId())){
			    	exUserLog.setId(null);
			    }
		     	int result=logService.saveLog(exUserLog);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}
	

}
