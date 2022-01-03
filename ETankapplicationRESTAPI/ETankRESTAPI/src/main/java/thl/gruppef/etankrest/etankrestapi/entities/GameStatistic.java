package thl.gruppef.etankrest.etankrestapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GameStatistic extends IdentifiedEntity {

    private Long userId;

    private String userName;

    private Long gameNumber;

    private boolean winner;

    private int roundWins;

    private int gamePoints;

    private int kills;

    private int deaths;

    private int shots;

    private int hitPoints;
}
