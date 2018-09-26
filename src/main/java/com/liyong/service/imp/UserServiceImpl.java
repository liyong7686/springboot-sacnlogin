package com.liyong.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liyong.dao.UserDao;
import com.liyong.entity.User;
import com.liyong.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService{

	@Resource
    private UserDao userDao;
		
	@Override
	public boolean addUser(User user) {
		boolean result = false;
        try {
            userDao.insert(user);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}



	@Override
	public User getUserById(String userId) {
		return userDao.selectByPrimaryKey(userId);
	}

}
