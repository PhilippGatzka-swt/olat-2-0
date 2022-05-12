package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatSubjectEntity;
import ch.bbw.olat.data.repository.IOlatSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlatSubjectService extends AOlatService<IOlatSubjectRepository, OlatSubjectEntity> {
    @Autowired
    public OlatSubjectService(IOlatSubjectRepository repository) {
        super(repository);
    }
}

