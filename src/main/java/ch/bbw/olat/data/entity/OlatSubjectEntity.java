package ch.bbw.olat.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "olat_subject_entity")
public class OlatSubjectEntity extends AOlatEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Lob
    @Column(name = "details")
    private String details;

    @Column(name = "file_system_prefix", nullable = false, unique = true)
    private String fileSystemPrefix;

}