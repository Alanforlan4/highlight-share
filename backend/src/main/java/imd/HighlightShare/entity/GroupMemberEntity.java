package imd.HighlightShare.entity;

import imd.HighlightShare.enums.MemberRole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "group_members")
public class GroupMemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(optional = false) @JoinColumn(name = "group_id")
    private GroupEntity group;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

    public GroupMemberEntity(Long id) { this.id = id; }
}
