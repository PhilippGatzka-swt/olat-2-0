package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatSubjectEntity;
import ch.bbw.olat.data.repository.IOlatSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OlatSubjectService extends AOlatService<IOlatSubjectRepository, OlatSubjectEntity> {
    @Autowired
    public OlatSubjectService(IOlatSubjectRepository repository) {
        super(repository);
    }

    public String checkConsistency(OlatSubjectEntity entity) {
        long id = entity.getId();
        String name = entity.getName();
        String fileSystemPrefix = entity.getFileSystemPrefix();

        List<OlatSubjectEntity> entities = repository.findAll();
        for (OlatSubjectEntity other : entities) {
            if (other.getId() == id) {
                continue;
            }
            if (name.matches(other.getName())) {
                return attributeNotUnique("name");
            }
            if (fileSystemPrefix.matches(other.getFileSystemPrefix())) {
                return attributeNotUnique("fileSystemPrefix");
            }
        }
        return "Successful";
    }

    @Override
    public List<OlatSubjectEntity> filterAll(String filter) {
        return null;
    }

    public OlatSubjectEntity buildEntity(String name, String details) {
        OlatSubjectEntity entity = OlatSubjectEntity
                .builder()
                .name(name)
                .fileSystemPrefix(createFileSystemPrefix(name))
                .details(Objects.equals(details, "") ? name : details)
                .build();
        entity.setInUse(false);
        return entity;
    }

    public OlatSubjectEntity updateEntity(OlatSubjectEntity entity, String name, String details) {
        entity.setName(name);
        entity.setDetails(details);
        entity.setFileSystemPrefix(createFileSystemPrefix(name));
        return entity;
    }


}

