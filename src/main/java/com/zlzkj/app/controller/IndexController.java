package com.zlzkj.app.controller;

import com.alibaba.fastjson.JSON;
import com.zlzkj.app.service.UserService;
import com.zlzkj.core.base.BaseController;
import com.zlzkj.core.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 */

@Controller
public class IndexController extends BaseController {
	
	@Autowired
	private UserService adminService;
	
	@RequestMapping(value={"/"})
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		
//		User test = new User();
//		test.setAddTime(Fn.time());
//		test.setLoginName("simon");
//		test.setLoginPass("123456");
//		test.setSex((byte) 1);
//		userService.save(test);
		
		//User user = userService.find();
		
		List<Row> userList = adminService.findBySQL();
		//model.addAttribute("userList",userList);
		render(response, JSON.toJSONString(userList),"json");
		return "second";
	}
	
	
	
}
