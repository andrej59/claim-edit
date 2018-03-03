package ru.ajana.claim.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.ajana.claim.jpa.entities.ClaimStatusEntity;
import ru.ajana.claim.repository.ClaimStatusRepository;

/**
 * DAO статусов заявки.
 *
 * @author Andrey Kharintsev
 * @date 28.06.2017.
 */
@Repository
public class ClaimStatusDao {

  @Autowired
  private ClaimStatusRepository statusRepository;

  public List<ClaimStatusEntity> findAllClaimStatus() {
    return statusRepository.findAll();
  }
}
