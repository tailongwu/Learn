package SpringCloud02.demo.user.repository;

import SpringCloud02.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
