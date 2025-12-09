package imd.HighlightShare.service;
import imd.HighlightShare.entity.PostEntity;
import imd.HighlightShare.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repo;
    public PostEntity create(PostEntity p){ return repo.save(p); }
    public PostEntity findById(Long id){ return repo.findById(id).orElseThrow(); }
    public List<PostEntity> listByGroup(Long groupId){ return repo.findByGroup_IdOrderByCreatedAtDesc(groupId); }
    public List<PostEntity> listAll(){ return repo.findAllByOrderByCreatedAtDesc(); }
}
