package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatAbsenceEntity;
import ch.bbw.olat.data.repository.IOlatAbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OlatAbsenceService extends AOlatService<IOlatAbsenceRepository, OlatAbsenceEntity> {
    @Autowired
    public OlatAbsenceService(IOlatAbsenceRepository repository) {
        super(repository);
    }

    @Override
    public String checkConsistency(OlatAbsenceEntity entity) {
        return null;
    }

    @Override
    public List<OlatAbsenceEntity> filterAll(String filter) {
        return null;
    }
}

