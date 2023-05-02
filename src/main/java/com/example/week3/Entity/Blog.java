package com.example.week3.Entity;

import com.example.week3.Dto.BlogRequestDto;
import com.example.week3.Dto.BlogResponseDto;
import com.example.week3.Repository.CommentRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Blog extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
    private String title;
    private String userName;
    private String content;
    private int blogLikeCount;

    public void setLikeCount(int blogLikeCount) {
        this.blogLikeCount = blogLikeCount;
    }

    public Blog(BlogRequestDto requestDto, User user) {
        this.user = user;
        this.title = requestDto.getTitle();
        this.userName = user.getUserName();
        this.content = requestDto.getContent();
    }
    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
