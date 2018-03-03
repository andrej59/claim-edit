package ru.ajana.claim.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import org.junit.Test;
import ru.ajana.claim.jpa.entities.AccountEntity;
import ru.ajana.claim.jpa.entities.ClaimEntity;
import ru.ajana.claim.jpa.entities.ClaimStatusEntity;
import ru.ajana.claim.model.Claim;

/**
 * Модульный тест для маппинга заявки.
 *
 * @author Andrey Kharintsev
 * @date 06.07.2017
 */
public class ClaimMapperTest {

  @Test
  public void testMapClaim() {
    ClaimEntity claimEntity = new ClaimEntity();
    final long id = 123L;
    claimEntity.setId(id);
    final String name = "Тестовая заявка";
    claimEntity.setName(name);

    final AccountEntity fromAccount = new AccountEntity();
    fromAccount.setId(123L);
    claimEntity.setFromAccount(fromAccount);
    final AccountEntity toAccount = new AccountEntity();
    toAccount.setId(124L);
    claimEntity.setToAccount(toAccount);
    final ClaimStatusEntity statusEntity = new ClaimStatusEntity();
    statusEntity.setId(4L);
    claimEntity.setStatus(statusEntity);
    final Date createDate = new Date();
    claimEntity.setDateCreate(createDate);

    Claim claim = ClaimMapper.mapClaim(claimEntity);
    assertEquals(String.valueOf(id), claim.getId());
    assertEquals(name, claim.getName());
    assertEquals(Long.valueOf(fromAccount.getId()), claim.getFromAccountId());
    assertEquals(Long.valueOf(toAccount.getId()), claim.getToAccountId());
    assertEquals(statusEntity.getId(), claim.getStatusId());
    assertEquals(createDate, claim.getDateCreate());

    assertNotNull(claim.getFromAccount());
    assertNotNull(claim.getToAccount());
    assertNotNull(claim.getStatus());

    assertEquals(Long.valueOf(fromAccount.getId()), claim.getFromAccount().getId());
    assertEquals(Long.valueOf(toAccount.getId()), claim.getToAccount().getId());
    assertEquals(statusEntity.getId().intValue(), claim.getStatus().getId());
  }

  @Test
  public void testMapClaimEntity() {
    final Claim claim = new Claim();
    final Long id = 123L;
    claim.setId(String.valueOf(id));
    final String name = "Тестовая заявка";
    claim.setName(name);
    final Long fromAccountId = 321L;
    claim.setFromAccountId(fromAccountId);
    final Long toAccountId = 111L;
    claim.setToAccountId(toAccountId);
    final Long statusId = 5L;
    claim.setStatusId(statusId);
    final Date createDate = new Date();
    claim.setDateCreate(createDate);

    final ClaimEntity claimEntity = ClaimMapper.mapClaimEntity(claim);

    assertEquals(id, Long.valueOf(claimEntity.getId()));
    assertEquals(name, claimEntity.getName());
    assertEquals(fromAccountId, claimEntity.getFromAccountId());
    assertEquals(toAccountId, claimEntity.getToAccountId());
    assertEquals(statusId, claimEntity.getStatusId());
    assertEquals(createDate, claimEntity.getDateCreate());
  }
}