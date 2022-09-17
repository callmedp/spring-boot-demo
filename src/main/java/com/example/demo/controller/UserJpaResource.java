package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.jpa.PostRepository;
import com.example.demo.jpa.UserRepository;
import com.example.demo.repository.Post;
import com.example.demo.repository.User;
import com.example.demo.service.UserDAOService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {



    private UserRepository repository;

    private PostRepository postRepository;

    public UserJpaResource(UserRepository repository, PostRepository postRepository) {

        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("id:"+id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("every-user"));

        return entityModel;
    }

    @PostMapping("/jpa/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody User user) {
        repository.save(user);
        return "USER created successfully!";
    }

    @DeleteMapping("/jpa/users/{id}")
    public String deleteUser(@PathVariable Integer id) {

        repository.deleteById(id);

        return "User removed successfully";
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("id:"+id);
        }

        List<Post> posts = user.get().getPosts();

        return posts;
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPostByUser(@PathVariable Integer id, @Valid @RequestBody Post post) {

        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("id:"+id);
        }

         post.setUser(user.get());

        postRepository.save(post);

        return "Post mapped to user";
    }
}
