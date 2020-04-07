package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.entity.Customer;
import by.ipps.ippsclients.resttemplate.base.BaseEntityRestTemplate;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRestTemplate extends BaseEntityRestTemplate<Customer> {
    public Customer getUserByLogin(String login);
}