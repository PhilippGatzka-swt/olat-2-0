package ch.bbw.olat.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "olat_person_entity")
public class OlatPersonEntity extends AOlatEntity {
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "olat_person_entity_olat_group_entities",
            joinColumns = @JoinColumn(name = "olat_person_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "olat_group_entities_id"))
    @ToString.Exclude
    private Collection<OlatGroupEntity> olatGroupEntities = new ArrayList<>();

}