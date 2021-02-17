import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    private static Window window;
    private boolean running;
    private int currentState;
    private Scene currentScene;
    private KeyListener keyListener = new KeyListener();;

    public Window(){
        this.running = true;
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setVisible(true);
        this.addKeyListener(keyListener);
        this.changeState(Constants.MENU_STATE);
    }

    public static Window getWindow(){
        if(Window.window == null)
            Window.window = new Window();
        return Window.window;
    }

    public void changeState(int state){
        switch(state){
            case Constants.MENU_STATE:
                currentState = state;
                currentScene = new MenuScene(keyListener);
                break;
            case Constants.GAME_STATE:
                currentState = state;
                currentScene = new GameScene(keyListener);
                break;
            default:
                currentScene = null;
                currentState = -1;
        }
    }

    public void drawFirstBuffer(Graphics g){
        currentScene.draw(g);
    }

    public void update(double dt){
        Image image = createImage(this.getWidth(),this.getHeight());
        Graphics bufferGraph = image.getGraphics();
        this.drawFirstBuffer(bufferGraph);
        this.getGraphics().drawImage(image,0,0,this);
        currentScene.update(dt);
    }

    @Override
    public void run() {
        double lastFrame = 0.0;
        while(this.running){
            double time = Time.getTime();
            double deltaTime = time - lastFrame;
            lastFrame = time;
            update(deltaTime);
        }
    }
}
