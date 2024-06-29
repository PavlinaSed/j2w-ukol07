package cz.czechitas.java2webapps.ukol7.repository;

import cz.czechitas.java2webapps.ukol7.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.slug = ?1")
    Page<Post> findPostBySlug(String slug, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.published <=current date ORDER BY p.published DESC ")
    Page<Post> findPostBeforeDay(@Param("currentDay") LocalDate currentDay, Pageable pageable);

}
