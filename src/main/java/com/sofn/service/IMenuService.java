package com.sofn.service;

import java.util.ArrayList;
import java.util.List;

import com.sofn.entity.Menu;
import com.sofn.util.TreeModel;

public interface IMenuService extends BaseService<Menu>{
	
	List<Menu> menuList();
	
	List<Menu> getMenuList(String level,String parentId);
	
	 ArrayList<TreeModel> getMenuTreeGird(Integer id);
	 
	 List<Menu> getMenuTree(Integer id);

}
