package guru.springframework.repositories;

import guru.springframework.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE " +
            "u.userName = :userName")
    User findByUsername(String userName);
}
