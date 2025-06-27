package cicd.cicdtest.service;

import cicd.cicdtest.domain.Member;
import cicd.cicdtest.dto.CustomUserDetails;
import cicd.cicdtest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final Member member = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

    return new CustomUserDetails(member);
  }
}
