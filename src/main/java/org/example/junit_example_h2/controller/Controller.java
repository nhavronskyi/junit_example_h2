package org.example.junit_example_h2.controller;

import lombok.RequiredArgsConstructor;
import org.example.junit_example_h2.dao.UserDao;
import org.example.junit_example_h2.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final UserDao dao;

    @GetMapping("all")
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @GetMapping
    public User getUser(@RequestParam long id) {
        return dao.findById(id).orElseGet(() -> new User(null, null, null));
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        dao.save(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id) {
        dao.deleteById(id);
    }
}
