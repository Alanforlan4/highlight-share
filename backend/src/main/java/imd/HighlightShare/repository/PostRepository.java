package imd.HighlightShare.repository;

import imd.HighlightShare.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByGroup_IdOrderByCreatedAtDesc(Long groupId);
    List<PostEntity> findAllByOrderByCreatedAtDesc();
}
