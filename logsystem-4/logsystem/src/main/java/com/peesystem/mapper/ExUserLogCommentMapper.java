package com.peesystem.mapper;

import java.util.List;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.common.base.MyBaseMapper;
import com.peesystem.entity.pojo.ExUserLogComment;

public interface ExUserLogCommentMapper extends MyBaseMapper<ExUserLogComment> {

	List<ExUserLogComment> selectBySelective(ExUserLogComment exUserLogComment, PageRowBounds pageRowBounds);
}