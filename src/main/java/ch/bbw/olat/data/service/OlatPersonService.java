package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.repository.IOlatPersonRepository;
import ch.bbw.olat.data.repository.IOlatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OlatPersonService extends AOlatService<IOlatPersonRepository, OlatPersonEntity> {

    @Autowired
    public OlatPersonService(IOlatPersonRepository repository) {
        super(repository);
    }

    @Override
    public String checkConsistency(OlatPersonEntity entity) {
        long id = entity.getId();
        String email = entity.getEmail();

        List<OlatPersonEntity> entities = getAll();

        for (OlatPersonEntity other : entities) {
            if (other.getId() == id) continue;

            if (email.equals(other.getEmail())) {
                return attributeNotUnique("email");
            }
        }

        return "Successful";
    }

    public List<OlatPersonEntity> filterAll(String value) {
        return repository.findAll().stream().filter(person -> {
            if (person.getFirstname().toLowerCase().contains(value.toLowerCase())) return true;
            if (person.getLastname().toLowerCase().contains(value.toLowerCase())) return true;
            return person.getEmail().toLowerCase().contains(value.toLowerCase());
        }).collect(Collectors.toList());
    }

    public List<OlatPersonEntity> getWithoutUser() {
        return getAll().stream().filter(p -> !p.getInUse()).collect(Collectors.toList());
    }

    public OlatPersonEntity buildEntity(String firstname, String lastname, String email) {
        OlatPersonEntity entity = OlatPersonEntity.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .build();
        entity.setInUse(false);
        return entity;
    }

    public OlatPersonEntity updateEntity(OlatPersonEntity entity, String firstname, String lastname, String email) {
        entity.setFirstname(firstname);
        entity.setLastname(lastname);
        entity.setEmail(email);
        return entity;
    }
}
