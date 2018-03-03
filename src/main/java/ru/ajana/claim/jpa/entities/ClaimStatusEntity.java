package ru.ajana.claim.jpa.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность статус заявки.
 *
 * @author Andrey Kharintsev
 * @date 23.07.2017.
 */
@Entity
@Table(name = "CLAIM_STATUS")
public class ClaimStatusEntity implements Serializable {
  private static final long serialVersionUID = -6028448104111512363L;
  @Id
  private Long id;
  @Column(name = "NAME")
  private String name;

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
}
