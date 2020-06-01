package com.peesystem.controller.manage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.peesystem.common.utils.ResultUtil;
import com.peesystem.common.utils.SessionUtil;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.entity.pojo.ExUserCollect;
import com.peesystem.entity.vo.ResultVo;
import com.peesystem.service.CollectService;


/**
 * 收藏控制器
 * @author 555
 *
 */
@Controller
@RequestMapping("web/manage")
public class CollectController {
	
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
	@RequestMapping(value = "/getCollectList")
	@ResponseBody
	public ResultVo getReplyList(ExUserCollect exUserCollect,PageDto<ExUserCollect> cpsPageDto,HttpSession session){
		  try{
			    ExUser exu=SessionUtil.getUserDetail(session);
			    exUserCollect.setUserId(exu.getUserId());
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
	@RequestMapping(value = "/deleteCollect")
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
	@RequestMapping(value = "/saveCollect")
	@ResponseBody
	public ResultVo saveCollect(ExUserCollect exUserCollect,HttpSession session){
		   try{
			    ExUser exu=SessionUtil.getUserDetail(session);
			    exUserCollect.setUserId(exu.getUserId());
		     	int result=collectService.saveCollect(exUserCollect);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}

}
