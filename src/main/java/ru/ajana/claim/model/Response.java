package ru.ajana.claim.model;

import java.io.Serializable;
import java.util.List;

/**
 * Структура ответа Rest-сервиса.
 *
 * @author Andrey Kharintsev
 * @date 02.07.2017.
 */
public class Response<T> implements Serializable {

  private boolean success;
  private T data;
  private String message;
  private List<String> errors;
  private Long tid;
  private String trace;

  public boolean getSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public Long getTid() {
    return tid;
  }

  public void setTid(Long tid) {
    this.tid = tid;
  }

  public String getTrace() {
    return trace;
  }

  public void setTrace(String trace) {
    this.trace = trace;
  }
}
