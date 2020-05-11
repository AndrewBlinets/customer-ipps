package by.ipps.ippsclients.utils;

import by.ipps.ippsclients.exception.InvalidJwtAuthenticationException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtRevokedTokensStore {

  @Autowired private JwtTokenUtil jwtTokenProvider;

  private LinkedList<String> revokedTokensQueue = new LinkedList<>();
  private Set<Integer> revokedTokens = new HashSet<>();

  void revokeToken(String token) throws InvalidJwtAuthenticationException {
    if (!revokedTokens.contains(token.hashCode())) {
      revokedTokens.add(token.hashCode());
      revokedTokensQueue.addLast(token);
    }

    boolean removeToken = true;

    while (removeToken && revokedTokensQueue.size() > 0) {
      String firstToken = revokedTokensQueue.getFirst();

      if (firstToken != null && !jwtTokenProvider.validateToken(firstToken)) {
        revokedTokens.remove(firstToken.hashCode());
        revokedTokensQueue.removeFirst();
      } else {
        removeToken = false;
      }
    }
  }

  boolean isTokenRevoked(String token) {
    return revokedTokens.contains(token.hashCode());
  }
}
