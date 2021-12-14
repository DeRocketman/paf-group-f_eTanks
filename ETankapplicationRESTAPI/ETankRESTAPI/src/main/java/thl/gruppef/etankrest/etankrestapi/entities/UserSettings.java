package thl.gruppef.etankrest.etankrestapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import thl.gruppef.etankrest.etankrestapi.request.UserRequest;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    //Ohne Ignore läuft er in eine endlosschleife
    @JsonIgnore
    @OneToOne(mappedBy = "userSettings")
    private User userRef;

    private int gameSoundVolume;

    private int gameMusicVolume;

    private boolean gameSoundOn;

    private boolean gameMusicOn;

    private String showStatisticKey;

    private String moveUpKey;

    private String moveLeftKey;

    private String moveRightKey;

    private String moveDownKey;

    private String fireMainWeaponKey;

    private String fireSecondWeaponKey;


    public UserSettings() {
        this.gameSoundVolume = 100;
        this.gameMusicVolume = 100;
        this.gameSoundOn = true;
        this.gameMusicOn = true;
        this.showStatisticKey = "TAB";
        this.moveUpKey = "W";
        this.moveLeftKey = "L";
        this.moveRightKey = "R";
        this.moveDownKey = "S";
        this.fireMainWeaponKey = "N";
        this.fireSecondWeaponKey = "M";
    }
}
