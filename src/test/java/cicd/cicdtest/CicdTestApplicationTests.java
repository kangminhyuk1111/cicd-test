package cicd.cicdtest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CicdTestApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void cicdTest() {
    System.out.println("CI/CD TEST");
  }

}
