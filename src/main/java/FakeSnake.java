import entities.Direction;
import entities.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static entities.Direction.*;

public class FakeSnake implements KeyListener {
    private int headPositionX=0;
    private int headPositionY =0;
    private int speed = 5;

    private Snake snake;

    private Direction direction = Direction.RIGHT;

    public FakeSnake(Snake snake){
        this.snake = snake;
    }

    public void move(){

    }


    public void draw(Graphics graphics){
        graphics.setColor(Color.GREEN);
        graphics.fillRect(headPositionX, headPositionY, 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: // UP
                if(this.direction == DOWN)break;
                setDirection(Direction.UP);
                headPositionY -= 5;
                break;
            case KeyEvent.VK_DOWN: // DOWN
                if(this.direction == UP)break;
                setDirection(DOWN);
                headPositionY += 5;
                break;
            case KeyEvent.VK_RIGHT: // RIGHT
                if(this.direction == LEFT)break;
                setDirection(Direction.RIGHT);
                headPositionX += 5;
                break;
            case KeyEvent.VK_LEFT: // LEFT
                if(this.direction == RIGHT)break;
                setDirection(Direction.LEFT);
                headPositionX -= 5;
                break;
        }
    }
    
    private void setDirection(Direction direction){
        this.direction = direction;
        this.snake.setDirection(direction);
    }


}
