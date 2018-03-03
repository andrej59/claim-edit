package ru.ajana.claim.model;

import java.io.Serializable;

/**
 * Статус абстрактной заявки.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */
public class ClaimStatus implements Serializable {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
