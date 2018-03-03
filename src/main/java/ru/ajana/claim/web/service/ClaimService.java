package ru.ajana.claim.web.service;

import java.util.List;
import ru.ajana.claim.model.Claim;
import ru.ajana.claim.model.ClaimStatus;

/**
 * Интерфейс сервиса заявок.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */

public interface ClaimService {

  List<Claim> findAllClaims();

  Claim getClaim(Long id);

  Claim save(Claim claim);

  void delete(Long id);

  List<ClaimStatus> getClaimStatusList();
}
