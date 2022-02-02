package thl.gruppef.etankrest.etankrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import thl.gruppef.etankrest.etankrestapi.entities.GameStatistic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public interface GameStatisticRepository extends JpaRepository<GameStatistic, Long> {
    Optional<GameStatistic> findGameStatisticByGameNumber(long gameNumber);

    @PersistenceContext
    EntityManager entityManager = null;

    @Query ("FROM GameStatistic WHERE user_id =?1")
    List<GameStatistic> findGameStatisticByUserId(long user_id);

    @Query ("FROM GameStatistic ORDER BY gamePoints DESC")
    List<GameStatistic> OrderByGamePointsDesc();
}


