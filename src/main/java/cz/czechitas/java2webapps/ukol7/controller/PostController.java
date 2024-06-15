package cz.czechitas.java2webapps.ukol7.controller;

import cz.czechitas.java2webapps.ukol7.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {
    private final PostService service;
@Autowired
    public PostController(PostService service){
        this.service = service;
    }

    @GetMapping("/index")
    public String list(){
    return "index";
    }

    @GetMapping("/post")
    public String post(){
    return "post";
    }
}
