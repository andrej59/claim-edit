package ru.ajana.claim.exception;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение неправильно введённых данных.
 *
 * @author Andrey Kharintsev
 * @date 06.07.2017
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

  private List<String> errors;

  public InvalidInputException(final List<String> errors) {
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }
}
