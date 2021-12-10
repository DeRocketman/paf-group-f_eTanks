package thl.gruppef.etankrest.etankrestapi.controller;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thl.gruppef.etankrest.etankrestapi.entities.User;
import thl.gruppef.etankrest.etankrestapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController


@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<User> index() {
        return userRepository.findAll();
    }

    @PostMapping("/username")
        public Optional<User> findUserByUsername(@RequestBody String username){
        return this.userRepository.findUserByUsername(username);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user){

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUserImage(user.getUserImage());
        newUser.setPassword(user.getPassword());
        newUser.setPublicName(user.getPublicName());

        User created = userRepository.save(newUser);

        return ResponseEntity.ok(created);
    }
}
