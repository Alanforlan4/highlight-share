package imd.HighlightShare.dto;
import imd.HighlightShare.enums.MemberRole;
public record RegisterDTO(String username, String email, String password, String avatarUrl,MemberRole role) {}
