package thl.gruppef.etankrest.etankrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thl.gruppef.etankrest.etankrestapi.entities.GameStatistic;

import java.util.Optional;

public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {
    Optional<GameStatistic> findGameStatisticByGameNumber(long gameNumber);
}


