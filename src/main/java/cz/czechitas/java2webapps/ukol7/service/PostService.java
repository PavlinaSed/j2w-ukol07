package cz.czechitas.java2webapps.ukol7.service;

import cz.czechitas.java2webapps.ukol7.entity.Post;
import cz.czechitas.java2webapps.ukol7.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    /**
     * Return a list of all articles posted on the blog
     */
public Page<Post> list(){
    Pageable pageable = PageRequest.of(0, 20);
    LocalDate currentDay = LocalDate.now();
    return postRepository.findPostBeforeDay(currentDay, pageable);
}

public Page<Post> singlePost(String slug, Pageable pageable){
    return postRepository.findPostBySlug(slug, pageable);
}
}
