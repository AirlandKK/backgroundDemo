package com.peesystem.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageRowBounds;
import com.peesystem.common.utils.MD5Util;
import com.peesystem.common.utils.SessionUtil;
import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUser;
import com.peesystem.mapper.ExUserMapper;
import com.peesystem.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private  ExUserMapper  exUserMapper;

	
	
	/**
	 * 删除用户
	 */
	public int deleteUser(ExUser igUser) {
		return exUserMapper.deleteByPrimaryKey(igUser.getUserId());
	}

	
	
	/**
	 * 保存用户
	 */
	public int saveUser(ExUser exUser) {
		if(StringUtils.isNotEmpty(exUser.getUserId())){
			return exUserMapper.updateByPrimaryKeySelective(exUser);
		}else{
			ExUser trUser=exUserMapper.selecUserByUserName(exUser);
			if(trUser!=null){
				return -1;
			}
			exUser.setUserPassword(MD5Util.encode(exUser.getUserPassword()));
			exUser.setCreateTime(new Date());
			exUserMapper.insertSelective(exUser);
			return 1;
		    
		}
	}

	
	
	/**
	 * 管理员查询所有的用户
	 */
	public PageDto<ExUser> getUserList(ExUser exUser, PageDto<ExUser> pageDto) {
		PageRowBounds pageRowBounds = new PageRowBounds(pageDto.getPageNum(), pageDto.getPageSize());
		List<ExUser> data = exUserMapper.selectBySelective(exUser, pageRowBounds);
		pageDto.setTotal(pageRowBounds.getTotal());
		pageDto.setResult(data);
		return pageDto;
	}



	/**
	 * 拉黑用户
	 */
	public int blackUser(ExUser exUser) {
		exUser.setIsEnable("0");
		return  exUserMapper.updateByPrimaryKeySelective(exUser);
	}



	/**
	 * 得到用户信息
	 */
	public ExUser getUserInfo(ExUser exUser) {
		return exUserMapper.selectByPrimaryKey(exUser.getUserId());
	}



	@Override
	public int saveAccount(ExUser exUser) {
		exUser.setIsEnable("1");
		if(StringUtils.isNotEmpty(exUser.getUserId())){
			return exUserMapper.updateByPrimaryKeySelective(exUser);
			
		}else{
			ExUser trUser=exUserMapper.selecUserByUserName(exUser);
			if(trUser!=null){
				return -1;
			}
			exUser.setUserPassword(MD5Util.encode(exUser.getUserPassword()));
			exUser.setCreateTime(new Date());
			exUserMapper.insertSelective(exUser);
		    return 1;
		  
		}
	}



	/**
	 * 比较密码
	 */
	public Map<String, Object> comparePassWord(ExUser exUser) {
		Map<String, Object>  map=new HashMap<String,Object>();
		exUser.setUserPassword(MD5Util.encode(exUser.getUserPassword()));
	    ExUser cspUser1=exUserMapper.selecUser(exUser);
		if(cspUser1!=null){
			if(StringUtils.equals(cspUser1.getIsEnable(), "1")){
				map.put("flag", true);
				map.put("user", cspUser1);
			}else{
				map.put("flag", false);
				map.put("resultMsg", "已封号，请联系管理员");
				
			}
			
		}else{
			map.put("flag", false);
			map.put("resultMsg", "账号或密码输入错误");
		}

	  return map;
	}



}
