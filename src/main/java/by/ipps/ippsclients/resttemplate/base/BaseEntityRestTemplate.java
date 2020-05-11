package by.ipps.ippsclients.resttemplate.base;

import by.ipps.ippsclients.custom.CustomPage;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface BaseEntityRestTemplate<T> {

  ResponseEntity<T> findById(Long id, String url, int idCustomer);

  ResponseEntity<CustomPage<T>> findPagingRecords(
      long page, int size, String sort, String url, int idCustomer);

  ResponseEntity<List<T>> findAll(String url, int idCustomer);
}
