package by.ipps.ippsclients.resttemplate.base;

import by.ipps.ippsclients.custom.CustomPage;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface BaseEntityRestTemplate<T> {

  ResponseEntity<T> findById(
      Long id, String url, String language, String section, String department, int idCustomer);

  ResponseEntity<CustomPage<T>> findPagingRecords(
      long page,
      int size,
      String sort,
      String language,
      String url,
      String section,
      String department,
      int idCustomer);

  ResponseEntity<List<T>> findAll(
      String language, String url, String section, String department, int idCustomer);
}
