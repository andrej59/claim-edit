package ru.ajana.claim.mapper;

import org.apache.commons.lang3.math.NumberUtils;
import ru.ajana.claim.dao.ClaimStatusDao;
import ru.ajana.claim.helper.ObjectMapperHelper;
import ru.ajana.claim.jpa.entities.AccountEntity;
import ru.ajana.claim.jpa.entities.ClaimEntity;
import ru.ajana.claim.jpa.entities.ClaimStatusEntity;
import ru.ajana.claim.model.Account;
import ru.ajana.claim.model.Claim;
import ru.ajana.claim.model.ClaimStatus;

/**
 * Mapper для заявки Claim <-> ClaimEntity.
 *
 * @author Andrey Kharintsev
 * @date 06.07.2017
 */
public class ClaimMapper {

  public static Claim mapClaim(final ClaimEntity entity) {
    if (entity == null) {
      return null;
    }
    final Claim claim = new Claim();
    claim.setId(String.valueOf(entity.getId()));
    claim.setName(entity.getName());
    claim.setToAccountId(entity.getToAccountId());
    claim.setFromAccountId(entity.getFromAccountId());
    claim.setStatusId(entity.getStatusId());
    claim.setFromAccount(ObjectMapperHelper.map(entity.getFromAccount(), Account.class));
    claim.setToAccount(ObjectMapperHelper.map(entity.getToAccount(), Account.class));
    claim.setStatus(ObjectMapperHelper.map(entity.getStatus(), ClaimStatus.class));
    claim.setDateCreate(entity.getDateCreate());
    return claim;
  }

  public static ClaimEntity mapClaimEntity(final Claim claim) {
    if (claim == null) {
      return null;
    }

    final ClaimEntity entity = new ClaimEntity();
    long claimId = NumberUtils.toLong(claim.getId(), 0);
    entity.setId(claimId);
    entity.setName(claim.getName());

    final AccountEntity fromAccount = new AccountEntity();
    fromAccount.setId(claim.getFromAccountId());
    entity.setFromAccount(fromAccount);

    final AccountEntity toAccount = new AccountEntity();
    toAccount.setId(claim.getToAccountId());
    entity.setToAccount(toAccount);

    final ClaimStatusEntity status = new ClaimStatusEntity();
    status.setId(claim.getStatusId());
    entity.setStatus(status);
    entity.setDateCreate(claim.getDateCreate());
    return entity;
  }
}
