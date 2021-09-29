package com.restcrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/")
    List<User> findAll(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    User findOne(@PathVariable int id){
        return repository.findById(id).orElse(null);
    }
    @PostMapping("/")
    User create(@RequestBody User user){
        return repository.save(user);
    }
    @PutMapping("/{id}")
    User update(@PathVariable int id, @RequestBody User user){
        User oldUser = repository.findById(id).orElse(null);
        oldUser.setName(user.getName());
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        return repository.save(oldUser);
    }

    @GetMapping(value = "/{id}")
    Integer destroy(@PathVariable int id){
        repository.deleteById(id);
        return id;
    }

    




}
