package com.liyong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/userController")
public class UserController{

	@GetMapping("/user")
    public String index(String name,ModelAndView mv) {
        mv.addObject("name", name);
        mv.addObject("from", "lqdev.cn");
        //模版名称，实际的目录为：src/main/resources/templates/freemarker.html
        return "freemarker";
    }
	
}
