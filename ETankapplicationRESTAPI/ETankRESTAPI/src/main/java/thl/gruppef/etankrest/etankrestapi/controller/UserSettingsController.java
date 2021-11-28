package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thl.gruppef.etankrest.etankrestapi.repository.UserSettingsRepository;

import javax.persistence.OneToOne;

@RestController
@RequestMapping("/user_settings")
public class UserSettingsController {

    private UserSettingsRepository userSettingsRepository;

    public UserSettingsController(UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;
    }


}
