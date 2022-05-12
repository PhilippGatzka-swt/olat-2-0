package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatGroupEntity;
import ch.bbw.olat.data.repository.IOlatGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlatGroupService extends AOlatService<IOlatGroupRepository, OlatGroupEntity> {
    @Autowired
    public OlatGroupService(IOlatGroupRepository repository) {
        super(repository);
    }
}

