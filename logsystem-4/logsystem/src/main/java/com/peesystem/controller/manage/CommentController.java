package com.peesystem.controller.manage;

import java.util.List;

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
import com.peesystem.entity.pojo.ExUserLog;
import com.peesystem.entity.pojo.ExUserLogComment;
import com.peesystem.entity.vo.ResultVo;
import com.peesystem.service.CommentService;


/**
 * 评论控制器
 * @author 555
 *
 */
@Controller
@RequestMapping("web/manage")
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	
	/**
	 * 到评论页面
	 * @return
	 */
	@RequestMapping(value = "/toComment")
	public ModelAndView toComment(HttpSession session,ModelAndView view) {
		view.setViewName("manage/comment");
		return view;
	}
	
	
	/**
	 * 得到评论列表(得到我的评论)
	 * @param exUser
	 * @param cpsPageDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getCommentList")
	@ResponseBody
	public ResultVo getCommentList(ExUserLogComment exUserLogComment,PageDto<ExUserLogComment> cpsPageDto,HttpSession session){
		  try{
			    ExUser exu=SessionUtil.getUserDetail(session);
			    exUserLogComment.setUserId(exu.getUserId());
			    PageDto<ExUserLogComment> pageDto =  commentService.getCommentList(exUserLogComment, cpsPageDto);
			    return  ResultUtil.success(pageDto);
			    }catch(Exception e){
				e.printStackTrace();
				  return  ResultUtil.exception();
			   }
	}
	
	
	/**
	 * 得到评论列表
	 * @param exUserLogComment
	 * @return
	 */
	@RequestMapping(value = "/getAllCommentByLogId")
	@ResponseBody
	public ResultVo getAllCommentByLogId(ExUserLogComment exUserLogComment){
		
		     try{
		     	 List<ExUserLogComment> list=commentService.getAllCommentByLogId(exUserLogComment);
		     	 return  ResultUtil.success(list);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
		
	}
	
	/**
	 * 删除评论
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/deleteComment")
	@ResponseBody
	public ResultVo deleteComment(ExUserLogComment exUserLogComment){
		   try{
		     	int result=commentService.deleteComment(exUserLogComment);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}

	
	/**
	 * 保存评论
	 * @param exUser
	 * @return
	 */
	@RequestMapping(value = "/saveComment")
	@ResponseBody
	public ResultVo saveComment(ExUserLogComment exUserLogComment,HttpSession session){
		   try{
			    ExUser exu=SessionUtil.getUserDetail(session);
			    exUserLogComment.setUserId(exu.getUserId());
		     	int result=commentService.saveComment(exUserLogComment);
		     	 return  ResultUtil.success(result);
			    }catch(Exception e){
				e.printStackTrace();
				return  ResultUtil.exception();
			   }
		
	}

}
