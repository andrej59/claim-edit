package ru.ajana.claim.jpa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Сущность заявки.
 *
 * @author Andrey Kharintsev
 * @date 28.06.2017.
 */
@Entity
@Table(name = "OP_CLAIMS")
public class ClaimEntity implements Serializable {

  private static final long serialVersionUID = 469097689697632885L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "NAME")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "FROM_ACCOUNT_ID", nullable = false, updatable = false)
  private AccountEntity fromAccount;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "TO_ACCOUNT_ID", nullable = false, updatable = false)
  private AccountEntity toAccount;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "STATUS_ID", nullable = false)
  private ClaimStatusEntity status;


  @OrderBy("dateCreate desc")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DT_CREATE")
  private Date dateCreate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getFromAccountId() {
    return fromAccount.getId();
  }

  public Long getToAccountId() {
    return toAccount.getId();
  }

  public Long getStatusId() {
    return status.getId();
  }

  public AccountEntity getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(AccountEntity fromAccount) {
    this.fromAccount = fromAccount;
  }

  public AccountEntity getToAccount() {
    return toAccount;
  }

  public void setToAccount(AccountEntity toAccount) {
    this.toAccount = toAccount;
  }

  public ClaimStatusEntity getStatus() {
    return status;
  }

  public void setStatus(ClaimStatusEntity status) {
    this.status = status;
  }

  public Date getDateCreate() {
    return dateCreate;
  }

  public void setDateCreate(Date dateCreate) {
    this.dateCreate = dateCreate;
  }
}
