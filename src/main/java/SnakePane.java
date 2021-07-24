

import entities.GridView;
import entities.Position;
import entities.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SnakePane extends JComponent implements Runnable {


    private int sleepsSkipped;
    private boolean running;
    private int numberOfTimesGameUpdated;
    private int numOfReports;
    private int framesSkipped;
    private Thread threadGame;
    private long startTime;
    private BufferedImage gameImage;


    private Snake snake = new Snake(
            new GridView(50, 500),
        new Position(2,0), new Position(1,0), new Position(0,0)
    );
    private FakeSnake fakeSnake = new FakeSnake(snake);


    private int score;

    public SnakePane() {

        sleepsSkipped = 0;
        setFocusable(true);
        running = false;
        numberOfTimesGameUpdated = 0;
        numOfReports = 0;
        framesSkipped = 0;

        gameImage = new BufferedImage(SnakeGame.GAME_WIDTH, SnakeGame.GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);

        addKeyListener(fakeSnake);
        requestFocus();

    }


    @Override
    public void addNotify() {
        super.addNotify();
        if (!running || threadGame == null) {
            threadGame = new Thread(this, "Game Thread");
            threadGame.start();
            System.out.println("threadGame thread started");
        }
    }

    @Override
    public void run() {
        long now, lastTime, delta;
        long timeToSleepInMsec, framesToSkip;
        lastTime = System.nanoTime();
        startTime = lastTime;

        running = true;

        while (running) {
            now = System.nanoTime();
            delta = now - lastTime;
            lastTime = now;
            framesToSkip = delta / SnakeGame.NANO_SECS_TIME_PER_FRAME;

            int currentFramesSkipped = 0;
            while (framesToSkip > 1 && currentFramesSkipped < SnakeGame.MAX_SKIPS) {
                gameUpdate();
                System.out.println("Inside slow");
                currentFramesSkipped++;
                framesToSkip -= 1;
            }

            framesSkipped += currentFramesSkipped;

            gameUpdate();
            gameRender();

            timeToSleepInMsec = (SnakeGame.NANO_SECS_TIME_PER_FRAME - ((System.nanoTime() - lastTime))) / 1_000_000L;
            try {
                if (timeToSleepInMsec > 0) {
                    // We are in the good road, let's sleep for a while
                    // System.out.println("Go to sleep " + timeToSleep);
                    Thread.sleep(timeToSleepInMsec);
                } else {
                    // We are running out of time, no sleeping, unless we did not sleep for MAX_SLEEP_SKIPPED times
                    sleepsSkipped++;
                    if (sleepsSkipped >= SnakeGame.MAX_SLEEP_SKIPPED) {
                        sleepsSkipped = 0;
                        Thread.yield();
                        System.out.println("[MAX_SLEEP_SKIPPED] Sleeping because we had no chance to sleep before.");
                        // Should I add 50ms to LastTime ? Because now i have 50
                        // mils of delay ...
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long diffTimeSinceStartGame = (System.nanoTime() - startTime) / 1_000_000L;
            // Each ~2 sec set title
            if (diffTimeSinceStartGame > (2000L * (numOfReports + 1))) {
                numOfReports++;
                // Number Of Times updated / seconds passed since the start of the game
            }
        }
        System.out.println("exiting...");
        System.exit(0);
    }


    private void gameUpdate() {
        numberOfTimesGameUpdated++;
        fakeSnake.move();
        snake.update();
    }

    int x=4;
    int y =0;
    private void gameRender() {
        Graphics g = getGraphics();
        // Get a canvas to draw on

        Graphics graphics = gameImage.createGraphics(); // We must get graphics and use it


        drawBase(graphics);
        graphics.setColor(Color.white);
        graphics.fillRect(x, y, 10, 10);
        fakeSnake.draw(graphics);
        snake.draw(graphics);
        // Display the image
        g.drawImage(gameImage, 0, 0, null);


    }
    private void drawBase(Graphics graphics) {
        // Paint with base color
        graphics.setColor(SnakeGame.GAME_COLOR);
        graphics.fillRect(0, 0, gameImage.getWidth(), gameImage.getHeight());
    }

    @Override
    public Dimension getPreferredSize() {
        super.getPreferredSize();
        return new Dimension(SnakeGame.GAME_WIDTH, SnakeGame.GAME_HEIGHT);
    }

    @Override
    public Dimension getSize(Dimension dimension) {
        super.getSize(dimension);
        return new Dimension(SnakeGame.GAME_WIDTH, SnakeGame.GAME_HEIGHT);
    }

    public void onKeyExit() {
        running = false;
    }

}