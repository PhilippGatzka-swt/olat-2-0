package ch.bbw.olat.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "olat_absence_entity")
public class OlatAbsenceEntity extends AOlatEntity {

    @Column(name = "comment")
    private String comment;

    @Column(name = "signed")
    private Boolean signed;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "olat_subject_entity_id", nullable = false)
    private OlatSubjectEntity olatSubjectEntity;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private OlatPersonEntity student;

}