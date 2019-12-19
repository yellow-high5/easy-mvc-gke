package handson.springbootappgke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import handson.springbootappgke.models.User;
import handson.springbootappgke.repositories.UserRepository;

@SpringBootApplication
@Controller
public class Application {

  @Autowired
  UserRepository repository;

  // ユーザー一覧
  @CrossOrigin
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public @ResponseBody Iterable<User> getUserlist() {
      return repository.findAll();
  }

  // ユーザー作成
  @CrossOrigin
  @RequestMapping(path = "/users/new", method = RequestMethod.POST)
  public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
      User n = new User();
      n.setName(name);
      n.setEmail(email);
      repository.save(n);
      return "Saved";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
