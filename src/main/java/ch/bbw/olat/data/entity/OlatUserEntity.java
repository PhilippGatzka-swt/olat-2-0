package ch.bbw.olat.data.entity;

import ch.bbw.olat.data.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "olat_user_entity")
public class OlatUserEntity extends AOlatEntity{
    @OneToOne(cascade = CascadeType.MERGE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "olat_person_entity_id", nullable = false, unique = true)
    private OlatPersonEntity person;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column(name = "file_system_prefix", nullable = false, unique = true)
    private String fileSystemPrefix;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "registered")
    private Boolean registered;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "olat_user_entity_olat_group_entities",
            joinColumns = @JoinColumn(name = "olat_user_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "olat_group_entities_id"))
    private Collection<OlatGroupEntity> olatGroupEntities = new ArrayList<>();

    public String getName(){
        return person.getFirstname() + " " + person.getLastname();
    }

    @Override
    public String toString() {
        return getName();
    }
}