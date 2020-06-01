package com.peesystem.controller.manage;

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
@RequestMapping("web/manage")
public class WebCommonController {
	

	
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
