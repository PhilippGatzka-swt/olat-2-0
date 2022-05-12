package ch.bbw.olat.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "olat_absence_entity")
public class OlatAbsenceEntity extends AOlatEntity{
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "olat_absence_entity_id", nullable = false)
    private OlatAbsenceEntity olatAbsenceEntity;

    @Column(name = "start", nullable = false)
    private Timestamp start;

    @Column(name = "end", nullable = false)
    private Timestamp end;

    @Column(name = "comment")
    private String comment;

    @Column(name = "signed")
    private Boolean signed;

}