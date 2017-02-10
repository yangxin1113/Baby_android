package com.zlzkj.app.controller;

import com.zlzkj.app.service.TagsService;
import com.zlzkj.app.service.UserService;
import com.zlzkj.app.utils.AjaxResult.AjaxResult;
import com.zlzkj.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页控制器
 */

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private UserService adminService;

	@Autowired
	private TagsService tagsService;

	@RequestMapping(value={"/Login"}, method = RequestMethod.POST)
	public void login(Model model, HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AjaxResult result = adminService.login(username, password);
		ajaxReturn(response, result);

	}
	@RequestMapping(value={"/register"})
	public void register(Model model, HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	}

	@RequestMapping(value={"/getTags"},method = RequestMethod.GET)
	public void getTags(Model model, HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");
		AjaxResult result = tagsService.getTags(userid);
		ajaxReturn(response, result);
	}

	@RequestMapping(value={"/updateTags"},method = RequestMethod.POST)
	public void updateTags(Model model, HttpServletRequest request, HttpServletResponse response) {
		String tags = request.getParameter("tags");
		String userid = request.getParameter("userid");
		AjaxResult result = tagsService.updateTags(userid,tags);
		//ajaxReturn(response, result);
	}
}
