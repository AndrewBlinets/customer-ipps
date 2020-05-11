package by.ipps.ippsclients.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
