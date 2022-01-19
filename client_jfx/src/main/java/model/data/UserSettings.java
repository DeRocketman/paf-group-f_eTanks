/**
 * The UserSettings class is the model for the control keymap and the principle game settings of each user.
 * @author Gruppe F (Patterns and Frameworks WiSe21)
 *
 */

package model.data;

//@todo: Es bleibt zu ueberlegen, ob die strings für die Steuerung noch zu ASCII "gemappt" werden muessen. (z.B. space)

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The type User settings.
 */
public class UserSettings {

    private long id;

    private int gameSoundVolume;
    private int gameMusicVolume;
    private boolean gameSoundOn;
    private boolean gameMusicOn;
    private KeyCode moveUpKey;
    private KeyCode moveLeftKey;
    private KeyCode moveDownKey;
    private KeyCode moveRightKey;
    private KeyCode fireMainWeaponKey;
    private KeyCode fireSecondaryWeaponKey;
    private KeyCode showStatisticKey;

    /**
     * Instantiates a new User settings.
     */
    public UserSettings() {
    }


    /**
     * Sets default settings.
     */
    public void setDefaultSettings() {
        this.id = 0;
        this.gameSoundVolume = 100;
        this.gameMusicVolume = 100;
        this.gameSoundOn = true;
        this.gameMusicOn = true;
        this.fireMainWeaponKey = KeyCode.SPACE;
        this.moveUpKey = KeyCode.W;
        this.moveLeftKey = KeyCode.A;
        this.moveDownKey = KeyCode.S;
        this.moveRightKey = KeyCode.D;
        this.fireSecondaryWeaponKey = KeyCode.COMMAND;
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

    public KeyCode getMoveUpKey() {
        return moveUpKey;
    }

    public void setMoveUpKey(KeyCode moveUpKey) {
        this.moveUpKey = moveUpKey;
    }

    public KeyCode getMoveLeftKey() {
        return moveLeftKey;
    }

    public void setMoveLeftKey(KeyCode moveLeftKey) {
        this.moveLeftKey = moveLeftKey;
    }

    public KeyCode getMoveDownKey() {
        return moveDownKey;
    }

    public void setMoveDownKey(KeyCode moveDownKey) {
        this.moveDownKey = moveDownKey;
    }

    public KeyCode getMoveRightKey() {
        return moveRightKey;
    }

    public void setMoveRightKey(KeyCode moveRightKey) {
        this.moveRightKey = moveRightKey;
    }

    public KeyCode getFireMainWeaponKey() {
        return fireMainWeaponKey;
    }

    public void setFireMainWeaponKey(KeyCode fireMainWeaponKey) {
        this.fireMainWeaponKey = fireMainWeaponKey;
    }

    public KeyCode getFireSecondaryWeaponKey() {
        return fireSecondaryWeaponKey;
    }

    public void setFireSecondaryWeaponKey(KeyCode fireSecondaryWeaponKey) {
        this.fireSecondaryWeaponKey = fireSecondaryWeaponKey;
    }

    public KeyCode getShowStatisticKey() {
        return showStatisticKey;
    }

    public void setShowStatisticKey(KeyCode showStatisticKey) {
        this.showStatisticKey = showStatisticKey;
    }



    /*    *//**
     * Gets move up key.
     *
     * @return the move up key
     *//*
    public String getMoveUpKey() {
        return moveUpKey;
    }

    *//**
     * Sets move up key.
     *
     * @param moveUpKey the move up key
     *//*
    public void setMoveUpKey(String moveUpKey) {
        this.moveUpKey = moveUpKey;
    }

    *//**
     * Gets move left key.
     *
     * @return the move left key
     *//*
    public String getMoveLeftKey() {
        return moveLeftKey;
    }

    *//**
     * Sets move left key.
     *
     * @param moveLeftKey the move left key
     *//*
    public void setMoveLeftKey(String moveLeftKey) {
        this.moveLeftKey = moveLeftKey;
    }

    *//**
     * Gets move down key.
     *
     * @return the move down key
     *//*
    public String getMoveDownKey() {
        return moveDownKey;
    }

    *//**
     * Sets move down key.
     *
     * @param moveDownKey the move down key
     *//*
    public void setMoveDownKey(String moveDownKey) {
        this.moveDownKey = moveDownKey;
    }

    *//**
     * Gets move right key.
     *
     * @return the move right key
     *//*
    public String getMoveRightKey() {
        return moveRightKey;
    }

    *//**
     * Sets move right key.
     *
     * @param moveRightKey the move right key
     *//*
    public void setMoveRightKey(String moveRightKey) {
        this.moveRightKey = moveRightKey;
    }

    *//**
     * Gets fire main weapon key.
     *
     * @return the fire main weapon key
     *//*
    public String getFireMainWeaponKey() {
        return fireMainWeaponKey;
    }

    *//**
     * Sets fire main weapon key.
     *
     * @param fireMainWeaponKey the fire main weapon key
     *//*
    public void setFireMainWeaponKey(String fireMainWeaponKey) {
        this.fireMainWeaponKey = fireMainWeaponKey;
    }

    *//**
     * Gets fire secondary weapon key.
     *
     * @return the fire secondary weapon key
     *//*
    public String getFireSecondaryWeaponKey() {
        return fireSecondaryWeaponKey;
    }

    *//**
     * Sets fire secondary weapon key.
     *
     * @param fireSecondaryWeaponKey the fire secondary weapon key
     *//*
    public void setFireSecondaryWeaponKey(String fireSecondaryWeaponKey) {
        this.fireSecondaryWeaponKey = fireSecondaryWeaponKey;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShowStatisticKey() {
        return showStatisticKey;
    }

    public void setShowStatisticKey(String showStatisticKey) {
        this.showStatisticKey = showStatisticKey;
    }
}*/
}
