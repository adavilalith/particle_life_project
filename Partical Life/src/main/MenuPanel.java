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
    static TextField gcf,ycf,rcf,bcf,mcf;

    final int FPS = 40;


    static int gc=500,yc=500,rc=500,bc=500,mc=500;

    Thread menuThread;

    public MenuPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);

        // setLayout(new GridLayout(3,1));

        randomMatBtn = new JButton("set Random Matrix");
        randomMatBtn.addActionListener(new randomMatBtnPress());
        this.add(randomMatBtn);

        startStopBtn = new JButton("Start");
        startStopBtn.addActionListener(new startStopButtonPress());
        this.add(startStopBtn);
        
        JPanel counts = new JPanel();
        gcf = new TextField(10);
        ycf = new TextField(10);
        rcf = new TextField(10);
        bcf = new TextField(10);
        mcf = new TextField(10);
        counts.setBackground(getBackground());
        counts.setLayout(new GridLayout(5,2,10,10));
        counts.add(new Label("Green"));
        counts.add(gcf);        
        counts.add(new Label("Yellow"));
        counts.add(ycf);        
        counts.add(new Label("Red"));
        counts.add(rcf);        
        counts.add(new Label("Blue"));
        counts.add(bcf);        
        counts.add(new Label("Magenata"));
        counts.add(mcf);
        
        this.add(counts);

        setCounts = new JButton("Set Counts");
        setCounts.addActionListener(new setCountBtnPress());
        this.add(setCounts);


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
        Particles.setParticlesInfo(MenuPanel.gc,MenuPanel.yc,MenuPanel.rc,MenuPanel.bc,MenuPanel.mc);
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
        GamePanel.paused = true; 
        String temp = MenuPanel.gcf.getText();
        MenuPanel.gc=500;
        if(temp.length()>0){MenuPanel.gc=Integer.parseInt(temp);}

        temp = MenuPanel.ycf.getText();
        MenuPanel.yc=500;
        if(temp.length()>0){MenuPanel.yc=Integer.parseInt(temp);}

        temp = MenuPanel.rcf.getText();
        MenuPanel.rc=500;
        if(temp.length()>0){MenuPanel.rc=Integer.parseInt(temp);}

        temp = MenuPanel.bcf.getText();
        MenuPanel.bc=500;
        if(temp.length()>0){MenuPanel.bc=Integer.parseInt(temp);}

        temp = MenuPanel.mcf.getText();
        MenuPanel.mc=500;
        if(temp.length()>0){MenuPanel.mc=Integer.parseInt(temp);}
     
        Particles.setParticlesInfo(MenuPanel.gc,MenuPanel.yc,MenuPanel.rc,MenuPanel.bc,MenuPanel.mc);
        Particles.setRandomAttractionMatrix();
            
        GamePanel.paused = false; 
        if(GamePanel.paused)
        MenuPanel.startStopBtn.setText("Start");
    else
        MenuPanel.startStopBtn.setText("Pause");
    }
    
}