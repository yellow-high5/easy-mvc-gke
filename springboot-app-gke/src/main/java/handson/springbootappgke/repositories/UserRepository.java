package handson.springbootappgke.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import handson.springbootappgke.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public User findById(int id);
}