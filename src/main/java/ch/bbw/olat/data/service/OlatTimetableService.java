package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatTimetableEntity;
import ch.bbw.olat.data.repository.IOlatTimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OlatTimetableService extends AOlatService<IOlatTimetableRepository, OlatTimetableEntity> {
    @Autowired
    public OlatTimetableService(IOlatTimetableRepository repository) {
        super(repository);
    }

    @Override
    public String checkConsistency(OlatTimetableEntity entity) {
        return null;
    }

    @Override
    public List<OlatTimetableEntity> filterAll(String filter) {
        return null;
    }
}
