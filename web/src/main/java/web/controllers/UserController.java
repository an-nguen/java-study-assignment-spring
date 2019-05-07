package web.controllers;

import domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import persistence.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getUsers() {
        return (List<User>) this.repository.findAll();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") User userFromDB,
                           @RequestBody User user) {
        BeanUtils.copyProperties(user, userFromDB, "id");
        return repository.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        repository.delete(user);
    }
}
