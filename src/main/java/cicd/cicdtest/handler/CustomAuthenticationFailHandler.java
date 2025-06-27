package cicd.cicdtest.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(final HttpServletRequest request,
      final HttpServletResponse response, final AuthenticationException exception)
      throws IOException, ServletException {

    // 실패 원인 로깅
    System.out.println("로그인 실패: " + exception.getMessage());
    System.out.println("실패한 사용자명: " + request.getParameter("username"));

    // 에러 메시지 설정
    String errorMessage = getErrorMessage(exception);

    // 로그인 페이지로 리다이렉트 (에러 메시지와 함께)
    response.sendRedirect("/login?error=true&message=" +
        java.net.URLEncoder.encode(errorMessage, "UTF-8"));
  }

  private String getErrorMessage(AuthenticationException exception) {
    if (exception instanceof org.springframework.security.authentication.BadCredentialsException) {
      return "아이디 또는 비밀번호가 잘못되었습니다.";
    } else if (exception instanceof org.springframework.security.authentication.DisabledException) {
      return "계정이 비활성화되었습니다.";
    } else if (exception instanceof org.springframework.security.authentication.AccountExpiredException) {
      return "계정이 만료되었습니다.";
    } else if (exception instanceof org.springframework.security.authentication.CredentialsExpiredException) {
      return "비밀번호가 만료되었습니다.";
    } else if (exception instanceof org.springframework.security.authentication.LockedException) {
      return "계정이 잠겨있습니다.";
    } else {
      return "로그인에 실패했습니다.";
    }
  }
}
