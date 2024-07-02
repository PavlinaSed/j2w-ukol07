package cz.czechitas.java2webapps.ukol7.controller;

import cz.czechitas.java2webapps.ukol7.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {
    private final PostService service;
@Autowired
    public PostController(PostService service){
    this.service = service;
    }

    @GetMapping("/")
    public ModelAndView index(Pageable pageable){
    return new ModelAndView("index")
            .addObject("posts", service.list(pageable));
    }

    @GetMapping("/post/{slug}")
    public Object post(@PathVariable String slug){
    return new ModelAndView("post")
            .addObject("post", service.singlePost(slug));
    }
}
