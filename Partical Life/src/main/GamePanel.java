package main;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 1;

    Color pixelColor = Color.white;

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameTheard(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread!=null){

            //update particle info
            update();
            //draw particles
            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();
            if(remainingTime < 0){
                remainingTime = 0;
            }
            remainingTime = remainingTime/1000000;
            try{
                Thread.sleep((long)remainingTime);
            }  
            catch(Exception e){
                e.printStackTrace();
            }          
            nextDrawTime += drawInterval;
        }
    }

    public void update(){
        pixelColor = (pixelColor==Color.yellow)?Color.white:Color.yellow;
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(pixelColor);
        g2.fillRect(screenWidth/2,screenHeight/2,5,5);
        g2.dispose();
    }
}
