package by.ipps.ippsclients.resttemplate.base;

import by.ipps.ippsclients.custom.CustomPage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Log4j2
public abstract class AbstractBaseEntityRestTemplate<E> implements BaseEntityRestTemplate<E> {

  //    protected static final String URL_SERVER = "http://192.168.1.125:8080/dao/";// server
  //    protected static final String URL_SERVER = "http://192.168.1.89:8082/dao/";// local work
  @Value("${url.dao}")
  protected String URL_SERVER;
  // = "http://localhost:8082/dao/";// local home
  //    protected static final String URL_SERVER = "http://localhost:8080/dao/";// local home
  private static final String LANGUAGE = "language";
  private static final String SECTION = "section";
  private static final String DEPARTAMENT = "department";

  @Autowired protected RestTemplate restTemplate;

  @Override
  public ResponseEntity<E> findById(
      Long id, String url, String language, String section, String department) {
    try {
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_SERVER + url + "/" + id);
      setSectionAndDeportamentAndLanguage(language, section, department, builder);
      return restTemplate.exchange(
          builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<E>() {});
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
  public ResponseEntity<CustomPage<E>> findPagingRecords(
      long page,
      int size,
      String sort,
      String language,
      String url,
      String section,
      String department) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(URL_SERVER + url)
              .queryParam("page", String.valueOf(page))
              .queryParam("size", String.valueOf(size))
              .queryParam("sort", sort);
      setSectionAndDeportamentAndLanguage(language, section, department, builder);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<CustomPage<E>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAlByPage");
      log.info(url);
      log.info(language + ", " + section + ", " + department);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  @Override
  public ResponseEntity<List<E>> findAll(
      String language, String url, String section, String department) {
    if (url.equals("/news/client"))
      return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    try {
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_SERVER + url + "/all");
      setSectionAndDeportamentAndLanguage(language, section, department, builder);
      return restTemplate.exchange(
          builder.toUriString(),
          HttpMethod.GET,
          null,
          new ParameterizedTypeReference<List<E>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findAll");
      log.info(url);
      log.info(language + ", " + section + ", " + department);
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  private void setSectionAndDeportamentAndLanguage(
      String language, String section, String department, UriComponentsBuilder builder) {
    if (language != null) builder.queryParam(LANGUAGE, language);
    if (section != null) builder.queryParam(SECTION, section);
    if (department != null) builder.queryParam(DEPARTAMENT, department);
  }
}
