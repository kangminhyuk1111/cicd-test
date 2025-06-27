package cicd.cicdtest.service;

import cicd.cicdtest.domain.Member;
import cicd.cicdtest.dto.JoinRequest;
import cicd.cicdtest.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Member join(final JoinRequest request) {
    final boolean isUser = userRepository.existsByUsername(request.username());
    if (isUser) {
      throw new RuntimeException("이미 존재하는 유저 네임 입니다.");
    }

    return userRepository.save(request.toEntity(passwordEncoder));
  }
}
