package com.sofn.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Default;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sofn.entity.Menu;
import com.sofn.entity.User;
import com.sofn.service.IMenuService;
import com.sofn.util.TreeModel;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Autowired
	private IMenuService menuService;
	@Autowired
	private HttpSession session;

	@RequestMapping("/meneList")
	public ModelAndView menuList() throws IOException {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return new ModelAndView("redirect:/login.jsp");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuList", menuService.menuList());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("menuList");
		return setModelAndWriteHtml(mav, map);
	}

	@RequestMapping("/getMenuList")
	@ResponseBody
	public Object getMenuList(String level, String parentId) {

		return menuService.getMenuList(level, parentId);
	}

	@RequestMapping("/getMenuTree")
	@ResponseBody
	public List<Menu> getMenuTree(Integer id) {
		return menuService.getMenuTree(id);
	}
	
	@RequestMapping("/getMenuTreeGird")
	@ResponseBody
	public ArrayList<TreeModel> getMenuTreeGird(Integer id) {
		return menuService.getMenuTreeGird(id);
	}
}
