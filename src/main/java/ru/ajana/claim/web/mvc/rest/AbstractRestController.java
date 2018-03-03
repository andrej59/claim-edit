package ru.ajana.claim.web.mvc.rest;

import java.util.List;
import org.springframework.http.ResponseEntity;
import ru.ajana.claim.model.Response;

/**
 * Абстрактный класс RestController.
 *
 * @author Andrey Kharintsev
 * @date 02.07.2017.
 */
public abstract class AbstractRestController<T> {

  protected ResponseEntity<T> ok() {
    final Response<T> response = new Response<>();
    response.setSuccess(true);
    return ok(null, "");
  }

  protected ResponseEntity<T> ok(T body) {
    return ok(body, "");
  }

  protected ResponseEntity<T> ok(T body, final String message) {
    final Response<T> response = new Response<>();
    response.setData(body);
    response.setSuccess(true);
    response.setMessage(message);
    return ResponseEntity.ok((T) response);
  }

  protected ResponseEntity<T> badRequest() {
    return badRequest(null, "Ошибка запроса данных");
  }

  protected ResponseEntity<T> badRequest(T body, final String message) {
    final Response<T> response = new Response<>();
    response.setData(body);
    response.setSuccess(false);
    response.setMessage(message);
    return ResponseEntity.badRequest().body((T) response);
  }

  protected ResponseEntity<T> badRequest(T body, final List<String> errors) {
    final Response<T> response = new Response<>();
    response.setData(body);
    response.setSuccess(false);
    response.setMessage("Ошибка запроса");
    response.setErrors(errors);
    return ResponseEntity.badRequest().body((T) response);
  }

  protected ResponseEntity<Void> notFound() {
    return ResponseEntity.notFound().build();
  }
}
