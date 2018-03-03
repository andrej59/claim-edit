package ru.ajana.claim.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.ajana.claim.jpa.entities.AccountEntity;
import ru.ajana.claim.repository.AccountRepository;

/**
 * DAO аккаунтов приложения.
 *
 * @author Andrey Kharintsev
 * @date 28.06.2017.
 */
@Repository
public class AccountDao {

  @Autowired
  private AccountRepository accountRepository;

  public List<AccountEntity> getAccounts() {
    return accountRepository.findAll();
  }

  public AccountEntity findOne(final long id) {
    return accountRepository.findOne(id);
  }
}
