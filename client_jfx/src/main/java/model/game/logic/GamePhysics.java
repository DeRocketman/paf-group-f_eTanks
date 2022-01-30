package model.game.logic;

public class GamePhysics {

    public static final double ROUND_TIME = 30;
    public static final double FRAMES_PER_SECOND = 60;
    public static final double BULLET_DELAY = 22.0 / FRAMES_PER_SECOND;
    public static final double BULLET_SPEED = 3.5;
    public static final double BULLET_ROTATION_UP = 360.0;
    public static final double BULLET_ROTATION_DOWN = 180.0;
    public static final double BULLET_ROTATION_LEFT = 270.0;
    public static final double BULLET_ROTATION_RIGHT = 90.0;
    public static final double TANK_SPEED = 8.0;
    public static final double TANK_QUARTER_ROTATION_DURATION = 0.2;
    public static final String TANK_MOVE_UP_COURSE = "360.0";
    public static final String TANK_MOVE_DOWN_COURSE = "180.0";
    public static final String TANK_MOVE_RIGHT_COURSE = "90.0";
    public static final String TANK_MOVE_LEFT_COURSE = "270.0";




    public static final long PLAYER_PREPARATION_TIME_SECONDS = 30;
    public static final long GAME_TIME_SECONDS = 180;
    public static final long GAME_TIME = GAME_TIME_SECONDS * 1000000000L;
    public static final double GAME_WIDTH = 1280;
    public static final double GAME_HEIGHT = 800;

    public static final int KILL_POINTS = 1000;
    public static final int ITEM_POINTS = 300;


    public static final double ELEMENT_SIZE = 40.0;
}
