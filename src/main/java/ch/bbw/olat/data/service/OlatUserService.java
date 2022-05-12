package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.repository.IOlatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlatUserService extends AOlatService<IOlatUserRepository, OlatUserEntity> {
    @Autowired
    public OlatUserService(IOlatUserRepository repository) {
        super(repository);
    }
}

