package com.peesystem.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUserCollect;
import com.peesystem.mapper.ExUserCollectMapper;
import com.peesystem.service.CollectService;

@Service
public class CollectServiceImpl implements CollectService{
	
	@Autowired
	private  ExUserCollectMapper exUserCollectMapper;

	
	
	/**
	 * 得到收藏的回复列表
	 */
	public PageDto<ExUserCollect> getCollectList(ExUserCollect exUserCollect, PageDto<ExUserCollect> pageDto) {
		PageRowBounds pageRowBounds = new PageRowBounds(pageDto.getPageNum(), pageDto.getPageSize());
		List<ExUserCollect> data = exUserCollectMapper.selectBySelective(exUserCollect, pageRowBounds);
		pageDto.setTotal(pageRowBounds.getTotal());
		pageDto.setResult(data);
		return pageDto;
	}

	
	
	/**
	 * 删除回复
	 */
	public int deleteCollect(ExUserCollect exUserCollect) {
		exUserCollectMapper.deleteByPrimaryKey(exUserCollect.getId());
		return 1;
	}

	
	
	/**
	 * 保存收藏
	 */
	public int saveCollect(ExUserCollect exUserCollect) {
		if(StringUtils.isNotEmpty(exUserCollect.getId())){
			return exUserCollectMapper.updateByPrimaryKeySelective(exUserCollect);
		}else{
			exUserCollect.setCreateTime(new Date());
			exUserCollectMapper.insertSelective(exUserCollect);
			return 1;
		    
		}
	}

}
