package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatTimetableEntity;
import ch.bbw.olat.data.repository.IOlatTimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlatTimetableService extends AOlatService<IOlatTimetableRepository, OlatTimetableEntity> {
    @Autowired
    public OlatTimetableService(IOlatTimetableRepository repository) {
        super(repository);
    }
}
