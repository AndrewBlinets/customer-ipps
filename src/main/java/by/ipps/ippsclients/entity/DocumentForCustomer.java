package by.ipps.ippsclients.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DocumentForCustomer extends BaseEntity {

  private byte[] file;
  private String fileName;
  private String mimeType;

}
