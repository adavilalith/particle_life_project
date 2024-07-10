package main;

import java.awt.*;

import javax.swing.JPanel;

public class MenuPanel extends JPanel implements Runnable {
    final int screenWidth = 1280;
    final int screenHeight = 720;

    final int FPS = 60;

    Thread menuThread;

    public MenuPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.red);
        this.setDoubleBuffered(true);

        KeyHandler k = new KeyHandler();
        this.addKeyListener(k);
        this.setFocusable(true);
    }

    void startMenuThread(){
        menuThread = new Thread(this);
        menuThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(menuThread!=null){

            System.out.print("");
            repaint();

            update();
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
    
    void update(){}
    public void paintComponent(Graphics g){
            g.drawString("hi", ALLBITS, ABORT);
    } 
}
