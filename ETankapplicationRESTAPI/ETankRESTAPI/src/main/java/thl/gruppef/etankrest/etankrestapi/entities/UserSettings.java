package thl.gruppef.etankrest.etankrestapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.input.KeyCode;
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

    private KeyCode showStatisticKey;

    private KeyCode moveUpKey;

    private KeyCode moveLeftKey;

    private KeyCode moveRightKey;

    private KeyCode moveDownKey;

    private KeyCode fireMainWeaponKey;

    private KeyCode fireSecondaryWeaponKey;


    public UserSettings() {
        this.gameSoundVolume = 100;
        this.gameMusicVolume = 100;
        this.gameSoundOn = true;
        this.gameMusicOn = true;
        this.showStatisticKey = KeyCode.TAB;
        this.moveUpKey = KeyCode.W;
        this.moveLeftKey = KeyCode.A;
        this.moveRightKey = KeyCode.D;
        this.moveDownKey = KeyCode.S;
        this.fireMainWeaponKey = KeyCode.SPACE;
        this.fireSecondaryWeaponKey = KeyCode.COMMAND;
    }
}
