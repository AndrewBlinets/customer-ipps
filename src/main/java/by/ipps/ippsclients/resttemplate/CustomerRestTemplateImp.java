package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.entity.Customer;
import by.ipps.ippsclients.entity.CustomerAuth;
import by.ipps.ippsclients.resttemplate.base.AbstractBaseEntityRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomerRestTemplateImp extends AbstractBaseEntityRestTemplate<Customer> implements CustomerRestTemplate {
    @Override
    public ResponseEntity<Customer> getUserByLogin(String login) {
//        UriComponentsBuilder builder =
//                UriComponentsBuilder.fromHttpUrl(URL_SERVER + "customer/getInfoAboutCustomer");
//        final ParameterizedTypeReference<Customer> responseType =
//                new ParameterizedTypeReference<Customer>() {};
        ResponseEntity<Customer> q = restTemplate.postForEntity(URL_SERVER + "customer/getInfoAboutCustomer", login, Customer.class);
        return q;
    }
}
