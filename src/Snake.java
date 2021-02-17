import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Snake {
    Rect[] snakeBody = new Rect[100];
    double bodyWidth;
    double bodyHeight;
    int size;
    Rect background;

    int head = 0;
    int tail = 0;

    double waitUpdates = Constants.WAIT_TIME;
    double timeLeft = waitUpdates;

    int direction = Constants.RIGHT;

    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background){
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.background = background;

        for(int i = 0; i <= size; i++){
            Rect bodyPiece = new Rect(startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
            snakeBody[i] = bodyPiece;
            head++;
        }
        head--;

    }

    public void update(double dt){
        if(timeLeft > 0){
            timeLeft = timeLeft - dt;
            return;
        }
        timeLeft = waitUpdates;

        if(intersectingWithSelf() || intersectingWithBoundaries(snakeBody[head])){
            Window.getWindow().changeState(Constants.MENU_STATE);
            return;
        }

        double updatedX = 0;
        double updatedY = 0;

        if(direction == Constants.RIGHT){
            updatedX = snakeBody[head].getX() + bodyWidth;
            updatedY = snakeBody[head].getY();
        }

        else if(direction == Constants.LEFT){
            updatedX = snakeBody[head].getX() - bodyWidth;
            updatedY = snakeBody[head].getY();
        }

        else if(direction == Constants.UP){
            updatedX = snakeBody[head].getX();
            updatedY = snakeBody[head].getY() - bodyHeight;
        }

        else if(direction == Constants.DOWN){
            updatedX = snakeBody[head].getX();
            updatedY = snakeBody[head].getY() + bodyHeight;
        }

        snakeBody[(head + 1) % snakeBody.length] = snakeBody[tail];
        snakeBody[tail] = null;
        head = (head + 1) % snakeBody.length;
        tail = (tail + 1) % snakeBody.length;
        snakeBody[head].setX(updatedX);
        snakeBody[head].setY(updatedY);

    }

    public boolean intersect(Rect r1, Rect r2){
        return (r1.getX() >= r2.getX() && r1.getX() + r1.getWidth() <= r2.getX() + r2.getWidth() && r1.getY() >= r2.getY()
        && r1.getY() + r1.getHeight() <= r2.getY() + r2.getHeight());
    }

    public boolean intersectingWithSelf() {
        Rect headOfSnake = snakeBody[head];
        return intersectingWithRect(headOfSnake);
    }

    public boolean intersectingWithRect(Rect rect){
        for(int i = tail; i != head; i = (i +1) % snakeBody.length){
            if(intersect(rect, snakeBody[i]))
                return true;
        }
        return false;
    }

    public boolean intersectingWithBoundaries(Rect head) {
        return (head.getX() < background.getX() || (head.getX() + head.getWidth()) > background.getX() + background.getWidth() ||
                head.getY() < background.getY() || (head.getY() + head.getHeight()) > background.getY() + background.getHeight());
    }

    public void grow(){
        double updatedX = 0;
        double updatedY = 0;

        if(direction == Constants.RIGHT){
            updatedX = snakeBody[tail].getX() - bodyWidth;
            updatedY = snakeBody[tail].getY();
        }

        else if(direction == Constants.LEFT){
            updatedX = snakeBody[tail].getX() + bodyWidth;
            updatedY = snakeBody[tail].getY();
        }

        else if(direction == Constants.UP){
            updatedX = snakeBody[tail].getX();
            updatedY = snakeBody[tail].getY() + bodyHeight;
        }

        else if(direction == Constants.DOWN){
            updatedX = snakeBody[tail].getX();
            updatedY = snakeBody[tail].getY() - bodyHeight;
        }

        Rect newPiece = new Rect(updatedX, updatedY, bodyWidth, bodyHeight);

        tail = (tail - 1) % snakeBody.length;
        snakeBody[tail] = newPiece;
    }

    public void draw(Graphics graphics){
        Random random = new Random();
        Graphics2D g2 = (Graphics2D) graphics;
        for(int i = tail; i != head; i = (i + 1) % snakeBody.length){
            Rect piece = snakeBody[i];
            g2.setColor(new Color(random.nextInt(100),random.nextInt(100),random.nextInt(100)));
            g2.fill(new Rectangle2D.Double(piece.getX() + 2.0, piece.getY() + 2.0, bodyWidth, bodyHeight));

        }
    }
}
