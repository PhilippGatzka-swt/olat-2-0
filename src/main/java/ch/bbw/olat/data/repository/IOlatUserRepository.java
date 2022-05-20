package ch.bbw.olat.data.repository;

import ch.bbw.olat.data.Role;
import ch.bbw.olat.data.entity.OlatUserEntity;

import java.util.List;
import java.util.Set;

public interface IOlatUserRepository extends IOlatRepository<OlatUserEntity> {
    OlatUserEntity findByUsername(String username);

    List<OlatUserEntity> findByPerson_FirstnameLikeIgnoreCaseOrPerson_LastnameLikeIgnoreCaseOrPerson_EmailLikeIgnoreCaseOrUsernameLikeIgnoreCase(String firstname, String lastname, String email, String username);
}
