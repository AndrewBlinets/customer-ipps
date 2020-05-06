package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.entity.FavoriteProject;
import by.ipps.ippsclients.entity.Project;
import by.ipps.ippsclients.resttemplate.base.BaseInfoForRest;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Log4j2
public class FavoritesProjectRestTemplate extends BaseInfoForRest {

  private static final String URL = "customer/favoriteProject";

  public ResponseEntity<List<Project>> getFavoritProjectByIdCustomer(int idCustomer) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(this.urlServer + URL)
              .queryParam("customer", idCustomer);
      return this.restTemplate.exchange(
          builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Project>>() {});
    } catch (org.springframework.web.client.HttpClientErrorException exception) {
      log.info("findByid");
      log.error(exception.getStatusCode() + " " + exception.getStatusText());
      log.error(exception.getStackTrace());
      return new ResponseEntity<>(HttpStatus.valueOf(exception.getStatusCode().value()));
    }
  }

  public ResponseEntity<Project> addFavoriteProject(FavoriteProject favoriteProject) {
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(this.urlServer + URL);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    HttpEntity<FavoriteProject> requestEntity = new HttpEntity<>(favoriteProject, requestHeaders);
    return restTemplate.exchange(
        builder.toUriString(), HttpMethod.POST, requestEntity, Project.class);
  }

  public void removeFavoriteProject(int infoFromToken, long id) {
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(this.urlServer + URL + "/" + id)
            .queryParam("customer", String.valueOf(infoFromToken));
    restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, null, Boolean.TYPE);
  }
}
