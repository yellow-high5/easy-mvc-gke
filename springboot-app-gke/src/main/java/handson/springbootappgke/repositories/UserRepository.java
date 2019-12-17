package handson.springbootappgke.repositories;

import org.springframework.data.repository.CrudRepository;
import handson.springbootappgke.models.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}