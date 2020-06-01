package com.peesystem.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.peesystem.common.utils.SessionUtil;
 
@Component
public class MyInterceptor implements HandlerInterceptor {

	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String currenturl;
		String paramStr = request.getQueryString();
		if ("".equals(paramStr) || paramStr == null) {
			currenturl = request.getRequestURL().toString();
		} else {
			currenturl = request.getRequestURL().append("?").append(paramStr)
					.toString();
		}
		if(currenturl.indexOf("web/manage") != -1){
			if(SessionUtil.getUserDetail(session) == null ){
				String path=getFullPath(request)+"web/visitor/toLogin";
				if(path.indexOf("443")!=-1){
					path=StringUtils.replace(path, "443", "80");
				}
				response.sendRedirect(path);
		        return false;
			}
			return true;
		}
		  return true; 
	}
	
	

	/**
	 * 得到地址全路径
	 * @param args
	 */
	public String getFullPath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
  
  


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 渲染视图之后被调�?. 释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}


}
