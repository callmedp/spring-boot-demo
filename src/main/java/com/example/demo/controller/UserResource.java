package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.User;
import com.example.demo.service.UserDAOService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserResource {

    private UserDAOService service;

    public UserResource(UserDAOService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        User user = service.findUser(id);

        if(user == null) {
            throw new UserNotFoundException("id:"+id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("every-user"));

        return entityModel;
    }

    @PostMapping("/users")
    public String createUser(@Valid @RequestBody User user) {
        service.save(user);
        return "USER created successfully!";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Integer id) {

        service.deleteById(id);

        return "User removed successfully";
    }
}
