import java.awt.*;

public class SnakeGame {

    public static final int MAX_SLEEP_SKIPPED = 50;
    public static final int MAX_SKIPS = 5;
    public static final int FPS = 20;
    public static final long NANO_SECS_TIME_PER_FRAME = 1_000_000_000L / FPS;

    public static final Color GAME_COLOR = Color.BLACK;
    public static final Color COLOR_TEXT = Color.GREEN;

    public static final int X_TILE_COUNT = 50;
    public static final int TILE_WIDTH = 25;
    public static final Color COLOR_SNAKE_HEAD = new Color(0x343212);
    public static final Color COLOR_SNAKE_TAIL = new Color(0x123561);

    private static final int Y_TILE_COUNT = 25;
    public static final int TILE_HEIGHT = 25;

    public final static int GAME_WIDTH = X_TILE_COUNT * TILE_WIDTH;
    public final static int GAME_HEIGHT = Y_TILE_COUNT * TILE_HEIGHT;
}
