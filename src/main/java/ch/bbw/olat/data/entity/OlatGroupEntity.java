package ch.bbw.olat.data.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "olat_group_entity")
public class OlatGroupEntity extends AOlatEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "file_system_prefix", nullable = false, unique = true)
    private String fileSystemPrefix;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private OlatUserEntity teacher;

}