package com.liyong.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liyong.model.User;
import com.liyong.service.UserService;

@Controller
@RequestMapping("/userController")
public class UserController{

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
    public ModelAndView index(String name) {
		System.out.println("---------------------------- ");
		ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("from", "lqdev.cn");
        mv.setViewName("index");
        //模版名称，实际的目录为：src/main/resources/templates/freemarker.html
        
        Random random = new Random();
        int sRandom = random.nextInt(300) % (300 - 1 + 1) + 1;
        this.userService.addUser(new User("" + sRandom ,"test_001", 334333));
        
        return mv;
    }
	
}
