package com.example.week3.Repository;

import com.example.week3.Entity.Blog;
import com.example.week3.Entity.BlogLike;
import com.example.week3.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogLikeRepository extends JpaRepository<BlogLike, Long> {
    List<BlogLike> findAllByBlog(Blog blog);
    Optional<BlogLike> findByUser(User user);
    void deleteByBlogAndUser(Blog blog, User user);
}
