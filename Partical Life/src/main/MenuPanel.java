package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;


public class MenuPanel extends JPanel implements Runnable {
    final int screenWidth = 280;
    final int screenHeight = 720;

    static JButton randomMatBtn, startStopBtn,setCounts;
    TextField gcf,ycf,rcf,bcf,mcf;

    final int FPS = 40;


    int gc=500,yc=500,rc=500,bc=500,mc=500;

    Thread menuThread;

    public MenuPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);

        


        randomMatBtn = new JButton("set Random Matrix");
        randomMatBtn.addActionListener(new randomMatBtnPress());
        this.add(randomMatBtn);

        startStopBtn = new JButton("Start");
        startStopBtn.addActionListener(new startStopButtonPress());
        this.add(startStopBtn);

        

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
        super.paintComponent(g);
    } 
}

class randomMatBtnPress implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
            System.out.println("setting rand");
            GamePanel.paused = true;
            Particles.setDefaultParticlesInfo();
            Particles.setRandomAttractionMatrix();
            
            GamePanel.paused = false; 
            if(GamePanel.paused)
                MenuPanel.startStopBtn.setText("Start");
            else
                MenuPanel.startStopBtn.setText("Pause");

    }
    
}


class startStopButtonPress implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        GamePanel.paused = !GamePanel.paused; 
        if(GamePanel.paused)
            MenuPanel.startStopBtn.setText("Start");
        else
            MenuPanel.startStopBtn.setText("Pause");

    }

}

class setCountBtnPress implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}