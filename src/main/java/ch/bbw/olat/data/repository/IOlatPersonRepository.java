package ch.bbw.olat.data.repository;

import ch.bbw.olat.data.entity.OlatPersonEntity;

import java.util.Optional;

public interface IOlatPersonRepository extends IOlatRepository<OlatPersonEntity>{

    OlatPersonEntity findByEmail(String email);
}
