package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thl.gruppef.etankrest.etankrestapi.entities.User;
import thl.gruppef.etankrest.etankrestapi.entities.UserSettings;
import thl.gruppef.etankrest.etankrestapi.repository.GameStatisticRepository;
import thl.gruppef.etankrest.etankrestapi.repository.UserRepository;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;
import thl.gruppef.etankrest.etankrestapi.request.AuthRequest;
import thl.gruppef.etankrest.etankrestapi.request.UserRequest;
import thl.gruppef.etankrest.etankrestapi.security.JwtTokenProvider;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserSettingsRepository userSettingsRepository;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private GameStatisticRepository gameStatisticRepository;


    public AuthController(GameStatisticRepository gameStatisticRepository, UserSettingsRepository userSettingsRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userSettingsRepository = userSettingsRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.gameStatisticRepository = gameStatisticRepository;
    }

    //User registrieren, default Settings anlegen
    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody AuthRequest authRequest) {
        //Prüfen ob Username schon in der DB
        Optional<User> userOptional = userRepository.findUserByUsername(authRequest.getUsername());

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        UserSettings userSettings = new UserSettings();
        userSettings = userSettingsRepository.save(userSettings);

        User user = new User();
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setUsername(authRequest.getUsername());
        user.setPublicName(authRequest.getPublicName());
        user.setUserSettings(userSettings);

        User created = userRepository.save(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){

        //Überprüft ob User existiert und das Password stimmt
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }

    //User registrieren, default Settings anlegen USER übergeben
    @PostMapping(value = "/register/user")
    public ResponseEntity<User> register(@RequestBody UserRequest userRequest) {
        //Prüfen ob Username schon in der DB


        System.out.println("Hallo"+userRequest.getUser().getUsername());
        Optional<User> userOptional = userRepository.findUserByUsername(userRequest.getUser().getUsername());

        System.out.println(userRequest);

        if (userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        UserSettings userSettings = new UserSettings();
        userSettings = userSettingsRepository.save(userSettings);

        User user = new User();
        user.setPassword(passwordEncoder.encode(userRequest.getUser().getPassword()));
        user.setUsername(userRequest.getUser().getUsername());
        user.setPublicName(userRequest.getUser().getPublicName());
        user.setUserSettings(userSettings);

        User created = userRepository.save(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping(value = "/login/user")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest){

        //Überprüft ob User existiert und das Password stimmt
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getUser().getUsername(),
                        userRequest.getUser().getPassword()
                )
        );
        return ResponseEntity.ok(jwtTokenProvider.generateToken(authentication));
    }


}
