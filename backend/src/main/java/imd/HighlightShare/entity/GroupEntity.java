package imd.HighlightShare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class GroupEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GroupMemberEntity> members;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<PostEntity> posts;

    public GroupEntity(Long id) { this.id = id; }

    @PrePersist
    public void prePersist() { if (createdAt == null) createdAt = LocalDateTime.now(); }
}
