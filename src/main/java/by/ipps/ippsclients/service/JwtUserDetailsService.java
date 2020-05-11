package by.ipps.ippsclients.service;

import by.ipps.ippsclients.entity.CustomerAuth;
import by.ipps.ippsclients.utils.RestRequestToDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final RestRequestToDao restRequestToDao;

  public JwtUserDetailsService(RestRequestToDao restRequestToDao) {
    this.restRequestToDao = restRequestToDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    CustomerAuth customer = restRequestToDao.getUserByLogin(username);
    return new User(
        customer.getLogin(),
        customer.getHashPassword(),
        customer.isEnabled(),
        true,
        true,
        !customer.isBlock(),
        getAuthorities(customer.getRoles()));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {

    return getGrantedAuthorities(getPrivileges(roles));
  }

  private List<String> getPrivileges(List<String> roles) {
    List<String> privileges = new ArrayList<>();
    if (roles != null)
      for (String role : roles) {
        privileges.add(role);
      }
    return privileges;
  }

  private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }
}
