package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.entity.Customer;
import by.ipps.ippsclients.resttemplate.base.AbstractBaseEntityRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestTemplateImp extends AbstractBaseEntityRestTemplate<Customer>
    implements CustomerRestTemplate {

  @Override
  public ResponseEntity<Customer> getUserByLogin(String login) {
    return restTemplate.postForEntity(
        new StringBuffer(this.urlServer).append("customer/getInfoAboutCustomer").toString(),
        login,
        Customer.class);
  }
}
