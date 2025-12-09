package imd.HighlightShare.controller;

import imd.HighlightShare.dto.*;
import imd.HighlightShare.entity.UserEntity;
import imd.HighlightShare.repository.UserRepository;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(token);
        var jwt = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(jwt));
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
        repository.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
}
