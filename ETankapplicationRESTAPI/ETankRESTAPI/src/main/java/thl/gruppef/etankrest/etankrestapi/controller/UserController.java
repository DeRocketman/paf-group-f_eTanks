package thl.gruppef.etankrest.etankrestapi.controller;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import javafx.scene.control.Alert;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import thl.gruppef.etankrest.etankrestapi.entities.GameStatistic;
import thl.gruppef.etankrest.etankrestapi.entities.User;
import thl.gruppef.etankrest.etankrestapi.entities.UserSettings;
import thl.gruppef.etankrest.etankrestapi.repository.GameStatisticRepository;
import thl.gruppef.etankrest.etankrestapi.repository.UserRepository;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;

import javax.persistence.EntityNotFoundException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private GameStatisticRepository gameStatisticRepository;


    public UserController(UserRepository userRepository, UserSettingsRepository userSettingsRepository, PasswordEncoder passwordEncoder, GameStatisticRepository gameStatisticRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.gameStatisticRepository = gameStatisticRepository;
    }

    @GetMapping("")
    public List<User> index() {
        return userRepository.findAll();
    }

    @PostMapping("/username")
        public Optional<User> findUserByUsername(@RequestBody String username){

        Optional<User> userOptional = userRepository.findUserByUsername(username);

        //TODO Entfernen oder verbessern DEBUG
        if(userOptional.isEmpty()){
            User user = new User();
            user.setUsername("DER FALSCHE KERL");
            return Optional.of(user);
        }

        return this.userRepository.findUserByUsername(username);
    }

    @PostMapping("/id")
    public Optional<User> findUserByUserId(@RequestBody long id){

        Optional<User> userOptional = userRepository.findUserById(id);

        //TODO Entfernen oder verbessern DEBUG
        if(userOptional.isEmpty()){
            User user = new User();
            user.setUsername("Falsche ID");
            return Optional.of(user);
        }
        return this.userRepository.findUserById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User changedUser) {
        Optional<User> user = userRepository.findUserByUsername(changedUser.getUsername());

        if (!user.isPresent()) { return ResponseEntity.badRequest().build(); }

        User userTest = changedUser;
        userTest.setPassword(passwordEncoder.encode(changedUser.getPassword()));

        userRepository.save(changedUser);
        return ResponseEntity.ok(changedUser);
    }
}
