package imd.HighlightShare.controller;

import imd.HighlightShare.dto.group.GroupCreateDTO;
import imd.HighlightShare.entity.GroupEntity;
import imd.HighlightShare.mapper.GroupMapper;
import imd.HighlightShare.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @PostMapping
    public GroupEntity create(@RequestBody GroupCreateDTO dto){
        return groupService.create(groupMapper.toEntity(dto));
    }
}
