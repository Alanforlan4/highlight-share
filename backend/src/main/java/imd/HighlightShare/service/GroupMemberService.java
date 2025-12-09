package imd.HighlightShare.service;

import imd.HighlightShare.entity.GroupMemberEntity;
import imd.HighlightShare.repository.GroupMemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public GroupMemberEntity save(GroupMemberEntity member) {
        return groupMemberRepository.save(member);
    }

    public List<GroupMemberEntity> listMembers(Long groupId) {
        return groupMemberRepository.findByGroup_Id(groupId);
    }

    public boolean isMember(Long userId, Long groupId) {
        return groupMemberRepository.existsByUser_IdAndGroup_Id(userId, groupId);
    }
}
