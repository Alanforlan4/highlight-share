package imd.HighlightShare.mapper;

import imd.HighlightShare.dto.user.UserSummaryDTO;
import imd.HighlightShare.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserSummaryDTO toSummary(UserEntity u) {
        if (u == null) return null;
        String name = u.getDisplayName() != null ? u.getDisplayName() : u.getUsername();
        String username = u.getUsername().startsWith("@") ? u.getUsername() : "@" + u.getUsername();
        return new UserSummaryDTO(name, username, u.getAvatarUrl());
    }
}
