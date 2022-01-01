package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thl.gruppef.etankrest.etankrestapi.entities.GameStatistic;
import thl.gruppef.etankrest.etankrestapi.entities.User;
import thl.gruppef.etankrest.etankrestapi.repository.GameStatisticRepository;
import thl.gruppef.etankrest.etankrestapi.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_game_statistic")
public class GameStatisticController {

    private GameStatisticRepository userGameStatisticRepository;
    private UserRepository userRepository;

    public GameStatisticController(GameStatisticRepository userGameStatisticRepository,UserRepository userRepository) {
        this.userGameStatisticRepository = userGameStatisticRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<GameStatistic> index() {
        return userGameStatisticRepository.findAll();
    }

    @PostMapping("new/{userId}")
    public ResponseEntity<GameStatistic> test( @RequestBody GameStatistic gameStatistic, @PathVariable Long userId){

        Optional<User> userOptional = userRepository.findUserById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user =  userRepository.findUserById(userId).get();

        GameStatistic newGameStatistic = gameStatistic;
        newGameStatistic.setUser(user);
        newGameStatistic = userGameStatisticRepository.save(newGameStatistic);

        return ResponseEntity.ok(gameStatistic);
    }
}
