package by.ipps.ippsclients.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomPage<T> {

  List<T> content;
  long totalElements;
  private int number;
  private int size;
  private int totalPages;
  private int numberOfElements;
}
