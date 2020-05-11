package by.ipps.ippsclients.resttemplate.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BaseInfoForRest {

  @Value("${url.dao}")
  protected String urlServer;

  protected final RestTemplate restTemplate;

  public BaseInfoForRest(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
}
