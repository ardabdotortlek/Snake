import java.awt.*;

public class Food {
    Rect background;
    Snake snake;
    int width, height;
    Color color;
    Rect rect;

    public int xPadding;

    public boolean isSpawned = false;

    public Food(Rect background, Snake snake, int width, int height, Color color) {
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rect = new Rect(0, 0, width, height);

        xPadding = (int)((Constants.TILE_WIDTH - this.width) / 2.0);
    }

    public void spawn() {
        do {
            double randX = (int)(Math.random() * (int)(background.getWidth() / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.getX();
            double randY = (int)(Math.random() * (int)(background.getHeight() / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.getY();
            this.rect.setX(randX);
            this.rect.setY(randY);
        } while(snake.intersectingWithRect(this.rect));
        this.isSpawned = true;
    }

    public void update(double dt) {
        if (snake.intersectingWithRect(this.rect)) {
            snake.grow();
            this.rect.setY(100);
            this.rect.setY(-100);
            isSpawned = false;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect((int)this.rect.getX() + xPadding, (int)this.rect.getY() + xPadding, width, height);
    }

}
