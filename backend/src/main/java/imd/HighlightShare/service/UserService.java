package imd.HighlightShare.service;

import imd.HighlightShare.entity.UserEntity;
import imd.HighlightShare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    public Optional<UserEntity> findById(Long id) { return repo.findById(id); }
    public Optional<UserEntity> findByUsername(String u) { return repo.findByUsername(u); }
    public UserEntity save(UserEntity u) { return repo.save(u); }
}
