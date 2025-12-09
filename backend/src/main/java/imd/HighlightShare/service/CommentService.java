package imd.HighlightShare.service;
import imd.HighlightShare.entity.CommentEntity;
import imd.HighlightShare.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repo;
    public CommentEntity create(CommentEntity c){ return repo.save(c); }
    public List<CommentEntity> listByPost(Long postId){ return repo.findByPost_IdOrderByCreatedAtAsc(postId); }
    public long countByPost(Long postId){ return repo.countByPost_Id(postId); }
}
