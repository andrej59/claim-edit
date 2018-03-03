package ru.ajana.claim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение неправильно заполненого значения поля.
 *
 * @author Andrey Kharintsev
 * @date 02.07.2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidValueException extends RuntimeException {

  public InvalidValueException(String fieldName) {
    super(String.format("Значение поля '%s' установлено не верно", fieldName));
  }
}
