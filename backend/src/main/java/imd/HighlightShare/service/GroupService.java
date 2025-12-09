package imd.HighlightShare.service;
import imd.HighlightShare.entity.GroupEntity;
import imd.HighlightShare.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repo;
    public GroupEntity create(GroupEntity g){ return repo.save(g); }
    public GroupEntity findById(Long id){ return repo.findById(id).orElseThrow(); }
    public List<GroupEntity> findAll(){ return repo.findAll(); }
}
