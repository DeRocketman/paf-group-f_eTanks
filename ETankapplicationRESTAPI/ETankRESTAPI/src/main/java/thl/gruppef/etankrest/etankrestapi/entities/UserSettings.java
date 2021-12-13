package thl.gruppef.etankrest.etankrestapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import thl.gruppef.etankrest.etankrestapi.request.CreateUserRequest;

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

    public UserSettings(CreateUserRequest createUserRequest) {

        this.gameSoundVolume = createUserRequest.getGameSoundVolume();
        this.gameMusicVolume = createUserRequest.getGameMusicVolume();
        this.gameSoundOn = createUserRequest.isGameSoundOn();
        this.gameMusicOn = createUserRequest.isGameMusicOn();
        this.showStatisticKey = createUserRequest.getShowStatisticKey();
        this.moveUpKey = createUserRequest.getMoveUpKey();
        this.moveLeftKey = createUserRequest.getMoveLeftKey();
        this.moveRightKey = createUserRequest.getMoveRightKey();
        this.moveDownKey = createUserRequest.getMoveDownKey();
        this.fireMainWeaponKey = createUserRequest.getFireMainWaeponKey();
        this.fireSecondWeaponKey = createUserRequest.getFireSecondWeaponKey();
    }

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
