package by.ipps.ippsclients.resttemplate.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BaseInfoForRest {

  @Value("${url.dao}")
  protected String urlServer;

  @Autowired
  protected RestTemplate restTemplate;

}
