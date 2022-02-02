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

/**
 * Handles requests related to GameStatistics
 */
@RestController
@RequestMapping("/user_game_statistic")
public class GameStatisticController {

    private GameStatisticRepository gameStatisticRepository;
    private UserRepository userRepository;

    /**
     * Constructor of the class GameStatisticontroller
     *
     * @param userGameStatisticRepository
     * @param userRepository
     */
    public GameStatisticController(GameStatisticRepository userGameStatisticRepository,UserRepository userRepository) {
        this.gameStatisticRepository = userGameStatisticRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds all game statistics
     *
     * @return List of GameStatistics
     */
    @GetMapping("")
    public List<GameStatistic> index() {
        return gameStatisticRepository.findAll();
    }

    /**
     * Creates new GameStatistic
     *
     * @param gameStatistic     the new GameStatistic
     * @param userId            the id of the user
     * @return                  the saved gameStatistic
     */
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

    /**
     * Finds all GameStatistic of the user
     *
     * @param userId    Id of the user
     * @return          List of all gameStatistics of the user
     */
    @GetMapping("/{userId}")
    public List<GameStatistic> gameStatistics(@PathVariable Long userId) {
        return gameStatisticRepository.findGameStatisticByUserId(userId);
    }

    /**+
     * Finds highscore list of the gameStatistics
     *
     * @param size      the size of the highscore list
     * @return          highscore list of the gameStatistics
     */
    @GetMapping("/highscorelist/{size}")
    public List<GameStatistic> highscorelist(@PathVariable int size){
        Page<GameStatistic> gameStatisticPageTest = gameStatisticRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "gamePoints"));
        List<GameStatistic> gameStatisticsTop = gameStatisticPageTest.getContent();
        return gameStatisticsTop;
    }
}
