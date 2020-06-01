package com.peesystem.mapper;

import java.util.List;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.common.base.MyBaseMapper;
import com.peesystem.entity.pojo.ExUserLog;

public interface ExUserLogMapper extends MyBaseMapper<ExUserLog> {

	List<ExUserLog> selectBySelective(ExUserLog exUserLog, PageRowBounds pageRowBounds);
}