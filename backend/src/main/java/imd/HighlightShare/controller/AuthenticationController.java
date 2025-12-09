package imd.HighlightShare.controller;

import imd.HighlightShare.dto.*;
import imd.HighlightShare.entity.GroupEntity;
import imd.HighlightShare.entity.GroupMemberEntity;
import imd.HighlightShare.entity.UserEntity;
import imd.HighlightShare.enums.MemberRole;
import imd.HighlightShare.repository.UserRepository;
import imd.HighlightShare.service.GroupMemberService;
import imd.HighlightShare.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final GroupMemberService groupMemberService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(token);
        var user = (UserEntity) auth.getPrincipal();
        var jwt = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(jwt, user.getId(), user.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto){
        if(repository.existsByUsername(dto.username())) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        UserEntity u = new UserEntity();
        u.setUsername(dto.username());
        u.setEmail(dto.email());
        u.setPassword(passwordEncoder.encode(dto.password()));
        u.setDisplayName(dto.username());
        u.setAvatarUrl(dto.avatarUrl());
        u.setRole(dto.role());
        u.setCreatedAt(LocalDateTime.now());
        UserEntity savedUser = repository.save(u);

        try {
            GroupMemberEntity membership = new GroupMemberEntity();
            membership.setUser(savedUser);
            membership.setGroup(new GroupEntity(1L));
            membership.setRole(MemberRole.MEMBER);
            groupMemberService.save(membership);
        } catch (Exception e) {
            System.err.println("Erro ao adicionar usuario no grupo padrão: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
}
