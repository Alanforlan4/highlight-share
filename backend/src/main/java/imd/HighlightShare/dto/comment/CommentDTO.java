package imd.HighlightShare.dto.comment;
public record CommentDTO(Long id, Long authorId, String authorName, String content, String createdAt) {}
