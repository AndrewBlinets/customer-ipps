package by.ipps.ippsclients.utils;

import by.ipps.ippsclients.entity.CustomerAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Component
public class RestRequestToDao {

  @Value("${url.dao}")
  protected String URL_SERVER;

  public CustomerAuth getUserByLogin(String login) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<CustomerAuth> response =
            restTemplate.postForEntity(URL_SERVER + "users/auth", login, CustomerAuth.class);
    return response.getBody();
  }
}
