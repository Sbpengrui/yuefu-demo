package com.sofn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sofn.dao.IMenuDao;
import com.sofn.entity.Menu;
import com.sofn.service.IMenuService;
import com.sofn.util.TreeModel;
import com.sofn.util.TreeNode;

@Service("menuService")
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuDao menuDao;

	@Override
	public int insert(Menu entity) throws Exception {
		return 0;
	}

	@Override
	public int update(Menu entity) throws Exception {
		return 0;
	}

	@Override
	public int delete(Menu entity) throws Exception {
		return 0;
	}

	@Override
	public Menu select(Menu entity) {
		return null;
	}

	@Override
	public List<Menu> menuList() {
		return menuDao.menuList();
	}

	@Override
	public List<Menu> getMenuList(String level, String parentId) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("level", level);
		queryMap.put("parentId", parentId);
		return menuDao.getMenuList(queryMap);
	}

	@Override
	public List<Menu> getMenuTree(Integer id) {
		ArrayList<TreeModel> menuTree = new ArrayList<>();
		ArrayList<TreeModel> root = new ArrayList<>();
		List<Menu> menuList = menuDao.getMenuTree(id);
//
//		for (Menu menu : menuList) {
//			TreeModel treeNode = new TreeModel();
//			treeNode.setId(menu.getId());
//			treeNode.setText(menu.getMenuName());
//			if(menu.getLevel() == 1 && menu.getId() == 1){
//				treeNode.setState("open");
//			}else{
//				treeNode.setState("closed");
//			}
//			treeNode.setLevel_id(menu.getLevel());
//			treeNode.setParent_id(menu.getParentId());
//			menuTree.add(treeNode);
//		}
//		root = TreeNode.getTree(menuTree);
//		return root;
		return menuList;
	}

	@Override
	public ArrayList<TreeModel> getMenuTreeGird(Integer id) {
		ArrayList<TreeModel> menuTree = new ArrayList<>();
		ArrayList<TreeModel> root = new ArrayList<>();
		List<Menu> menuList = menuDao.getMenuTree(id);

		for (Menu menu : menuList) {
			TreeModel treeNode = new TreeModel();
			treeNode.setId(menu.getId());
			treeNode.setText(menu.getMenuName());
			if(menu.getLevel() == 1 && menu.getId() == 1){
				treeNode.setState("open");
			}else{
				treeNode.setState("closed");
			}
			treeNode.setLevel_id(menu.getLevel());
			treeNode.setParent_id(menu.getParentId());
			menuTree.add(treeNode);
		}
		root = TreeNode.getTree(menuTree);
		return root;
	}

}
