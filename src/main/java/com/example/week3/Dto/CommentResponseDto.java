package com.example.week3.Dto;

import com.example.week3.Entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String userName;
    private String comment;
    private int commentLikeCount;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userName = comment.getUserName();
        this.comment = comment.getComment();
        this.commentLikeCount = comment.getCommentLikeCount();
        this.createdAt = comment.getCreatedAt();
    }
}
