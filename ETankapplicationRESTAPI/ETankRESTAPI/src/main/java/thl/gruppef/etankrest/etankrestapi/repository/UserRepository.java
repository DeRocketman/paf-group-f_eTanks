package thl.gruppef.etankrest.etankrestapi.repository;

import thl.gruppef.etankrest.etankrestapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
