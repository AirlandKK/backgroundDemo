package com.peesystem.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.common.utils.MD5Util;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.entity.pojo.ExUserLog;
import com.peesystem.mapper.ExUserLogMapper;
import com.peesystem.service.LogService;

@Service
public class LogServiceImpl implements LogService{
	
	@Autowired
	private ExUserLogMapper exUserLogMapper;

	/**
	 * 得到用户日志列表
	 */
	public PageDto<ExUserLog> getExUserLogList(ExUserLog exUserLog, PageDto<ExUserLog> pageDto) {
		PageRowBounds pageRowBounds = new PageRowBounds(pageDto.getPageNum(), pageDto.getPageSize());
		List<ExUserLog> data = exUserLogMapper.selectBySelective(exUserLog, pageRowBounds);
		pageDto.setTotal(pageRowBounds.getTotal());
		pageDto.setResult(data);
		return pageDto;
	}

	/**
	 * 查看日志详情
	 */
	public ExUserLog findLogById(ExUserLog exUserLog) {
		return exUserLogMapper.selectByPrimaryKey(exUserLog.getId());
	}

	
	
	/**
	 * 删除日志
	 */
	public int deleteLog(ExUserLog exUserLog) {
		return exUserLogMapper.deleteByPrimaryKey(exUserLog.getId());
	}

	
	
	/**
	 * 保存日志
	 */
	public int saveLog(ExUserLog exUserLog) {
		if(StringUtils.isNotEmpty(exUserLog.getId())){
			return exUserLogMapper.updateByPrimaryKeySelective(exUserLog);
		}else{
			exUserLog.setCreateTime(new Date());
			exUserLogMapper.insertSelective(exUserLog);
			return 1;
		    
		}
	}
	
	
	
	
	
	
	

}
