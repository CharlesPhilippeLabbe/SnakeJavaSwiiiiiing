import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static MainPanel panel= new MainPanel();

    public Main(){
        add(panel);

        panel.setFocusable(true);
    }

    /**
     *Squares
     * Snake
     * Snake movement
     * apples
     * key listeners
     * collisions
     * - borders
     * - self
     * - apple
     *
     * colours
     *
     * @param args
     */
    public static void main(String[] args){


        Main frame = new Main();

        frame.setTitle("Big snake");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gameloop();
    }



    private static void gameloop(){

        long fps = 1000/60;
        long executionTime;



        while(true){
            executionTime = System.currentTimeMillis() +fps;

            panel.repaint();

            executionTime = executionTime - System.currentTimeMillis();
            System.out.println(executionTime);
            if(executionTime > 0) {
                try {
                    Thread.sleep(executionTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    static class MainPanel extends JPanel{

        int x = 0;
        int y = 5;

        public MainPanel(){

        }


        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            g.fillRect(x, y, 10, 10);;
            x+=5;
            if(x > 500){
                x =0;
                y +=5;
            }

        }
    }
}
