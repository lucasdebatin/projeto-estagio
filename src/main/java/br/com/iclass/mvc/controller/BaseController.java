package br.com.iclass.mvc.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

    @ModelAttribute("module")
    abstract String module();
}
