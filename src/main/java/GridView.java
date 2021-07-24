import java.awt.*;

public class GridView {
    int sizeX;
    int sizeY;
    int width;

    Apple apple;

    GridView(int sizeX, int sizeY, int width, Graphics g){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.width = width;
        apple = new Apple(g, 0, 0);
    }



    private void updateApplePosition(){
        int x = (int)(Math.random()*500);
        int y = (int)(Math.random()*500);
    }

    private void updateSnakePosition(){

    }
}
