package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.repository.IOlatPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OlatPersonService extends AOlatService<IOlatPersonRepository, OlatPersonEntity> {
    @Autowired
    public OlatPersonService(IOlatPersonRepository repository) {
        super(repository);
    }
}
