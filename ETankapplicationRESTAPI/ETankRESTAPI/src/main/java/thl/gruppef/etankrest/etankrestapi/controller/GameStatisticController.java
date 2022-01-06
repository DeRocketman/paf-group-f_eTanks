package thl.gruppef.etankrest.etankrestapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    private GameStatisticRepository gameStatisticRepository;
    private UserRepository userRepository;

    public GameStatisticController(GameStatisticRepository userGameStatisticRepository,UserRepository userRepository) {
        this.gameStatisticRepository = userGameStatisticRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<GameStatistic> index() {
        return gameStatisticRepository.findAll();
    }

    @PostMapping("new/{userId}")
    public ResponseEntity<GameStatistic> newStatistic( @RequestBody GameStatistic gameStatistic, @PathVariable Long userId){

        Optional<User> userOptional = userRepository.findUserById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User user =  userRepository.findUserById(userId).get();

        GameStatistic newGameStatistic = gameStatistic;
        newGameStatistic.setUserId(user.getId());
        newGameStatistic.setUserName(user.getUsername());
        newGameStatistic = gameStatisticRepository.save(newGameStatistic);

        return ResponseEntity.ok(gameStatistic);
    }

    @GetMapping("/{userId}")
    public List<GameStatistic> gameStatistics(@PathVariable Long userId) {
        return gameStatisticRepository.findGameStatisticByUserId(userId);
    }

    @GetMapping("/sorted")
    public List<GameStatistic> sorted() {
        return gameStatisticRepository.OrderByGamePointsDesc();
    }

    @GetMapping("/highscorelist/{size}")
    public List<GameStatistic> highscorelist(@PathVariable int size){
        Page<GameStatistic> gameStatisticPageTest = gameStatisticRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "gamePoints"));
        List<GameStatistic> gameStatisticsTop = gameStatisticPageTest.getContent();
        return gameStatisticsTop;
    }
}
