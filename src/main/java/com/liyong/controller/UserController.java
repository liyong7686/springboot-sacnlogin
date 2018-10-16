package com.liyong.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liyong.model.User;
import com.liyong.service.UserService;


@Controller
@RequestMapping("/userController")
public class UserController{

	@Autowired
	private UserService userService;
	
	@Value("${wx_appid}")
	private String appid;
	
	@RequestMapping("/index")
    public String index(String name,Model model) {
		System.out.println("---------------------------- ");
		System.out.println("---------------------------- " + appid );
		model.addAttribute("name", name);
		model.addAttribute("from", "lqdev.cn");
		model.addAttribute("index");
        //模版名称，实际的目录为：src/main/resources/templates/freemarker.html
        
        Random random = new Random();
        int sRandom = random.nextInt(300) % (300 - 1 + 1) + 1;
        this.userService.addUser(new User("" + sRandom ,"test_001", 334333,"123123"+appid));
        
        return "index";
    }
	
}
