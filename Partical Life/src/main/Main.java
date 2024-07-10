package main;


import javax.swing.JFrame;

public class Main {
    
    static JFrame window;
    static MenuPanel menuPanel;
    static GamePanel gamePanel;
    public static void main(String[] args) throws Exception {
        window = new JFrame();
        gamePanel = new GamePanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Particle Life");

        window.add(gamePanel);
        gamePanel.startGameTheard();
        window.pack();        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
