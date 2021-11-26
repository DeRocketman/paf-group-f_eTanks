package model;

public class GamePhysics {
    public static final double FRAMES_PER_SECOND = 60;
    public static final double DELAY_SECOND = 1.0 / FRAMES_PER_SECOND;
    public static final double TANK_SPEED = 10.0;
    public static final double TANK_QUARTER_ROTATION_DURATION = 0.25;

    public static final long GAME_TIME_SECONDS = 180;
    public static final long GAME_TIME = GAME_TIME_SECONDS * 1000000000L;

    public static final int KILL_POINTS = 1000;
}
