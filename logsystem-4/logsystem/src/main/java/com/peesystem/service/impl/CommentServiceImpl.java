package com.peesystem.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.entity.pojo.ExUserLog;
import com.peesystem.entity.pojo.ExUserLogComment;
import com.peesystem.mapper.ExUserLogCommentMapper;
import com.peesystem.mapper.ExUserMapper;
import com.peesystem.service.CommentService;

import tk.mybatis.mapper.entity.Example;

@Service
public class CommentServiceImpl implements CommentService{
	
	
	
	@Autowired
	private  ExUserLogCommentMapper exUserLogCommentMapper;
	@Autowired 
	private ExUserMapper exUserMapper;
	/**
	 * 得到评论列表
	 */
	public PageDto<ExUserLogComment> getCommentList(ExUserLogComment exUserLogComment,
			PageDto<ExUserLogComment> pageDto) {
		PageRowBounds pageRowBounds = new PageRowBounds(pageDto.getPageNum(), pageDto.getPageSize());
		List<ExUserLogComment> data = exUserLogCommentMapper.selectBySelective(exUserLogComment, pageRowBounds);
		pageDto.setTotal(pageRowBounds.getTotal());
		pageDto.setResult(data);
		return pageDto;
	}

	/**
	 * 保存评论
	 */
	public int saveComment(ExUserLogComment exUserLogComment) {
		if(StringUtils.isNotEmpty(exUserLogComment.getId())){
			return exUserLogCommentMapper.updateByPrimaryKeySelective(exUserLogComment);
		}else{
			exUserLogComment.setCreateTime(new Date());
			exUserLogCommentMapper.insertSelective(exUserLogComment);
			return 1;
		    
		}
	}

	/**
	 * 删除评论
	 */
	public int deleteComment(ExUserLogComment exUserLogComment) {
		exUserLogCommentMapper.deleteByPrimaryKey(exUserLogComment.getId());
		return 1;
	}

	
	
	/**
	 * 得到评论列表
	 */
	public List<ExUserLogComment> getAllCommentByLogId(ExUserLogComment exUserLogComment) {
		Example example=new Example(ExUserLogComment.class);
		example.and().andEqualTo("logId", exUserLogComment.getLogId());
		example.setOrderByClause("create_time asc");
		List<ExUserLogComment> list=exUserLogCommentMapper.selectByExample(example);
		for(ExUserLogComment eulc:list){
			ExUser exUser=exUserMapper.selectByPrimaryKey(eulc.getUserId());
			eulc.setUserName(exUser.getNick());
		}
		return list;
	}

}
