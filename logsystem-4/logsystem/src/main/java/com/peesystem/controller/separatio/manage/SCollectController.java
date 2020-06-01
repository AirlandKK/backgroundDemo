package com.peesystem.controller.separatio.manage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peesystem.common.utils.ResultUtil;
import com.peesystem.common.utils.SessionUtil;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.entity.pojo.ExUserCollect;
import com.peesystem.entity.vo.ResultVo;
import com.peesystem.service.CollectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 收藏控制器
 * @author 555
 *
 */
@Api(description = "收藏管理")
@Controller
@RequestMapping("separatio/manage")
public class SCollectController {
	
	@Autowired
	private  CollectService collectService;
	
	
	/**
	 * 到收藏页面
	 * @return
	 */
	@RequestMapping(value = "/toCollect")
	public ModelAndView toReply(HttpSession session,ModelAndView view) {
		view.setViewName("manage/collect");
		return view;
	}
	
	/**
	 * 得到收藏列表(得到我的收藏)
	 * @param exUser
	 * @param cpsPageDto
	 * @param session
	 * @return
	 */
	@ApiOperation(value = "得到收藏列表(得到我的收藏)" ,  notes="得到收藏列表(得到我的收藏)")
	@RequestMapping(value = "/getCollectList",method=RequestMethod.GET)
	@ResponseBody
	public ResultVo getReplyList(ExUserCollect exUserCollect,PageDto<ExUserCollect> cpsPageDto,HttpSession session){
		  try{
			    PageDto<ExUserCollect> pageDto =  collectService.getCollectList(exUserCollect, cpsPageDto);
			    return  ResultUtil.success(pageDto);
			    }catch(Exception e){
				e.printStackTrace();
				  return  ResultUtil.exception();
			   }
	}
	
	/**
	 * 删除收藏
	 * @param exUser
	 * @return
	 */
	@ApiOperation(value = " 删除收藏" ,  notes=" 删除收藏")
	@RequestMapping(value = "/deleteCollect",method=RequestMethod.POST)
	@ResponseBody
	public ResultVo deleteCollect(ExUserCollect exUserCollect){
		   try{
		     	int result=collectService.deleteCollect(exUserCollect);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}

	
	/**
	 * 保存收藏
	 * @param exUser
	 * @return
	 */
	@ApiOperation(value = "保存收藏" ,  notes="保存收藏")
	@RequestMapping(value = "/saveCollect",method=RequestMethod.POST)
	@ResponseBody
	public ResultVo saveCollect(ExUserCollect exUserCollect,HttpSession session){
		   try{
		     	int result=collectService.saveCollect(exUserCollect);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}

}
