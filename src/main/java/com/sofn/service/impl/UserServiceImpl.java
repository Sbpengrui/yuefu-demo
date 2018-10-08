package com.sofn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sofn.dao.IUserDao;
import com.sofn.dao.IUserDetailsDao;
import com.sofn.entity.User;
import com.sofn.entity.UserDetails;
import com.sofn.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserDetailsDao userDetailsDao;

	public UserServiceImpl() {
	}

	public User getUserById(Integer id) {
		return this.userDao.selectByPrimaryKey(id);
	}

	public int insert(User entity) throws Exception {
		return 0;
	}

	public int update(User entity) throws Exception {
		return 0;
	}

	public int delete(User entity) throws Exception {
		return 0;
	}

	public User select(User entity) {
		return null;
	}

	@Override
	public User getUserByName(String name) {
		return this.userDao.selectByName(name);
	}

	@Override
	public Map<String, Object> list(String firstName, String lastName,
			Integer pageNumber, Integer pageSize, String order, String sort) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Integer offset = (pageNumber - 1) * pageSize;
		paramsMap.put("offset", offset);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("order", order);
		paramsMap.put("sort", sort);
		paramsMap.put("firstName", firstName);
		paramsMap.put("lastName", lastName);

		map.put("rows", userDao.list(paramsMap));
		map.put("total", userDao.listCount(paramsMap));
		return map;
	}

	@Override
	public List<User> getUserLoginList() {

		return userDao.getUserLoginList();
	}

	@Override
	public boolean saveOrUpdateUser(UserDetails userDetails) {
		if (userDetails != null) {
			if (userDetails.getId() == null) {
				userDetailsDao.insert(userDetails);
			} else {
				userDetailsDao.updateByPrimaryKey(userDetails);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUsers(String ids) {
		if (!StringUtils.isEmpty(ids)) {
			ids = ids.substring(0, ids.length()-1);
			String[] idArr = ids.split(",");
			
			int[] arr = new int[idArr.length];
			for (int i = 0;i<idArr.length;i++) {
				arr[i] = Integer.parseInt(idArr[i]);
			}
			userDetailsDao.deleteUsersByIds(arr);
			return true;
		}
		return false;
	}

	@Override
	public Map<String,Object> list(Map<String, Object> paramsMap) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userList", userDetailsDao.list(paramsMap));
		map.put("count", userDetailsDao.listCount());
		return map;
	}
}
