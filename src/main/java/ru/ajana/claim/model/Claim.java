package ru.ajana.claim.model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Абстрактная заявка.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */
public class Claim implements Serializable {

  private String id;
  @NotBlank(message = "Должно быть заполнено")
  @Size(max = 255, message = "Значение превышает 255 символов")
  private String name;
  @NotNull(message = "Должно быть заполнено")
  @Min(value = 1, message = "Должно быть заполнено")
  private Long fromAccountId;
  @NotNull(message = "Должно быть заполнено")
  @Min(value = 1, message = "Должно быть заполнено")
  private Long toAccountId;
  @NotNull(message = "Должно быть заполнено")
  @Min(value = 1, message = "Должно быть заполнено")
  private Long statusId;
  private Account fromAccount;
  private Account toAccount;
  private ClaimStatus status;
  private Date dateCreate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getFromAccountId() {
    return fromAccountId;
  }

  public void setFromAccountId(Long fromAccountId) {
    this.fromAccountId = fromAccountId;
  }

  public Long getToAccountId() {
    return toAccountId;
  }

  public void setToAccountId(Long toAccountId) {
    this.toAccountId = toAccountId;
  }

  public Long getStatusId() {
    return statusId;
  }

  public void setStatusId(Long statusId) {
    this.statusId = statusId;
  }

  public Account getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(Account fromAccount) {
    this.fromAccount = fromAccount;
  }

  public Account getToAccount() {
    return toAccount;
  }

  public void setToAccount(Account toAccount) {
    this.toAccount = toAccount;
  }

  public ClaimStatus getStatus() {
    return status;
  }

  public void setStatus(ClaimStatus status) {
    this.status = status;
  }

  public Date getDateCreate() {
    return dateCreate;
  }

  public void setDateCreate(Date dateCreate) {
    this.dateCreate = dateCreate;
  }
}
