package by.ipps.ippsclients.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FileManager extends BaseEntity implements Serializable {

  private String fileName;
  private String fileMine;
  private String path;
}
