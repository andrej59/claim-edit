package ru.ajana.claim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение того, что заявка не найдена в базе.
 *
 * @author Andrey Kharintsev
 * @date 01.07.2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClaimNotFoundException extends RuntimeException {

  public ClaimNotFoundException(final String claimId) {
    super(String.format("Заявка с идентификатором '%s' не найдена", claimId));
  }
}
