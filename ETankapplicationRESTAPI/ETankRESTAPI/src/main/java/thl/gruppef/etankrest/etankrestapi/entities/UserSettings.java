package thl.gruppef.etankrest.etankrestapi.entities;

import javax.persistence.*;
import java.lang.management.LockInfo;

@Entity
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
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

    private String fireMainWaeponKey;

    private String fireSecondWeaponKey;
}
