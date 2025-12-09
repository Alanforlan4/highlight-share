package imd.HighlightShare.repository;

import imd.HighlightShare.entity.GroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, Long> {
    List<GroupMemberEntity> findByGroup_Id(Long groupId);
    boolean existsByUser_IdAndGroup_Id(Long userId, Long groupId);
}
