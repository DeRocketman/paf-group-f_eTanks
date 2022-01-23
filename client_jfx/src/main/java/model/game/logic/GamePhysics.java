package model.game.logic;

public class GamePhysics {

    public static final double ROUND_TIME = 5;
    public static final double FRAMES_PER_SECOND = 60;
    public static final double DELAY_SECOND = 1.0 / FRAMES_PER_SECOND;
    public static final double TANK_SPEED = 10.0;
    public static final double TANK_QUARTER_ROTATION_DURATION = 0.2;
    public static final double BULLET_SPEED = 3000.0;
    public static final double SHOOT_LENGTH = 1200.0;


    public static final long PLAYER_PREPARATION_TIME_SECONDS = 30;
    public static final long GAME_TIME_SECONDS = 180;
    public static final long GAME_TIME = GAME_TIME_SECONDS * 1000000000L;
    public static final double GAME_WIDTH = 1280;
    public static final double GAME_HEIGHT = 800;

    public static final int KILL_POINTS = 1000;
    public static final int ITEM_POINTS = 300;


    public static final double ELEMENT_SIZE = 40.0;
}
