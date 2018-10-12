package com.liyong.mapper;

import com.liyong.model.User;

public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
}
