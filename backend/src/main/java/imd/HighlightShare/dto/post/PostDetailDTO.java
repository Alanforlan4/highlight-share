package imd.HighlightShare.dto.post;
import imd.HighlightShare.dto.user.UserSummaryDTO;
import imd.HighlightShare.dto.comment.CommentDTO;
import java.util.List;
public record PostDetailDTO(Long id, UserSummaryDTO user, String location, String imageUrl, String caption, String timestamp, long likes, List<CommentDTO> comments) {}
