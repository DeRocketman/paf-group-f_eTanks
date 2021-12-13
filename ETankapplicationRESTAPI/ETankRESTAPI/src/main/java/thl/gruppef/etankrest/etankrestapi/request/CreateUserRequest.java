package thl.gruppef.etankrest.etankrestapi.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    //User
    private String username;

    private String password;

    private String publicName;

    private String image;

    //UserSettings
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
