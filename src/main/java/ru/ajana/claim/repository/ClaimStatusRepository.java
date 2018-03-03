package ru.ajana.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ajana.claim.jpa.entities.ClaimStatusEntity;

/**
 * Репозиторий статусов заявок.
 *
 * @author Andrey Kharintsev
 * @date 23.07.2017.
 */
public interface ClaimStatusRepository extends JpaRepository<ClaimStatusEntity, Long> {

}
