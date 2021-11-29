package thl.gruppef.etankrest.etankrestapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GameStatistic extends IdentifiedEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long gameNumber;

    private int gamePoints;

    private int gameWins;

    private int kills;

    private int deaths;

    private int shots;

    private int hitPoints;

    private float killDeathRate;

    private float hitRate;

    private boolean winner;

}
