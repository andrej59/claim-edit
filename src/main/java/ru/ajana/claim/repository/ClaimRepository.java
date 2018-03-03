package ru.ajana.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ajana.claim.jpa.entities.ClaimEntity;

/**
 * Репозиторий заявок.
 *
 * @author Andrey Kharintsev
 * @date 29.06.2017.
 */

public interface ClaimRepository extends JpaRepository<ClaimEntity, Long> {

}
