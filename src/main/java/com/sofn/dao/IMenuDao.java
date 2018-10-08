package com.sofn.dao;


import java.util.List;
import java.util.Map;

import com.sofn.entity.Menu;

public interface IMenuDao extends BaseDao<Menu> {
	List<Menu> menuList();
	
	List<Menu> getMenuList(Map<String,Object> map);
	
	List<Menu> getMenuTree(Integer id);
}