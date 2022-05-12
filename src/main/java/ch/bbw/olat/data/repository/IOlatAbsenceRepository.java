package ch.bbw.olat.data.repository;

import ch.bbw.olat.data.entity.OlatAbsenceEntity;
import ch.bbw.olat.data.entity.OlatPersonEntity;

import java.util.List;

public interface IOlatAbsenceRepository extends IOlatRepository<OlatAbsenceEntity> {
    List<OlatAbsenceEntity> getByStudent(OlatPersonEntity person);
}
