package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.entity.Customer;
import by.ipps.ippsclients.resttemplate.base.AbstractBaseEntityRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestTemplateImp extends AbstractBaseEntityRestTemplate<Customer> implements CustomerRestTemplate {

    @Value("${url.dao}")
    protected String URL_SERVER;

    @Override
    public ResponseEntity<Customer> getUserByLogin(String login) {
//        UriComponentsBuilder builder =
//                UriComponentsBuilder.fromHttpUrl(URL_SERVER + "customer/getInfoAboutCustomer");
//        final ParameterizedTypeReference<Customer> responseType =
//                new ParameterizedTypeReference<Customer>() {};
        ResponseEntity<Customer> q = restTemplate.postForEntity(this.URL_SERVER + "customer/getInfoAboutCustomer", login, Customer.class);
        return q;
    }
}
