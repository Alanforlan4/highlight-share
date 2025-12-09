package imd.HighlightShare.mapper;
import imd.HighlightShare.dto.post.PostDTO;
import imd.HighlightShare.dto.post.PostDetailDTO;
import imd.HighlightShare.entity.PostEntity;
import imd.HighlightShare.dto.comment.CommentDTO;
import imd.HighlightShare.dto.user.UserSummaryDTO;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostMapper {

    private final UserMapper userMapper;
    private final CommentMapper commentMapper;

    public PostMapper(UserMapper userMapper, CommentMapper commentMapper) {
        this.userMapper = userMapper;
        this.commentMapper = commentMapper;
    }

    private String humanTime(LocalDateTime createdAt) {
        if (createdAt == null) return "";
        Duration d = Duration.between(createdAt, LocalDateTime.now());
        if (d.toMinutes() < 60) return d.toMinutes() + "min atrás";
        if (d.toHours() < 24) return d.toHours() + "h atrás";
        return d.toDays() + "d atrás";
    }

    public PostDTO toDTO(PostEntity p, long likesCount, long commentsCount) {
        UserSummaryDTO u = userMapper.toSummary(p.getAuthor());
        return new PostDTO(p.getId(), u, p.getLocation(), p.getImageUrl(), likesCount, commentsCount, p.getCaption(), humanTime(p.getCreatedAt()));
    }

    public PostDetailDTO toDetailDTO(PostEntity p, long likesCount, List<CommentDTO> comments) {
        UserSummaryDTO u = userMapper.toSummary(p.getAuthor());
        return new PostDetailDTO(p.getId(), u, p.getLocation(), p.getImageUrl(), p.getCaption(), humanTime(p.getCreatedAt()), likesCount, comments);
    }
}
