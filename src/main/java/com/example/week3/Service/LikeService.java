package com.example.week3.Service;

import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Entity.*;
import com.example.week3.Exception.CustomException;
import com.example.week3.Exception.ErrorCode;
import com.example.week3.Repository.*;
import com.example.week3.Security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BlogLikeRepository blogLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public StatusResponseDto blogLike(Long blogId, UserDetailsImpl userDetails) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new CustomException(ErrorCode.BLOG_NOT_FOUND)
        );
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new CustomException(ErrorCode.INVALID_USER)
        );
//        User user = userDetails.getUser();
        if (blogLikeRepository.findByUserAndBlog(user, blog).isPresent()) {
            blog.setLikeCount(blogLikeCount(blogId) - 1);
            blogLikeRepository.deleteByBlogAndUser(blog, user);
            return new StatusResponseDto(200, "좋아요 삭제 성공.");
        } else {
            blog.setLikeCount(blogLikeCount(blogId) + 1);
            blogLikeRepository.save(new BlogLike(user, blog));
            return new StatusResponseDto(200, "좋아요 성공.");
        }
    }

    public int blogLikeCount(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new CustomException(ErrorCode.BLOG_NOT_FOUND)
        );
        List<BlogLike> blogLikes = blogLikeRepository.findAllByBlog(blog);
        return blogLikes.size();
    }

    @Transactional
    public StatusResponseDto commentLike(Long commentId, UserDetailsImpl userDetails) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new CustomException(ErrorCode.INVALID_USER)
        );
//        User user = userDetails.getUser();
        if (commentLikeRepository.findByUserAndComment(user, comment).isPresent()) {
            comment.setCommentLikeCount(commentLikeCount(commentId) - 1);
            commentLikeRepository.deleteByCommentAndUser(comment, user);
            return new StatusResponseDto(200, "좋아요 삭제 성공.");
        } else {
            comment.setCommentLikeCount(commentLikeCount(commentId) + 1);
            commentLikeRepository.save(new CommentLike(user, comment));
            return new StatusResponseDto(200, "좋아요 성공.");
        }
    }

    public int commentLikeCount(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );
        List<CommentLike> commentLikes = commentLikeRepository.findAllByComment(comment);
        return commentLikes.size();
    }
}
