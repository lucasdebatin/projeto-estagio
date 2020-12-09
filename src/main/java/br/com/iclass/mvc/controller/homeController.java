package br.com.iclass.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController extends BaseController {

    String module() {
        return "home";
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

}
