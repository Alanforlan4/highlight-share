package imd.HighlightShare.controller;

import imd.HighlightShare.dto.comment.CommentCreateDTO;
import imd.HighlightShare.entity.CommentEntity;
import imd.HighlightShare.entity.PostEntity;
import imd.HighlightShare.entity.UserEntity;
import imd.HighlightShare.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentEntity create(@PathVariable Long postId, @RequestBody CommentCreateDTO dto){
        CommentEntity c = new CommentEntity();
        c.setPost(new PostEntity(postId));
        c.setAuthor(new UserEntity(dto.authorId()));
        c.setContent(dto.content());
        return commentService.create(c);
    }
}
