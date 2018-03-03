package ru.ajana.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ajana.claim.jpa.entities.AccountEntity;

/**
 * Репозиторий аккаунта.
 *
 * @author Andrey Kharintsev
 * @date 23.07.2017.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
