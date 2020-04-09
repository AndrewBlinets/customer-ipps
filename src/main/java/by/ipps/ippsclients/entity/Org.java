package by.ipps.ippsclients.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Org extends BaseEntity implements Serializable {

  private String unp;
  private String name;
  private String shortName;
  private String officialSite;
  private String addr;
  private String email;
}
