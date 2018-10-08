package com.sofn.service;

import java.util.List;
import java.util.Map;

import com.sofn.entity.User;
import com.sofn.entity.UserDetails;


public interface IUserService extends  BaseService<User>{
	User getUserById(Integer id);
	User getUserByName(String name);
	Map<String,Object> list(String firstName,String lastName,Integer pageNumber,Integer pageSize,String order,String sort);
	List<User> getUserLoginList();
	
	boolean saveOrUpdateUser(UserDetails userDetails);
	
	boolean deleteUsers(String ids);
	
	Map<String,Object> list(Map<String, Object> paramsMap);
}
