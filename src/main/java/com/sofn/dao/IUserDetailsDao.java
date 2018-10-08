package com.sofn.dao;

import java.util.List;
import java.util.Map;

import com.sofn.entity.UserDetails;


public interface IUserDetailsDao extends BaseDao<UserDetails>{
    
    int deleteUsersByIds(int[] ids);
    
    List<UserDetails> list(Map<String, Object> paramsMap);
    
    Long listCount();
}