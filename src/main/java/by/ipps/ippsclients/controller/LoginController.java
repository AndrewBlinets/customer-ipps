package by.ipps.ippsclients.controller;

import by.ipps.ippsclients.entity.Customer;
import by.ipps.ippsclients.entity.JwtRequest;
import by.ipps.ippsclients.entity.JwtResponse;
import by.ipps.ippsclients.exception.AuthException;
import by.ipps.ippsclients.resttemplate.CustomerRestTemplate;
import by.ipps.ippsclients.utils.JwtTokenUtil;
import by.ipps.ippsclients.utils.RestRequestToDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final RestRequestToDao restRequestToDao;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, RestRequestToDao restRequestToDao, CustomerRestTemplate rest) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.restRequestToDao = restRequestToDao;
        this.rest = rest;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(
            @RequestBody JwtRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (AuthException e) {
            return new ResponseEntity<>(new JwtResponse(null, e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(
                new JwtResponse(
                        jwtTokenUtil.generateToken(
                                restRequestToDao.getUserByLogin(authenticationRequest.getUsername())),
                        "Успешно авторизован!"));
    }

    private void authenticate(String username, String password) throws AuthException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (LockedException e) {
            throw new AuthException("Пользователь заблокирован");
        } catch (DisabledException e) {
            throw new AuthException("Пользователь отключен");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException("Неверный логин или пароль!");
        }
    }

    private CustomerRestTemplate rest;

    @GetMapping(value = "/getInfoAboutCustomer")
    @ResponseBody
    public ResponseEntity<Customer> getInfoAboutCustomer(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (!username.equals("anonymousUser")) {
            return rest.getUserByLogin(username);
        } else return null;
    }
}
