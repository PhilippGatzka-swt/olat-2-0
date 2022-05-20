package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatGroupEntity;
import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.repository.IOlatGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OlatGroupService extends AOlatService<IOlatGroupRepository, OlatGroupEntity> {
    @Autowired
    public OlatGroupService(IOlatGroupRepository repository) {
        super(repository);
    }

    @Override
    public String checkConsistency(OlatGroupEntity entity) {
        long id = entity.getId();
        String name = entity.getName();
        String fileSystemPrefix = entity.getFileSystemPrefix();

        List<OlatGroupEntity> entities = repository.findAll();

        for (OlatGroupEntity other : entities) {
            if (other.getId() == id) {
                continue;
            }
            if (other.getName().equals(name)) {
                return attributeNotUnique("name");
            }
            if (other.getFileSystemPrefix().equals(fileSystemPrefix)) {
                return attributeNotUnique("fileSystemPrefix");
            }
        }

        return "Successful";
    }

    public List<OlatGroupEntity> filterAll(String value) {
        return new ArrayList<>();
    }

    public OlatGroupEntity buildEntity(String name, OlatUserEntity teacher) {
        OlatGroupEntity entity = OlatGroupEntity
                .builder()
                .name(name)
                .fileSystemPrefix(createFileSystemPrefix(name))
                .teacher(teacher)
                .build();
        entity.setInUse(false);
        return entity;
    }

    public OlatGroupEntity updateEntity(OlatGroupEntity entity, String name, OlatUserEntity teacher) {
        entity.setName(name);
        entity.setTeacher(teacher);
        entity.setFileSystemPrefix(createFileSystemPrefix(name));
        return entity;
    }
}

