package by.ipps.ippsclients.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentForCustomer extends BaseEntity {

  private Project project;
  private Sheet sheet;
  private byte[] file;
  private String fileName;
  private String description;
  private int size;
  private String mimeType;
}
