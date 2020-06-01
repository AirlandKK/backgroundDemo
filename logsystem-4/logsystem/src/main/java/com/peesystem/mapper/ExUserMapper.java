package com.peesystem.mapper;

import java.util.List;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.common.base.MyBaseMapper;
import com.peesystem.entity.pojo.ExUser;

public interface ExUserMapper extends MyBaseMapper<ExUser> {

	ExUser selecUser(ExUser cspUser);

	ExUser selecUserByUserName(ExUser exUser);

	List<ExUser> selectBySelective(ExUser exUser, PageRowBounds pageRowBounds);
}