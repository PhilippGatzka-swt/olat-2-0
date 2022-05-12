package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatAbsenceEntity;
import ch.bbw.olat.data.repository.IOlatAbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlatAbsenceService extends AOlatService<IOlatAbsenceRepository, OlatAbsenceEntity> {
    @Autowired
    public OlatAbsenceService(IOlatAbsenceRepository repository) {
        super(repository);
    }
}

