package imd.HighlightShare.mapper;
import imd.HighlightShare.dto.group.GroupDTO;
import imd.HighlightShare.dto.group.GroupCreateDTO;
import imd.HighlightShare.entity.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public GroupEntity toEntity(GroupCreateDTO dto) {
        var g = new GroupEntity();
        g.setName(dto.name());
        g.setDescription(dto.description());
        return g;
    }
    public GroupDTO toDTO(GroupEntity g) {
        return new GroupDTO(g.getId(), g.getName(), g.getDescription(), g.getCreatedAt() != null ? g.getCreatedAt().toString() : null);
    }
}
