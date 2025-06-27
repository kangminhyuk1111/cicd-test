package cicd.cicdtest.repository;

import cicd.cicdtest.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {

  boolean existsByUsername(String username);

  Optional<Member> findByUsername(String username);
}
