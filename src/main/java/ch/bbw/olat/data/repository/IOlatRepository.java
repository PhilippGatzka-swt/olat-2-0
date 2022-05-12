package ch.bbw.olat.data.repository;

import ch.bbw.olat.data.entity.AOlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOlatRepository<Type extends AOlatEntity> extends JpaRepository<Type, Long> {
}
