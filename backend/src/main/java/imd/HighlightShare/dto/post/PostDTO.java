package imd.HighlightShare.dto.post;
import imd.HighlightShare.dto.user.UserSummaryDTO;
public record PostDTO(Long id, UserSummaryDTO user, String location, String imageUrl, long likes, long comments, String caption, String timestamp) {}
