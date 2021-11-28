package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thl.gruppef.etankrest.etankrestapi.repository.UserGameStatisticRepository;

@RestController
@RequestMapping("/user_game_statistic")
public class UserGameStatisticController {
    private UserGameStatisticRepository userGameStatisticRepository;

    public UserGameStatisticController(UserGameStatisticRepository userGameStatisticRepository) {
        this.userGameStatisticRepository = userGameStatisticRepository;
    }
}
