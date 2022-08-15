package swm.toy.baseframework.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(Email email);

    Optional<User> findFirstByProfileUserName(UserName userName);
}
