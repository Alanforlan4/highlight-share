package imd.HighlightShare.controller;

import imd.HighlightShare.entity.GroupMemberEntity;
import imd.HighlightShare.service.GroupMemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/members")
@AllArgsConstructor
public class GroupMemberController {

    @Autowired
    private GroupMemberService groupMemberService;

    @PostMapping
    public GroupMemberEntity addMember(
            @PathVariable Long groupId,
            @RequestBody GroupMemberEntity member) {

        return groupMemberService.save(member);
    }

    @GetMapping
    public List<GroupMemberEntity> list(@PathVariable Long groupId) {
        return groupMemberService.listMembers(groupId);
    }
}
