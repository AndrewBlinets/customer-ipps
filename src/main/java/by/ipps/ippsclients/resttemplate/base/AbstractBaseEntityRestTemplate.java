package by.ipps.ippsclients.resttemplate.base;

import by.ipps.ippsclients.custom.CustomPage;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Log4j2
public abstract class AbstractBaseEntityRestTemplate<E> extends BaseInfoForRest
    implements BaseEntityRestTemplate<E> {

  public AbstractBaseEntityRestTemplate(RestTemplate restTemplate) {
    super(restTemplate);
  }

  @Override
  public ResponseEntity<E> findById(Long id, String url, int idCustomer) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(urlServer + url + "/" + id)
              .queryParam("user", idCustomer);
      return restTemplate.exchange(
          builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<E>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findByid");
      log.info(url + "/" + id);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<CustomPage<E>> findPagingRecords(
      long page, int size, String sort, String url, int idCustomer) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(urlServer + url)
              .queryParam("page", String.valueOf(page))
              .queryParam("size", String.valueOf(size))
              .queryParam("sort", sort)
              .queryParam("user", idCustomer);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<CustomPage<E>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAlByPage");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<List<E>> findAll(String url, int idCustomer) {
    if (url.equals("/news/client"))
      return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(urlServer + url + "/all").queryParam("user", idCustomer);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<E>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAll");
      log.info(url);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }
}
