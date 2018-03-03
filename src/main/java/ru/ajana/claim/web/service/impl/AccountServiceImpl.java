package ru.ajana.claim.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ajana.claim.dao.AccountDao;
import ru.ajana.claim.helper.ObjectMapperHelper;
import ru.ajana.claim.model.Account;
import ru.ajana.claim.web.service.AccountService;

/**
 * Реализация сервиса аккаунтов.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

  private final AccountDao accountDao;

  @Autowired
  public AccountServiceImpl(final AccountDao accountDao) {
    this.accountDao = accountDao;
  }


  @Override
  public List<Account> findAllAccounts() {
    final List<Account> accounts = new ArrayList<>();
    accountDao.getAccounts().forEach(obj -> accounts.add(ObjectMapperHelper.map(obj, Account.class)));
    return accounts;
  }
}
