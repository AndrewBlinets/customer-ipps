package by.ipps.ippsclients.controller.base;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

public class BaseInfoForController {

  @Value("${prefiks}")
  private String prefiks;

  @Value("${headre}")
  private String headre;

  @Value("${jwt.secret}")
  private String secretKey;

  protected int getInfoFromToken(HttpServletRequest request) {
    String jwtToken = request.getHeader(headre).replace(prefiks, "");
    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody();
    return (int) claims.get("id");
  }
}
