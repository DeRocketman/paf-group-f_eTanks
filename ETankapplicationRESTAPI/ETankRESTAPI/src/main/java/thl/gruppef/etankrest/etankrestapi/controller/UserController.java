package thl.gruppef.etankrest.etankrestapi.controller;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import thl.gruppef.etankrest.etankrestapi.entities.User;
import thl.gruppef.etankrest.etankrestapi.entities.UserSettings;
import thl.gruppef.etankrest.etankrestapi.repository.UserRepository;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UserController(UserRepository userRepository,
    UserSettingsRepository userSettingsRepository) {
        this.userRepository = userRepository;

    }

    @GetMapping("")
    public List<User> index() {
        return userRepository.findAll();
    }

    @PostMapping("/username")
        public Optional<User> findUserByUsername(@RequestBody String username){

        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if(!userOptional.isPresent()){
            User user = new User();
            user.setUsername("DER FALSCHE KERL");
            return Optional.of(user);
        }

        return this.userRepository.findUserByUsername(username);
    }

    //Userdaten ändern
    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User changedUser) {
        Optional<User> user = userRepository.findUserByUsername(changedUser.getUsername());

        if (!user.isPresent()) { return ResponseEntity.badRequest().build(); }

        /* User userTest = changedUser;
        userTest.setPassword(passwordEncoder.encode(changedUser.getPassword()));
        */
        userRepository.save(changedUser);
        return ResponseEntity.ok(changedUser);
        /* return userRepository.findUserByUsername(changedUser.getUsername())
                .map(user -> {
                    userRepository.save(user);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> {
                    return ResponseEntity.badRequest().build();
                });
                  }*/

    }

}
