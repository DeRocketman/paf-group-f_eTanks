package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thl.gruppef.etankrest.etankrestapi.entities.UserSettings;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_settings")
public class UserSettingsController {

    private UserSettingsRepository userSettingsRepository;

    public UserSettingsController(UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;
    }

    @GetMapping("")
    public List<UserSettings> index() {
        return userSettingsRepository.findAll();
    }


    @PostMapping(value = "/update_settings")
    public ResponseEntity<UserSettings> updateUserSettings(@RequestBody UserSettings userSettings) {

        Optional<UserSettings> userSettingsOptional = userSettingsRepository.findUserSettingsById(userSettings.getId());

        if (!userSettingsOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        userSettingsRepository.save(userSettings);

        return ResponseEntity.ok(userSettings);
    }

}
