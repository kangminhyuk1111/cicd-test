package cicd.cicdtest.dto;

import cicd.cicdtest.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public record JoinRequest(String username, String password) {

  public Member toEntity() {
    return new Member(username, password);
  }

  public Member toEntity(PasswordEncoder passwordEncoder) {
    return new Member(username, passwordEncoder.encode(password));
  }
}
