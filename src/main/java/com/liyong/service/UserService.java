package com.liyong.service;

import com.liyong.entity.User;

public interface UserService {
	
	User getUserById(String userId) ;
	
	boolean addUser(User record);

}
