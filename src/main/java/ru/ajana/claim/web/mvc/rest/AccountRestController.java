package ru.ajana.claim.web.mvc.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ajana.claim.model.Account;
import ru.ajana.claim.web.service.AccountService;

/**
 * Rest - контроллер аккаунтов.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */

@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

  private final static Logger LOG = LoggerFactory.getLogger(AccountRestController.class);
  private final AccountService accountService;

  @Autowired
  public AccountRestController(
      final AccountService accountService) {
    this.accountService = accountService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<Account> getAccounts() {
    LOG.info("execute");
    return accountService.findAllAccounts();
  }
}
