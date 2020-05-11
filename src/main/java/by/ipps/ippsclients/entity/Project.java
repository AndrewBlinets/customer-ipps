package by.ipps.ippsclients.entity;

import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Project extends BaseEntity implements Serializable {
  private String title;
  private List<CustomerForProject> customers;
  private boolean favorites;
}
