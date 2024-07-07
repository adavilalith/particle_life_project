package main;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 32;
    final int maxScreenRow = 18;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 1;

    Color pixelColor = Color.white;

    Particles particlesObj;

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.particlesObj = new Particles();

    }

    public void startGameTheard(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread!=null){
            repaint();

            //update particle info
            update();
            //draw particles

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
        particlesObj.update();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        ArrayList<Particle>  particles = particlesObj.getParticles();
        // g2.setColor(pixelColor);
        // g2.fillOval(screenWidth-7,screenHeight-7,7,7);
        for(int i=0;i<particles.size();i++){
            Particle p = particles.get(i);
            g2.setColor(p.c);
            g2.fillOval(p.x,p.y,7,7);
        }
        g2.dispose();
    }
}
