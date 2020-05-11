package by.ipps.ippsclients.controller;

import by.ipps.ippsclients.controller.base.BaseInfoForController;
import by.ipps.ippsclients.entity.CustomerAuth;
import by.ipps.ippsclients.entity.UserProfail;
import by.ipps.ippsclients.resttemplate.UserProfailRestTemplate;
import by.ipps.ippsclients.utils.RestRequestToDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userProfile")
public class UserProfileController extends BaseInfoForController {

  private final UserProfailRestTemplate userProfailRestTemplate;
  private final RestRequestToDao restRequestToDao;
  @Autowired private PasswordEncoder passwordEncoder;

  public UserProfileController(
      UserProfailRestTemplate userProfailRestTemplate, RestRequestToDao restRequestToDao) {
    this.userProfailRestTemplate = userProfailRestTemplate;
    this.restRequestToDao = restRequestToDao;
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<String> saveChangeUserProfail(
      @RequestBody UserProfail userProfail, HttpServletRequest request) {
    CustomerAuth user =
        restRequestToDao.getUserByLogin(
            SecurityContextHolder.getContext().getAuthentication().getName());
    if (!userProfail.getNewPassword().equals("")) {
      if (!passwordEncoder.matches(userProfail.getPassword(), user.getHashPassword()))
        return new ResponseEntity<>("Неверный текущий пароль", HttpStatus.BAD_REQUEST);
    } else userProfail.setNewPassword(user.getHashPassword());
    if (userProfail.getNewPassword().equals(userProfail.getPassword())
        || userProfail.getNewPassword().length() < 8)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    if (userProfail.getId() != this.getInfoFromToken(request))
      return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    userProfail.setNewPassword(passwordEncoder.encode(userProfail.getNewPassword()));
    return userProfailRestTemplate.saveChange(userProfail);
  }
}
