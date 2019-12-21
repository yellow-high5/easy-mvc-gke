package handson.springbootappgke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
  @Transactional
  public @ResponseBody Iterable<User> getUserlist() {
      return repository.findAll();
  }

  // ユーザー情報
  @CrossOrigin
  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
  @Transactional
  public @ResponseBody User getUser(@PathVariable int id) {
      User n = repository.findById(id);
      return n;
  }

  // ユーザー作成
  @CrossOrigin
  @RequestMapping(path = "/users/new", method = RequestMethod.POST)
  @Transactional(readOnly = false)
  public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
      User n = new User();
      n.setName(name);
      n.setEmail(email);
      repository.save(n);
      return "New User Saved";
  }

  // ユーザー更新
  @CrossOrigin
  @RequestMapping(path = "/users/{id}/update", method = RequestMethod.PUT)
  @Transactional(readOnly = false)
  public @ResponseBody String updateUser(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
      User n = repository.findById(id);
      n.setName(name);
      n.setEmail(email);
      repository.saveAndFlush(n);
      return "Updated";
  }

  // ユーザー削除
  @CrossOrigin
  @RequestMapping(path = "/users/{id}/delete", method = RequestMethod.DELETE)
  @Transactional(readOnly = false)
  public @ResponseBody String deleteUser(@PathVariable int id) {
      repository.deleteById(id);
      return "Deleted";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
