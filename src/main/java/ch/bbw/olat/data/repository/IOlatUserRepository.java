package ch.bbw.olat.data.repository;

import ch.bbw.olat.data.entity.OlatUserEntity;

public interface IOlatUserRepository extends IOlatRepository<OlatUserEntity> {
    OlatUserEntity findByUsername(String username);
}
