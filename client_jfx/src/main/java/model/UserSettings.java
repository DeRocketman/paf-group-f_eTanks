/**
 * The UserSettings class is the model for the control keymap and the principle game settings of each user.
 * @author Gruppe F (Patterns and Frameworks WiSe21)
 *
 */

package model;

//@todo: Es bleibt zu ueberlegen, ob die strings für die Steuerung noch zu ASCII "gemappt" werden muessen. (z.B. space)
/**
 * The type User settings.
 */
public class UserSettings {
    private int gameSoundVolume;
    private int gameMusicVolume;
    private boolean gameSoundOn;
    private boolean gameMusicOn;
    private String moveUpKey;
    private String moveLeftKey;
    private String moveDownKey;
    private String moveRightKey;
    private String fireMainWeaponKey;
    private String fireSecondaryWeaponKey;

    /**
     * Instantiates a new User settings.
     */
    public UserSettings() {
    }

    /**
     * Sets default settings.
     */
    public void setDefaultSettings() {
        this.gameSoundVolume = 100;
        this.gameMusicVolume = 100;
        this.gameSoundOn = true;
        this.gameMusicOn = true;
        this.moveUpKey = "w";
        this.moveLeftKey = "a";
        this.moveDownKey = "s";
        this.moveRightKey = "d";
        this.fireMainWeaponKey = "space";
        this.fireSecondaryWeaponKey= "lctl";
    }

    /**
     * Gets game sound volume.
     *
     * @return the game sound volume
     */
    public int getGameSoundVolume() {
        return gameSoundVolume;
    }

    /**
     * Sets game sound volume.
     *
     * @param gameSoundVolume the game sound volume
     */
    public void setGameSoundVolume(int gameSoundVolume) {
        this.gameSoundVolume = gameSoundVolume;
    }

    /**
     * Gets game music volume.
     *
     * @return the game music volume
     */
    public int getGameMusicVolume() {
        return gameMusicVolume;
    }

    /**
     * Sets game music volume.
     *
     * @param gameMusicVolume the game music volume
     */
    public void setGameMusicVolume(int gameMusicVolume) {
        this.gameMusicVolume = gameMusicVolume;
    }

    /**
     * Is game sound on boolean.
     *
     * @return the boolean
     */
    public boolean isGameSoundOn() {
        return gameSoundOn;
    }

    /**
     * Sets game sound on.
     *
     * @param gameSoundOn the game sound on
     */
    public void setGameSoundOn(boolean gameSoundOn) {
        this.gameSoundOn = gameSoundOn;
    }

    /**
     * Is game music on boolean.
     *
     * @return the boolean
     */
    public boolean isGameMusicOn() {
        return gameMusicOn;
    }

    /**
     * Sets game music on.
     *
     * @param gameMusicOn the game music on
     */
    public void setGameMusicOn(boolean gameMusicOn) {
        this.gameMusicOn = gameMusicOn;
    }

    /**
     * Gets move up key.
     *
     * @return the move up key
     */
    public String getMoveUpKey() {
        return moveUpKey;
    }

    /**
     * Sets move up key.
     *
     * @param moveUpKey the move up key
     */
    public void setMoveUpKey(String moveUpKey) {
        this.moveUpKey = moveUpKey;
    }

    /**
     * Gets move left key.
     *
     * @return the move left key
     */
    public String getMoveLeftKey() {
        return moveLeftKey;
    }

    /**
     * Sets move left key.
     *
     * @param moveLeftKey the move left key
     */
    public void setMoveLeftKey(String moveLeftKey) {
        this.moveLeftKey = moveLeftKey;
    }

    /**
     * Gets move down key.
     *
     * @return the move down key
     */
    public String getMoveDownKey() {
        return moveDownKey;
    }

    /**
     * Sets move down key.
     *
     * @param moveDownKey the move down key
     */
    public void setMoveDownKey(String moveDownKey) {
        this.moveDownKey = moveDownKey;
    }

    /**
     * Gets move right key.
     *
     * @return the move right key
     */
    public String getMoveRightKey() {
        return moveRightKey;
    }

    /**
     * Sets move right key.
     *
     * @param moveRightKey the move right key
     */
    public void setMoveRightKey(String moveRightKey) {
        this.moveRightKey = moveRightKey;
    }

    /**
     * Gets fire main weapon key.
     *
     * @return the fire main weapon key
     */
    public String getFireMainWeaponKey() {
        return fireMainWeaponKey;
    }

    /**
     * Sets fire main weapon key.
     *
     * @param fireMainWeaponKey the fire main weapon key
     */
    public void setFireMainWeaponKey(String fireMainWeaponKey) {
        this.fireMainWeaponKey = fireMainWeaponKey;
    }

    /**
     * Gets fire secondary weapon key.
     *
     * @return the fire secondary weapon key
     */
    public String getFireSecondaryWeaponKey() {
        return fireSecondaryWeaponKey;
    }

    /**
     * Sets fire secondary weapon key.
     *
     * @param fireSecondaryWeaponKey the fire secondary weapon key
     */
    public void setFireSecondaryWeaponKey(String fireSecondaryWeaponKey) {
        this.fireSecondaryWeaponKey = fireSecondaryWeaponKey;
    }
}
