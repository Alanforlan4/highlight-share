package imd.HighlightShare.mapper;
import imd.HighlightShare.dto.like.LikeDTO;
import imd.HighlightShare.entity.LikeEntity;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {
    public LikeDTO toDTO(LikeEntity l) {
        return new LikeDTO(l.getId(), l.getUser().getId(), l.getCreatedAt().toString());
    }
}
