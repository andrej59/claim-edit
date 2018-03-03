package ru.ajana.claim.web.service;

import java.util.List;
import ru.ajana.claim.model.Account;

/**
 * Интерфейс сервиса аккаунтов.
 *
 * @author Andrey Kharintsev
 * @date 28.06.2017.
 */
public interface AccountService {

  List<Account> findAllAccounts();
}
