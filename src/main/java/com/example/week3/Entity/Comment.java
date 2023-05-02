package com.example.week3.Entity;

import com.example.week3.Dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "BLOG_ID")
    private Blog blog;
    private String userName;
    private String comment;
    private int commentLikeCount;

    public void setCommentLikeCount(int commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public Comment(Blog blog, CommentRequestDto commentRequestDto, User user) {
        this.blog = blog;
        this.userName = user.getUserName();
        this.comment = commentRequestDto.getComment();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
