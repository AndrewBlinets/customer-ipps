package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.entity.FavoriteProject;
import by.ipps.ippsclients.entity.Project;
import by.ipps.ippsclients.entity.UserProfail;
import by.ipps.ippsclients.resttemplate.base.BaseInfoForRest;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UserProfailRestTemplate extends BaseInfoForRest {

  private static final String URL = "customer/customerProfail";

  public ResponseEntity<String> saveChange(UserProfail userProfail) {
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(this.urlServer + URL);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    HttpEntity<UserProfail> requestEntity = new HttpEntity<>(userProfail, requestHeaders);
    return restTemplate.exchange(
        builder.toUriString(), HttpMethod.POST, requestEntity, String.class);
  }
}
