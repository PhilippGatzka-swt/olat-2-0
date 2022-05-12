package ch.bbw.olat.data.repository;

import ch.bbw.olat.data.entity.OlatGroupEntity;

public interface IOlatGroupRepository extends IOlatRepository<OlatGroupEntity> {
    OlatGroupEntity findByName(String name);
}
