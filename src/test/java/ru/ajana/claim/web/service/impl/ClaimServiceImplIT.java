package ru.ajana.claim.web.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.ajana.claim.AbstractTest;
import ru.ajana.claim.model.Claim;
import ru.ajana.claim.web.service.ClaimService;

/**
 * Интеграционный тест, выполняет тестирование CRUD заявки.
 *
 * @author Andrey Kharintsev
 * @date 22.07.2017.
 */
//@ActiveProfiles("test")
public class ClaimServiceImplIT extends AbstractTest {

  @Autowired
  private ClaimService claimService;

  @Test
  @Sql({"classpath:sql/test-claim-data.sql"})
  public void findAllClaims() throws Exception {
    final List<Claim> claimList = claimService.findAllClaims();
    assert claimList.size() == 1;
  }

  @Test
  @Sql({"classpath:sql/test-claim-data.sql"})
  public void getClaim() {
    final Claim claim = claimService.getClaim(1L);
    assertNotNull(claim);
    assertNotNull(claim.getName());
    assertNotNull(claim.getFromAccountId());
    assertNotNull(claim.getFromAccount());
    assertNotNull(claim.getToAccountId());
    assertNotNull(claim.getToAccount());
    assertNotNull(claim.getStatusId());
    assertNotNull(claim.getStatus());
    assertNotNull(claim.getDateCreate());
  }

  @Test
  public void save() {
    final Claim newClaim = new Claim();
    newClaim.setName("Новая заявка");
    newClaim.setFromAccountId(123L);
    newClaim.setToAccountId(125L);
    newClaim.setStatusId(1L);
    final Claim actualClaim = claimService.save(newClaim);
    assertNotNull(actualClaim.getId());

    actualClaim.setName("Заявка изменилась");
    actualClaim.setFromAccountId(124L);
    actualClaim.setToAccountId(125L);
    actualClaim.setStatusId(3L);

    claimService.save(actualClaim);
    final Claim actualClaim1 = claimService.getClaim(Long.valueOf(actualClaim.getId()));
    assertEquals(actualClaim.getName(), actualClaim1.getName());
    assertEquals(actualClaim.getFromAccountId(), actualClaim1.getFromAccountId());
    assertEquals(actualClaim.getToAccountId(), actualClaim1.getToAccountId());
    assertEquals(actualClaim.getStatusId(), actualClaim1.getStatusId());
  }

  @Test
  @Sql({"classpath:sql/test-claim-data.sql"})
  public void delete() {
    final Claim claim = claimService.getClaim(1L);
    assertNotNull(claim);
    claimService.delete(1L);
    final Claim actualClaim = claimService.getClaim(1L);
    assertNull(actualClaim);

  }

  @Test
  public void findAllClaimStatus() {
    assert claimService.getClaimStatusList().size() > 0;
  }
}