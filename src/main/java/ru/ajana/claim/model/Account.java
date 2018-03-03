package ru.ajana.claim.model;

import java.io.Serializable;

/**
 * Аккаунт пользователя приложения.
 *
 * @author Andrey Kharintsev
 * @date 28.06.2017.
 */
public class Account implements Serializable {

  private Long id;
  private String email;
  private String lastName;
  private String firstName;
  private String middleName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }
}
