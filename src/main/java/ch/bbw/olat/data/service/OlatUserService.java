package ch.bbw.olat.data.service;

import ch.bbw.olat.data.Role;
import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.repository.IOlatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OlatUserService extends AOlatService<IOlatUserRepository, OlatUserEntity> {
    @Autowired
    public OlatUserService(IOlatUserRepository repository) {
        super(repository);
    }

    @Override
    public String checkConsistency(OlatUserEntity entity) {
        long id = entity.getId();
        String username = entity.getUsername();

        List<OlatUserEntity> entities = getAll();

        for (OlatUserEntity other : entities) {
            if (other.getId() == id) continue;
            if (username.equals(other.getUsername())) {
                return attributeNotUnique("username");
            }
        }

        return "Successful";
    }

    @Override
    public List<OlatUserEntity> filterAll(String value) {
        return repository.findAll().stream().filter(entity -> {
            if (entity.getName().toLowerCase().contains(value.toLowerCase())) return true;
            return entity.getUsername().toLowerCase().contains(value.toLowerCase());
        }).collect(Collectors.toList());
    }


    public List<OlatUserEntity> filterByRole(Role teacher) {
        return repository.findAll().stream().filter(u -> u.getRoles().contains(teacher)).collect(Collectors.toList());
    }

    public OlatUserEntity buildEntity(String username_, String password_, OlatPersonEntity person_, Role role_) {
        Set<Role> roles = new HashSet<>();
        roles.add(role_);
        OlatUserEntity entity = OlatUserEntity.builder()
                .username(username_)
                .hashedPassword(password_)
                .person(person_)
                .roles(roles)
                .registered(false)
                .fileSystemPrefix(createFileSystemPrefix(username_))
                .build();
        entity.setInUse(false);
        return entity;
    }

    public OlatUserEntity updateEntity(OlatUserEntity entity, Role role_) {
        Set<Role> roles = new HashSet<>();
        roles.add(role_);
        entity.setRoles(roles);
        return entity;
    }
}

