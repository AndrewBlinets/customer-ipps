package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.Project;
import by.ipps.ippsclients.resttemplate.base.AbstractBaseEntityRestTemplate;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class ProjectRestTemplateImp extends AbstractBaseEntityRestTemplate<Project>
    implements ProjectRestTemplate {

  @Override
  public ResponseEntity<Project> findById(
      Long id, String url, String language, String section, String department, int idCustomer) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(
              urlServer + "project/projectForCustomerById/" + idCustomer + "/" + id);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<Project>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findByid");
      log.info(url + "/" + id);
      log.info(language + ", " + section + ", " + department);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<CustomPage<Project>> findPagingRecords(
      long page,
      int size,
      String sort,
      String language,
      String url,
      String section,
      String department,
      int idCustomer) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  @Override
  public ResponseEntity<List<Project>> findAll(
      String language, String url, String section, String department, int idCustomer) {
      try {
          UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
              urlServer + url + "/projectForCustomerByIdCustomer/" + idCustomer);
          return restTemplate.exchange(
              builder.toUriString(),
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<Project>>() {});
      } catch (org.springframework.web.client.HttpClientErrorException exception) {
          log.info("findAll");
          log.info(url);
          log.info(language + ", " + section + ", " + department);
          log.error(exception.getStatusCode() + " " + exception.getStatusText());
          log.error(exception.getStackTrace());
          return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
      }
  }
}
