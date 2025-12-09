package imd.HighlightShare.repository;

import imd.HighlightShare.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPost_IdOrderByCreatedAtAsc(Long postId);
    long countByPost_Id(Long postId);
}
