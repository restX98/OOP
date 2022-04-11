import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Ball extends JComponent {
    
    private int diameter;
    private float x;
    private float y;
    private float speedY;
    private float accelerationY;
    private float mass;
    private long time;

    public Ball() {
        this(100, 100, 20);
    }

    public Ball(float x, float y) {
        this(x, y, 20);
    }

    public Ball(float x, float y, int diameter){
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.speedY = 0;
        this.accelerationY = 0;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int)x, (int)y, (int)diameter, (int)diameter);
    }

    public void moveBall(float dx, float dy) {
        this.x += dx;
        this.y += dy;
        this.repaint();
    }

    public void update(long time){
        long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(this.time);
        long newTimeInSeconds = TimeUnit.MILLISECONDS.toSeconds(time);
        float speedY = this.speedY + (this.accelerationY * (newTimeInSeconds - timeInSeconds));
        float y = this.y + (this.y * speedY)
                + (accelerationY * (float)(Math.pow(newTimeInSeconds, 2) - Math.pow(timeInSeconds, 2)));
        
        this.moveBall(0, (y - this.y));

        this.speedY = speedY;
        this.y = y;
        this.time = time;
        System.out.println(this.time);
    }

    public void applyGravityForce(){
        this.accelerationY = 9.81F;
    }

    // Getter & Setter
    public void setX(float x) {
        this.x = x;
    }
    public float getXX(){
        return this.x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getYY(){
        return this.y;
    }
    public void setDiameter(int diameter){
        //this.diameter = diameter;
    }
    public int getDiameter(){
        return this.diameter;
    }
    
}