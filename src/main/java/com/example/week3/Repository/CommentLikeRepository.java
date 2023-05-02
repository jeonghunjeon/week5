package com.example.week3.Repository;

import com.example.week3.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    List<CommentLike> findAllByComment(Comment comment);
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
    void deleteByCommentAndUser(Comment comment, User user);
}
