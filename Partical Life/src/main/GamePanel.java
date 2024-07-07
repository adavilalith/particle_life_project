package main;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable{
    
    
    final int screenWidth = 1280;
    final int screenHeight = 720;

    final int FPS = 60;

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
    
        for(int i=0;i<particles.size();i++){
            Particle p = particles.get(i);
            g2.setColor(p.c);
            g2.fillRect(Math.abs((int)p.x),Math.abs((int)p.y),2,2);
        }
        g2.dispose();
    }
}
