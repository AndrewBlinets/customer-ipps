package by.ipps.ippsclients.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerForProject extends BaseEntity implements Serializable {
  private String name;
  private String surName;
  private String patronicName;
}
