package entities;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;


public class Snake implements Entity{


    private LinkedList<Position> snake;
    private Direction direction = Direction.RIGHT;
    private GridView grid;

    public Snake(GridView grid, Position...initialPosition){
        this.snake= new LinkedList<>(Arrays.asList(initialPosition));
        this.grid = grid;
    }

    @Override
    public void update() {

        int x = snake.getFirst().x;
        int y = snake.getFirst().y;
        switch (direction) {
            case UP -> moveHead(x, y - 1);
            case DOWN -> moveHead(x, y + 1);
            case LEFT -> moveHead(x - 1, y);
            case RIGHT -> moveHead(x + 1, y);
        }
    }

    private void moveHead(int x, int y){

        snake.removeLast();
        snake.addFirst(new Position(x,y));
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public void extend(){
        snake.addLast(snake.getLast());
    }

    @Override
    public void draw(Graphics graphics) {
        snake.forEach((p)->this.grid.draw(graphics, Color.white, p));

    }
}
