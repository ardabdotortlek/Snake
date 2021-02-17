import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuScene extends Scene {
    KeyListener keyListener;

    public MenuScene(KeyListener keyListener){
        this.keyListener = keyListener;
    }

    @Override
    public void update(double dt) {
        if(keyListener.isKeyPressed(KeyEvent.VK_ENTER))
            Window.getWindow().changeState(Constants.GAME_STATE);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        graphics2D.setFont(new Font("Times New Roman", Font.BOLD,80));
        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawString("SNAKE",Constants.SCREEN_WIDTH/2 - 155, 200);
        graphics2D.setFont(new Font("Times New Roman", Font.BOLD,30));
        graphics2D.drawString("Press Enter To Start!",Constants.SCREEN_WIDTH/2 - 150, Constants.SCREEN_HEIGHT/2+100);
    }
}
