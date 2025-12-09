package imd.HighlightShare.controller;

import imd.HighlightShare.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public void like(@PathVariable Long postId, @RequestParam Long userId) { likeService.like(postId, userId); }

    @DeleteMapping
    public void unlike(@PathVariable Long postId, @RequestParam Long userId) { likeService.unlike(postId, userId); }
}
