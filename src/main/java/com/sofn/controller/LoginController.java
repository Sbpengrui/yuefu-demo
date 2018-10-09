package com.sofn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sofn.entity.User;
import com.sofn.service.IUserService;
import com.sofn.util.HttpCode;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Autowired
	private IUserService userService;
	@Autowired
	private HttpSession session;

	@RequestMapping("/logout")
	@ResponseBody
	public Object logout() {
		session.removeAttribute("user");
		session.removeAttribute("valicode");
		session.removeAttribute("msg");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("HttpCode", HttpCode.OK);
		map.put("msg", HttpCode.OK.msg());
		return map;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(User model, String valicode, HttpSession session) {
		String msg = "";
		
		if (StringUtils.isEmpty(valicode) || model == null) {
			msg = "用户名密码错误";
			session.setAttribute("msg", msg);
			return setModelAndView("redirect:/login.jsp");
		}
		String code = (String) session.getAttribute("valicode");
		if (!valicode.toLowerCase().equals(code.toLowerCase())) {
			msg = "验证码错误";
			session.setAttribute("msg", msg);
			return setModelAndView("redirect:/login.jsp");
		}

		User user = userService.getUserByName(model.getUserName());

		if (user == null || !user.getPassword().equals(model.getPassword())) {
			msg = "用户名密码错误";
			session.setAttribute("msg", msg);
			return setModelAndView("redirect:/login.jsp");
		} else {
			session.setAttribute("user", user);
			return setModelAndView("model");
		}
	}
}
