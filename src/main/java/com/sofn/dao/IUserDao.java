package com.sofn.dao;

import java.util.List;
import java.util.Map;

import com.sofn.entity.User;


public interface IUserDao extends BaseDao<User>{
    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);
    
    User selectByName(String name);
    
    List<Map<String, Object>> list(Map<String, Object> paramsMap);
    
    Long listCount(Map<String, Object> paramsMap);
    
    List<User> getUserLoginList();
}