package ru.ajana.claim.jpa.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность аккаунта.
 *
 * @author Andrey Kharintsev
 * @date 23.07.2017.
 */
@Entity
@Table(name = "CLAIM_ACCOUNTS")
public class AccountEntity implements Serializable {

  private static final long serialVersionUID = -1383523903276816089L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "LAST_NAME")
  private String lastName;
  @Column(name = "FIRST_NAME")
  private String firstName;
  @Column(name = "MIDDLE_NAME")
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
