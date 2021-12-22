package thl.gruppef.etankrest.etankrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import thl.gruppef.etankrest.etankrestapi.entities.GameStatistic;

//TODO: Refactoring
public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {
}


