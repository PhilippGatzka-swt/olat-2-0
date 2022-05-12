package ch.bbw.olat.data.entity;

import ch.bbw.olat.data.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "olat_user_entity")
public class OlatUserEntity extends AOlatEntity{
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "olat_person_entity_id", nullable = false, unique = true)
    private OlatPersonEntity olatPersonEntity;

    @Enumerated
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "file_system_prefix", nullable = false, unique = true)
    private String fileSystemPrefix;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "hashed_password", nullable = false)
    private String hashedPassword;

}