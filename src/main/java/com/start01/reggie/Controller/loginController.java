package com.start01.reggie.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {

    @RequestMapping(path = {"/", "/login"})
    public String login() {
        return "redirect:backend/page/login/login.html";
    }
}
