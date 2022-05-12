package ch.bbw.olat.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "olat_timetable_entity")
public class OlatTimetableEntity extends AOlatEntity {
    @Column(name = "weekday", nullable = false)
    private String weekday;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "olat_group_entity_id", nullable = false)
    private OlatGroupEntity olatGroupEntity;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "olat_timetable_entity_olat_subject_entities",
            joinColumns = @JoinColumn(name = "olat_timetable_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "olat_subject_entities_id"))
    private Collection<OlatSubjectEntity> olatSubjectEntities = new ArrayList<>();

}