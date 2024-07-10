package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyCode()) {
            case 32:
                GamePanel.paused = !GamePanel.paused;
                break;
            case 27:
                GamePanel.paused = !GamePanel.paused;
                break;
            default:
                break;
        }
        System.out.println(e.getKeyCode()+"|"+GamePanel.paused);
        
    }
    
}
