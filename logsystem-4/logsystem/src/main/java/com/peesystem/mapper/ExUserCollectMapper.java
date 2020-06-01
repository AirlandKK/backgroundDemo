package com.peesystem.mapper;

import java.util.List;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.common.base.MyBaseMapper;
import com.peesystem.entity.pojo.ExUserCollect;

public interface ExUserCollectMapper extends MyBaseMapper<ExUserCollect> {

	List<ExUserCollect> selectBySelective(ExUserCollect exUserReply, PageRowBounds pageRowBounds);
}