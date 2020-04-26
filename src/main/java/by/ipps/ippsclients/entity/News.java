package by.ipps.ippsclients.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class News extends BaseEntity {

  private String title;
  private String content;
  private String shortTitle;
  private String entrySpeech;
  private FileManager mainImage;
}
