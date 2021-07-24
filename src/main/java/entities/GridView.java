package entities;

import java.awt.*;

public class GridView {
    int size;
    int width;
    int squareWidth;




    public GridView(int size, int width){
        this.size = size;
        this.width = width;
        this.squareWidth = width/size;

    }





    public void draw(Graphics g, Color color, Position p){
        g.setColor(color);
        g.fillRect(p.x*squareWidth, p.y*squareWidth, squareWidth,squareWidth);

    }

}
