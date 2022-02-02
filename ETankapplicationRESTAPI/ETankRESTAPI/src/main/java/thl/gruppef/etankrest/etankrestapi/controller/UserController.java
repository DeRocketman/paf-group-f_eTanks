package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import thl.gruppef.etankrest.etankrestapi.entities.User;
import thl.gruppef.etankrest.etankrestapi.repository.UserRepository;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;

import java.util.List;
import java.util.Optional;

/**
 * handles requests related to Users
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor of the class UserController
     *
     * @param userRepository
     * @param userSettingsRepository
     * @param passwordEncoder
     */
    public UserController(UserRepository userRepository, UserSettingsRepository userSettingsRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Finds all users
     *
     * @return list of all users
     */
    @GetMapping("")
    public List<User> index() {
        return userRepository.findAll();
    }

    @PostMapping("/username")
        public Optional<User> findUserByUsername(@RequestBody String username){

        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if(userOptional.isEmpty()){
            User user = new User();
            user.setUsername("DER FALSCHE KERL");
            return Optional.of(user);
        }

        return this.userRepository.findUserByUsername(username);
    }

    /**
     * Finds User by Id
     *
     * @param id    ID of the user
     * @return      the wanted user
     */
    @PostMapping("/id")
    public Optional<User> findUserByUserId(@RequestBody long id){

        Optional<User> userOptional = userRepository.findUserById(id);

        if(userOptional.isEmpty()){
            User user = new User();
            user.setUsername("Falsche ID");
            return Optional.of(user);
        }
        return this.userRepository.findUserById(id);
    }

    /**
     * Saves changes of User
     *
     * @param changedUser   the changed User
     * @return the changed User
     */
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
