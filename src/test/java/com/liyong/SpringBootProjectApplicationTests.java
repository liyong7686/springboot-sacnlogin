package com.liyong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liyong.entity.User;
import com.liyong.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootProjectApplicationTests {

	@Autowired
	private UserService userService;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void test1(){
		
		this.userService.addUser(new User("222rrr","33333",12));
		
	}
	
	
}
