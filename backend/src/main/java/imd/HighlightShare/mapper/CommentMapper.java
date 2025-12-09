package imd.HighlightShare.mapper;
import imd.HighlightShare.dto.comment.CommentDTO;
import imd.HighlightShare.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDTO toDTO(CommentEntity c) {
        var author = c.getAuthor();
        String authorName = author.getDisplayName() != null ? author.getDisplayName() : author.getUsername();
        return new CommentDTO(c.getId(), author.getId(), authorName, c.getContent(), c.getCreatedAt().toString());
    }
}
