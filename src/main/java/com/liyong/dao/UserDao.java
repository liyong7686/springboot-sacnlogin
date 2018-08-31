package com.liyong.dao;

import com.liyong.entity.User;

public interface UserDao {

    int deleteByPrimaryKey(String id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
}
