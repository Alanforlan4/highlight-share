package imd.HighlightShare.service;
import imd.HighlightShare.entity.LikeEntity;
import imd.HighlightShare.entity.PostEntity;
import imd.HighlightShare.entity.UserEntity;
import imd.HighlightShare.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository repo;
    public void like(Long postId, Long userId){
        if (repo.existsByPost_IdAndUser_Id(postId, userId)) return;
        LikeEntity l = new LikeEntity();
        l.setPost(new PostEntity(postId));
        l.setUser(new UserEntity(userId));
        repo.save(l);
    }
    public void unlike(Long postId, Long userId){ repo.deleteByPost_IdAndUser_Id(postId, userId); }
    public long count(Long postId){ return repo.countByPost_Id(postId); }
}
