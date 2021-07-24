import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame {

    private static MainPanel panel= new MainPanel();

    public Main(){
        Container mainContentPane = getContentPane();
        mainContentPane.setLayout(new BorderLayout());
        SnakePane snakePane = new SnakePane();
        mainContentPane.add(snakePane, BorderLayout.CENTER);
        setUndecorated(false); //no effect when false

        // Ignore automatic paints, we are gonna paint what we need manually ourselves
        setIgnoreRepaint(true);

        pack();
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

        frame.setTitle("Big Snake");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }






    static class MainPanel extends JPanel{

        int x = 0;
        int y = 5;

        double speed = 100/1000.0;
        long last = System.currentTimeMillis();

        public MainPanel(){

        }


        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            long  now = System.currentTimeMillis();

            g.fillRect(x, y, 10, 10);
            System.out.println(speed * ( (now-last) ));
            x+=speed * ( (now-last));
            if(x > 500){
                x =0;
                y +=5;
            }




            last = now;
        }
    }
}
