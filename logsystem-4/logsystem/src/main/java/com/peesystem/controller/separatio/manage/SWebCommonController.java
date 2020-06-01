package com.peesystem.controller.separatio.manage;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 注销控制器
 * @author 555
 *
 */
@Controller
@RequestMapping("separatio/manage")
public class SWebCommonController {
	

	
	/**
	 * 点注销
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView invalidateSession(HttpSession session,ModelAndView view) {
		session.invalidate();
		view.setViewName("visitor/login");
		return view;
	}
	
	

    

}
