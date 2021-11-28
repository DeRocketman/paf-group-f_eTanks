package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thl.gruppef.etankrest.etankrestapi.entities.UserGameStatistic;
import thl.gruppef.etankrest.etankrestapi.entities.UserSettings;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;

import javax.persistence.OneToOne;
import java.util.List;

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
}
