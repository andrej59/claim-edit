package ru.ajana.claim.web.service.impl;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ajana.claim.dao.AccountDao;
import ru.ajana.claim.dao.ClaimStatusDao;
import ru.ajana.claim.helper.ObjectMapperHelper;
import ru.ajana.claim.jpa.entities.ClaimEntity;
import ru.ajana.claim.mapper.ClaimMapper;
import ru.ajana.claim.model.Claim;
import ru.ajana.claim.model.ClaimStatus;
import ru.ajana.claim.repository.ClaimRepository;
import ru.ajana.claim.web.service.ClaimService;

/**
 * Реализация сервиса заявок.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */
@Service("claimService")
@Transactional(readOnly = true)
public class ClaimServiceImpl implements ClaimService {

  private final ClaimRepository claimRepository;
  private final AccountDao accountDao;
  private final ClaimStatusDao claimStatusDao;


  @Autowired
  public ClaimServiceImpl(final ClaimRepository claimRepository, final AccountDao accountDao,
      final ClaimStatusDao claimStatusDao
  ) {
    this.accountDao = accountDao;
    this.claimStatusDao = claimStatusDao;
    this.claimRepository = claimRepository;
  }

  public List<Claim> findAllClaims() {
    final List<ClaimEntity> claimEntities = Lists.newArrayList(claimRepository.findAll());
    final List<Claim> claims = new ArrayList<>();

    for (ClaimEntity entity : claimEntities) {
      Claim claim = ClaimMapper.mapClaim(entity);
      claims.add(claim);
    }

    return claims;
  }

  public Claim getClaim(final Long id) {
    if (id == null) {
      return null;
    }
    final ClaimEntity claimEntity = claimRepository.findOne(id);
    return ClaimMapper.mapClaim(claimEntity);
  }

  @Transactional
  public Claim save(final Claim claim) {
    if (claim == null) {
      return null;
    }
    final ClaimEntity entity = claimRepository.save(ClaimMapper.mapClaimEntity(claim));
    claimRepository.flush();
    return ClaimMapper.mapClaim(entity);
  }

  @Transactional
  public void delete(final Long id) {
    if (id == null) {
      return;
    }
    claimRepository.delete(id);
  }

  public List<ClaimStatus> getClaimStatusList() {
    List<ClaimStatus> statuses = new ArrayList<>();
    claimStatusDao.findAllClaimStatus()
        .forEach(obj -> statuses.add(ObjectMapperHelper.map(obj, ClaimStatus.class)));
    return statuses;
  }
}
