package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.entity.Customer;
import by.ipps.ippsclients.resttemplate.base.AbstractBaseEntityRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomerRestTemplateImp extends AbstractBaseEntityRestTemplate<Customer> implements CustomerRestTemplate {
    @Override
    public Customer getUserByLogin(String login) {
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(URL_SERVER + "customer/login");
        final ParameterizedTypeReference<Customer> responseType =
                new ParameterizedTypeReference<Customer>() {};
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, responseType).getBody();
    }
}
