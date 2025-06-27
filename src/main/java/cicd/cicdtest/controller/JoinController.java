package cicd.cicdtest.controller;

import cicd.cicdtest.domain.Member;
import cicd.cicdtest.dto.JoinRequest;
import cicd.cicdtest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JoinController {

  private final UserService userService;

  public JoinController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/join")
  public String joinPage() {
    return "join";
  }

  @PostMapping("/join")
  public String joinProcess(JoinRequest request) {
    System.out.println("call POST /join");

    final Member join = userService.join(request);

    System.out.println(join.toString());

    return "redirect:/login";
  }
}
