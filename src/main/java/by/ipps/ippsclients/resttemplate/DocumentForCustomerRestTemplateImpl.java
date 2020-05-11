package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.DocumentForCustomer;
import by.ipps.ippsclients.resttemplate.base.AbstractBaseEntityRestTemplate;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class DocumentForCustomerRestTemplateImpl
    extends AbstractBaseEntityRestTemplate<DocumentForCustomer>
    implements DocumentForCustomerRestTemplate {

  public DocumentForCustomerRestTemplateImpl(RestTemplate restTemplate) {
    super(restTemplate);
  }

  @Override
  public ResponseEntity<CustomPage<DocumentForCustomer>> findPagingRecords(
      long page, int size, String sort, String url, int idCustomer) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(urlServer + url + "/byCustomerPage/" + idCustomer)
              .queryParam("page", String.valueOf(page))
              .queryParam("size", String.valueOf(size))
              .queryParam("sort", sort);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<CustomPage<DocumentForCustomer>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAlByPage");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<List<DocumentForCustomer>> findAll(String url, int idCustomer) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(urlServer + url + "/byCustomer/" + idCustomer);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<DocumentForCustomer>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAll");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<CustomPage<DocumentForCustomer>> findPagingRecordsByProject(
      int page, int size, String sort, String url, int infoFromToken, long project) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(urlServer + url + project)
              .queryParam("page", String.valueOf(page))
              .queryParam("size", String.valueOf(size))
              .queryParam("sort", sort);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<CustomPage<DocumentForCustomer>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAlByPage");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<List<DocumentForCustomer>> findAllByProject(
      String url, int infoFromToken, long project) {
    try {
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlServer + url + project);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<DocumentForCustomer>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAll");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<CustomPage<DocumentForCustomer>> findPagingRecordsBySheet(
      int page, int size, String sort, String url, int infoFromToken, long sheet) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(urlServer + url + sheet)
              .queryParam("page", String.valueOf(page))
              .queryParam("size", String.valueOf(size))
              .queryParam("sort", sort);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<CustomPage<DocumentForCustomer>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAlByPage");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<List<DocumentForCustomer>> findAllBySheet(
      String url, int infoFromToken, long sheet) {
    try {
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlServer + url + sheet);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<DocumentForCustomer>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAll");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }
}
