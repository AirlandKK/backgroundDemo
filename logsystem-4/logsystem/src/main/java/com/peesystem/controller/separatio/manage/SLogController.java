package com.peesystem.controller.separatio.manage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 日志管理
 * @author 555
 *
 */
@Api(description = "日志管理")
@Controller
@RequestMapping("separatio/manage")
public class SLogController extends SBaseController{
	
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
	@ApiOperation(value = "得到日志列表" ,  notes="得到日志列表")
	@RequestMapping(value = "/getExUserLogList",method=RequestMethod.GET)
	@ResponseBody
	public ResultVo getExUserLogList(ExUser exu,ExUserLog exUserLog,PageDto<ExUserLog> cpsPageDto,HttpSession session){
		  try{
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
	@ApiOperation(value = "查看所有的日志" ,  notes="查看所有的日志")
	@RequestMapping(value = "/getAllExUserLogList",method=RequestMethod.GET)
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
	@ApiOperation(value = "查看日志内容" ,  notes="查看日志内容")
	@RequestMapping(value = "/findLogById",method=RequestMethod.GET)
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
	@ApiOperation(value = "删除日志" ,  notes="删除日志")
	@RequestMapping(value = "/deleteLog",method=RequestMethod.POST)
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
	@ApiOperation(value = "上传文件" ,  notes="上传文件")
	@RequestMapping(value = "/uploadFile",method=RequestMethod.POST)
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
	@ApiOperation(value = "保存日志" ,  notes="保存日志")
	@RequestMapping(value = "/saveLog",method=RequestMethod.POST)
	@ResponseBody
	public ResultVo saveLog(ExUserLog exUserLog,@RequestParam(value="workFile",required=false) MultipartFile workFile,HttpServletRequest  request,HttpSession session){
		   try{
			 
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
