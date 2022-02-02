package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thl.gruppef.etankrest.etankrestapi.entities.UserSettings;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;

import java.util.List;
import java.util.Optional;

/**
 * Handles requests related to user statistics
 */
@RestController
@RequestMapping("/user_settings")
public class UserSettingsController {

    private UserSettingsRepository userSettingsRepository;


    /**
     * Constructor of the class
     *
     * @param userSettingsRepository
     */
    public UserSettingsController(UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;
    }

    /**
     * Finds all userSettings
     *
     *  @return     List of all UserSettings
     */
    @GetMapping("")
    public List<UserSettings> index() {
        return userSettingsRepository.findAll();
    }

    /**
     * Updates a userSetting
     *
     * @param userSettings   the userSetting to update
     * @return  the updated userSetting
     */
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
