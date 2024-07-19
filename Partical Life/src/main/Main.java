package main;


import javax.swing.JFrame;

public class Main {
    
    static JFrame window;
    static MenuPanel menuPanel;
    static GamePanel gamePanel;
    public static void main(String[] args) throws Exception {
        window = new JFrame();

        gamePanel = new GamePanel();
        gamePanel.setBounds(280,0,1000,720);
       

        menuPanel = new MenuPanel();
        menuPanel.setBounds(0,0,280,720);
        
        KeyHandler k = new KeyHandler();
        window.addKeyListener(k);
        
        window.setSize(1280,720);
        window.setLayout(null);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Particle Life");
        window.setVisible(true);

        window.add(menuPanel);
        menuPanel.startMenuThread();

        gamePanel.startGameTheard();
        window.add(gamePanel);
        
    }
}
