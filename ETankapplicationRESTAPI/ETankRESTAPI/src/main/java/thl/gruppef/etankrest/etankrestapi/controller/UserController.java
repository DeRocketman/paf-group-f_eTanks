package thl.gruppef.etankrest.etankrestapi.controller;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.springframework.http.ResponseEntity;
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
}
