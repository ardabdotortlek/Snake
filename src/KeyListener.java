import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener {
    boolean[] keyPressed = new boolean[120];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed[e.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int keyCode){
        return keyPressed[keyCode];
    }
}
