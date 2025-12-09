package imd.HighlightShare.repository;

import imd.HighlightShare.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    boolean existsByPost_IdAndUser_Id(Long postId, Long userId);
    void deleteByPost_IdAndUser_Id(Long postId, Long userId);
    long countByPost_Id(Long postId);
}
