package swm.toy.baseframework.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(Email email);
    Optional<User> findFirstByProfileUserName(UserName userName);

}
