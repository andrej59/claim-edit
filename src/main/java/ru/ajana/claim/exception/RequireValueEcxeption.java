package ru.ajana.claim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение требующее заполнить значение поля.
 *
 * @author Andrey Kharintsev
 * @date 02.07.2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequireValueEcxeption extends RuntimeException {

  public RequireValueEcxeption(final String fieldName) {
    super(String.format("Поле '%s' обязательно для заполнения", fieldName));
  }
}
