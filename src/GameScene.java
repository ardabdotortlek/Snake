import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
    KeyListener keyListener;
    Rect background, foreground;
    Snake snake;
    Food food;

    public GameScene(KeyListener keyListener){
        this.keyListener = keyListener;
        this.background = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.foreground = new Rect(Constants.TILE_WIDTH,Constants.TILE_WIDTH*2,Constants.TILE_WIDTH*31,Constants.TILE_WIDTH*22);
        this.snake = new Snake(3, 48, 72, Constants.TILE_WIDTH, Constants.TILE_WIDTH, foreground);
        this.food = new Food(foreground,snake,12,12,Color.YELLOW);
        this.food.spawn();
    }

    public void changeDirection(int direction){
        if(direction == Constants.DOWN && snake.direction != Constants.UP)
            snake.direction = direction;
        else if(direction == Constants.UP && snake.direction != Constants.DOWN)
            snake.direction = direction;
        else if(direction == Constants.RIGHT && snake.direction != Constants.LEFT)
            snake.direction = direction;
        else if(direction == Constants.LEFT && snake.direction != Constants.RIGHT)
            snake.direction = direction;
    }

    @Override
    public void update(double dt) {
        if(keyListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            changeDirection(Constants.RIGHT);
        }
        else if(keyListener.isKeyPressed(KeyEvent.VK_LEFT)){
            changeDirection(Constants.LEFT);
        }
        else if(keyListener.isKeyPressed(KeyEvent.VK_UP)){
            changeDirection(Constants.UP);
        }
        else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
            changeDirection(Constants.DOWN);
        }
        if (!food.isSpawned)
            food.spawn();

        food.update(dt);
        snake.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Rectangle2D.Double(background.getX(),background.getY(),background.getWidth(),background.getHeight()));
        graphics2D.setColor(new Color(15, 187, 137));
        graphics2D.fill(new Rectangle2D.Double(foreground.getX(),foreground.getY(),foreground.getWidth(),foreground.getHeight()));
        snake.draw(g);
        food.draw(graphics2D);
    }
}
