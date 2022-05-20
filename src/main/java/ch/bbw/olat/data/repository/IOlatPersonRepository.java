package ch.bbw.olat.data.repository;

import ch.bbw.olat.data.entity.OlatPersonEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IOlatPersonRepository extends IOlatRepository<OlatPersonEntity>{
    @Transactional
    @Modifying
    @Query("update OlatPersonEntity o set o.firstname = ?1, o.lastname = ?2, o.email = ?3")
    void updateFirstnameAndLastnameAndEmailBy(String firstname, String lastname, String email);

    OlatPersonEntity findByEmail(String email);

    List<OlatPersonEntity> findByFirstnameOrLastnameOrEmail(String firstname, String lastname, String email);


}
