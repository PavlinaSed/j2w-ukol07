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
Pageable pageable = PageRequest.of(0,20);
    public Page<Post> list(Pageable pageable){
    LocalDate currentDay = LocalDate.now().plusDays(1);
    return postRepository.findPostBeforeDay(currentDay, pageable);
}

public Post singlePost(String slug){
    return (Post) postRepository.findPostBySlug(slug);
}
}
