package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.DocumentForCustomer;
import by.ipps.ippsclients.resttemplate.base.BaseEntityRestTemplate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface DocumentForCustomerRestTemplate
    extends BaseEntityRestTemplate<DocumentForCustomer> {

  ResponseEntity<CustomPage<DocumentForCustomer>> findPagingRecordsByProject(
      int page, int size, String s, String url, int infoFromToken, long project);

  ResponseEntity<List<DocumentForCustomer>> findAllByProject(
      String url, int infoFromToken, long project);

  ResponseEntity<CustomPage<DocumentForCustomer>> findPagingRecordsBySheet(
      int page, int size, String s, String url, int infoFromToken, long sheet);

  ResponseEntity<List<DocumentForCustomer>> findAllBySheet(
      String url, int infoFromToken, long sheet);
}
