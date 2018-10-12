package com.liyong.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liyong.mapper.UserMapper;
import com.liyong.model.User;
import com.liyong.service.UserService;


@Service(value = "userService")
public class UserServiceImpl implements UserService{

	@Resource
    private UserMapper userMapper;
		
	@Override
	public boolean addUser(User user) {
		boolean result = false;
        try {
        	userMapper.insert(user);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}



	@Override
	public User getUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

}
