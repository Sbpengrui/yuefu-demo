package com.sofn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sofn.entity.User;
import com.sofn.entity.UserDetails;
import com.sofn.interceptor.Authorization;
import com.sofn.service.IUserService;
import com.sofn.util.HttpCode;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{

	@Autowired
	private IUserService userService;
	@Autowired
	private HttpSession session;

	public UserController() {
	}

	@RequestMapping("/view")
	public String view() {
		return "/login";
	}

	@RequestMapping("/indexview")
	public String index() {
		User user = (User) session.getAttribute("user");
		if (user != null)
			return "/index";
		else {
			return "/login";
		}
	}
	@RequestMapping("/logout")
	@ResponseBody
	public Object logout(){
		session.removeAttribute("user");
		session.removeAttribute("valicode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("HttpCode", HttpCode.OK);
		map.put("msg", HttpCode.OK.msg());
		return map;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User model, String valicode ,HttpSession session) {
		if(StringUtils.isEmpty(valicode) || model == null){
			return setModelAndView("redirect:/login.jsp");
		}
		String code = (String)session.getAttribute("valicode");
		if(!valicode.toLowerCase().equals(code.toLowerCase())){
			return setModelAndView("redirect:/login.jsp");
		}
		
		User user = userService.getUserByName(model.getUserName());
		
		
		if (user == null || !user.getPassword().equals(model.getPassword())) {
			return setModelAndView("redirect:/login.jsp");
		} else {
			session.setAttribute("user", user);
			return setModelAndView("model");
		}
	}

	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	@ResponseBody
	@Authorization
	public Object getUserList(String firstName, String lastName, int page,
			int rows, String order, String sort) {
		Map<String, Object> map = userService.list(firstName, lastName, page,
				rows, order, sort);
		return map;
	}

	@RequestMapping(value = "/getUserLoginList")
	@ResponseBody
	public Object getUserLoginList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", userService.getUserLoginList());
		return map;
	}

	@RequestMapping(value = "/saveOrUpdateUser")
	@ResponseBody
	public Object saveOrUpdateUser(@RequestBody UserDetails userDetails) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.saveOrUpdateUser(userDetails);
		map.put("success", flag);
		return map;
	}

	@RequestMapping(value = "/delectUsers")
	@ResponseBody
	public Object delectUsers(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.deleteUsers(ids);
		map.put("success", flag);
		return map;
	}

	@RequestMapping(value = "/listCount")
	@ResponseBody
	public Object listCount() {
		return userService.list(null);
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	@Authorization
	public Object list(HttpSession session, int page, int pageSize)
			throws IOException {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("offset", (page - 1) * pageSize);
		paramsMap.put("pageSize", pageSize);

		Map<String, Object> map = userService.list(paramsMap);
		session.setAttribute("userList", map.get("list"));
		session.setAttribute("count", map.get("count"));

		return map;
	}

}
