package com.example.week3.Controller;

import com.example.week3.Dto.StatusResponseDto;
import com.example.week3.Security.UserDetailsImpl;
import com.example.week3.Service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog/{blogId}")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public StatusResponseDto blogLike(@PathVariable Long blogId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.blogLike(blogId, userDetails);
    }

    @PostMapping("/{commentId}/like")
    public StatusResponseDto commentLike(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likeService.commentLike(commentId, userDetails);
    }
}
