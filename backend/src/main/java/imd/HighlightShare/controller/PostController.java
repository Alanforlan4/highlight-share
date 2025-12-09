package imd.HighlightShare.controller;

import imd.HighlightShare.dto.post.*;
import imd.HighlightShare.dto.comment.CommentDTO;
import imd.HighlightShare.entity.*;
import imd.HighlightShare.mapper.*;
import imd.HighlightShare.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @GetMapping("/feed")
    public List<PostDTO> feed(){
        return postService.listAll().stream()
                .map(p -> postMapper.toDTO(p, likeService.count(p.getId()), commentService.countByPost(p.getId())))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PostDetailDTO detail(@PathVariable Long id){
        var p = postService.findById(id);
        var likes = likeService.count(id);
        var comments = commentService.listByPost(id).stream().map(commentMapper::toDTO).collect(Collectors.toList());
        return postMapper.toDetailDTO(p, likes, comments);
    }

    @PostMapping
    public PostEntity create(@RequestBody PostCreateDTO dto){
        PostEntity p = new PostEntity();
        p.setAuthor(new UserEntity(dto.authorId()));
        p.setGroup(new GroupEntity(dto.groupId()));
        p.setCaption(dto.caption());
        p.setImageUrl(dto.imageUrl());
        p.setLocation(dto.location());
        return postService.create(p);
    }
}
