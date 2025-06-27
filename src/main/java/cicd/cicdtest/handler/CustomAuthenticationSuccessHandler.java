package cicd.cicdtest.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication) throws IOException {

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    boolean isAdmin = authorities.stream()
        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

    logger.info("LOGIN_SUCCESS - User: {}, IP: {}, Time: {}",
        authentication.getName(),
        getClientIP(request),
        LocalDateTime.now());

    // 세션에 정보 저장
    HttpSession session = request.getSession();
    LocalDateTime loginTime = LocalDateTime.now();
    session.setAttribute("loginTime", loginTime);

    if (isAdmin) {
      System.out.println("ADMIN 권한으로 /admin 이동");
      response.sendRedirect("/admin");
    } else {
      System.out.println("USER 권한으로 / 이동");
      response.sendRedirect("/");
    }
  }

  private String getClientIP(HttpServletRequest request) {
    String xForwardedFor = request.getHeader("X-Forwarded-For");

    if (xForwardedFor != null && !xForwardedFor.isEmpty() && !"unknown".equalsIgnoreCase(xForwardedFor)) {
      // 첫 번째 IP가 실제 클라이언트 IP
      return xForwardedFor.split(",")[0].trim();
    }

    return request.getRemoteAddr();
  }
}
