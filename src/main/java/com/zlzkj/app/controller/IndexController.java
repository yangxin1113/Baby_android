package com.zlzkj.app.controller;

import com.zlzkj.app.service.UserService;
import com.zlzkj.app.utils.AjaxResult.AjaxResult;
import com.zlzkj.app.utils.AjaxResult.ResultCode;
import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页控制器
 */

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private UserService adminService;
	
	@RequestMapping(value={"/Login"})
	public void login(Model model, HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("zyx"+ username+password);
		if(adminService.isExit(username)){
			List<Row> userinfo = adminService.login(username, password);
			if(userinfo !=null && userinfo.size()>0){
				ajaxReturn(response, AjaxResult.getOK(ResultCode.SUCCESS, "登陆成功", userinfo.get(0)));
			}else {
				ajaxReturn(response, AjaxResult.getError(ResultCode.InfoException,"密码错误", ""));

			}
		}else {
			ajaxReturn(response, AjaxResult.getError(ResultCode.InfoException,"用户不存在", ""));

		}


	}


	@RequestMapping(value={"/OtherLogin"})
	public void register(Model model, HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	}
}
